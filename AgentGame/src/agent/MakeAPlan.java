/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import logic.FiniteStateMachine;

/**
 *
 * @author Si
 */
public class MakeAPlan extends Belief {
  private List< Action > thePlan = new ArrayList< Action >( ); //choosen plan (random)
  private List< Action[] > plans = new ArrayList< Action[] >( ); //list of possible plans
  private String planed = " ";
   public MakeAPlan (FiniteStateMachine fsm )
  {
    super(fsm);
    // TODO
  }
  public String getPlan()
  { 
    return planed;
  }
   
  @Override
   public void update( )
  {
    int move;
    int middle;
    
    if(!thePlan.isEmpty()){ //we have a plan
      if(planAchieveable()){
          move = choosePlan( thePlan.size( ) );
          action = thePlan.get(move); //make this move
          thePlan.remove(move); //clear from move list
          predicate = true;  
      }
      else{
        if(createAPlan()) //new plan
        {
          move = choosePlan( thePlan.size( ) );
          action = thePlan.get(move); //make this move
          thePlan.remove(move); //clear from move list
          predicate = true; 
        }
        else
        {
          predicate = false;
        }
      }
      return;
    }
    
    if(plans.isEmpty()){ //we dont have any plans
      if(createAPlan()){
        middle = middleMove();
        if(middle != -1){
          move = middle;
        }
        else{
          move = choosePlan(  thePlan.size( ) );
        }
        action = thePlan.get(move); //make this move
        thePlan.remove(move); //clear from move list
        predicate = true;  
      }
      return;
    }
     
  }
   
   private boolean createAPlan()
   {
     int randomNumber;
     plans.clear(); //insure that the list is empty
     thePlan.clear(); //fail safe
     Action[] populate; 
     Horizontal();
     Vertical();
     Diagonal();    
     if(!plans.isEmpty())
     {
       randomNumber = choosePlan(  plans.size() ); // choose a plan at random
       populate = plans.get(randomNumber);
       thePlan.addAll(Arrays.asList(populate));
       if(thePlan.indexOf(null) != -1){
         thePlan.remove(thePlan.indexOf(null));
       }
       for(int i=0; i< thePlan.size(); i++){
         if(thePlan.get(i) != null){
            planed += thePlan.get(i).getRow() + "" + thePlan.get(i).getColumn() + " ";
         }
        }
       planed += " , ";
       return true;
     }
     return false;
   }
   
   private boolean planAchieveable()
   {
     Action check;
     for(int i = 0; i < thePlan.size(); i++)
     {
       check = thePlan.get(i);
       if(grid[check.getRow()][check.getColumn()] == FiniteStateMachine.State.cross)
       {
         thePlan.clear();
         plans.clear();
         return false;
       }       
       if(grid[check.getRow()][check.getColumn()] == FiniteStateMachine.State.nought)
       {
         thePlan.remove(i); //unlikely random move hit the plan
         i--; // need to reset the counter else a check will be misssed
       }
     }
     return true;
   }
           
     private void Horizontal(){
      int index = 0, blankCount = 0, crossCount = 0;
      Action [] location = new Action[3];
      while(index < FiniteStateMachine.NUMBEROFROWS)
      {
        for( int j = 0; j < FiniteStateMachine.NUMBEROFCOLUMNS; j++ )
        {
          if(grid[index][j] == FiniteStateMachine.State.blank){
            location[blankCount] = new Action(index, j);
            blankCount ++;
          }
          crossCount = (grid[index][j] == FiniteStateMachine.State.cross) ? crossCount + 1 : crossCount;
          
          if(j == 2 && crossCount == 0){
            plans.add(location);
          }
        }  
        index++;
        crossCount = 0;
        blankCount = 0;
        location = new Action[3];
      }
    }
      
      
    
    
    private void Vertical(){
      int index = 0, blankCount = 0, crossCount = 0;
      Action [] location = new Action[3];
      while(index < FiniteStateMachine.NUMBEROFCOLUMNS)
      {
        for( int j = 0; j < FiniteStateMachine.NUMBEROFROWS; j++ )
        {
          if(grid[j][index] == FiniteStateMachine.State.blank){
            location[blankCount] = new Action(j, index);
            blankCount ++;
          }
          crossCount = (grid[j][index] == FiniteStateMachine.State.cross) ? crossCount + 1 : crossCount;
          
           if(j == 2 && crossCount == 0){
             plans.add(location);
           }
        }  
        index++;
        crossCount = 0;
        blankCount = 0;
        location = new Action[3];
      }
    }
    
    
    private void Diagonal(){
      Action [] location = new Action[3];
      int  blankCount = 0, crossCount = 0;
      int rows = FiniteStateMachine.NUMBEROFROWS - 1;
      for( int j = 0; j < FiniteStateMachine.NUMBEROFCOLUMNS; j++ )
      {
        if(grid[j][j] == FiniteStateMachine.State.blank){
          location[blankCount] = new Action(j,j);
          blankCount ++;
        }
        crossCount = (grid[j][j] == FiniteStateMachine.State.cross) ? crossCount + 1 : crossCount;
        if(j == 2 && crossCount == 0){
          plans.add(location);
        }
      }  
      
      location = new Action[3];
      crossCount = 0;
      blankCount = 0;
      
      for( int j = 0; j < FiniteStateMachine.NUMBEROFCOLUMNS; j++ )
      {
        if(grid[rows - j][j] == FiniteStateMachine.State.blank){
          location[blankCount] = new Action(rows - j, j);
          blankCount ++;
        }
        crossCount = (grid[rows - j][j] == FiniteStateMachine.State.cross) ? crossCount + 1 : crossCount;
        
        if(j == 2 && crossCount == 0){
          plans.add(location);
        }
      }
      crossCount = 0;
      blankCount = 0;
    }      
    
 private int getRandomInRange( int lower, int higher )
 {
    return (int) ( Math.floor( Math.random( ) * 
                 ( higher - lower - 1 ) ) + lower );
 }
  private int choosePlan(  int higher )
 {
   Random r = new Random();
   return (int) r.nextInt(higher);
 }
  
  private int middleMove(){
    Action middle = new Action(1,1);
    int planSize = thePlan.size(), location = -1;
    for(int i = 0; i < planSize; i++){
      if(thePlan.get(i).toString().equals(middle.toString())){
        location = i;
        break;
      }
    }
    return location;
  }
   
}
