/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agent;

import java.util.ArrayList;
import java.util.List;
import logic.FiniteStateMachine;

/**
 *
 * @author Simon
 */
public class ICanBlockAWin extends Belief {
  private List< Action > blanks = new ArrayList< Action >( );
  private int index = 0, blankCount = 0, crossCount = 0;
  public ICanBlockAWin( FiniteStateMachine fsm )
  {
    super(fsm);
    // TODO
  }
  
  @Override
  public void update( )
  {
    predicate = false; //  reset belief 

    Horizontal();
    if(!blanks.isEmpty())
    {
      action = blanks.get(0);
      predicate = true;  
      return;
    }
    
    Vertical();
    if(!blanks.isEmpty())
    {
      action = blanks.get(0);
      predicate = true;   
      return;
    }
    
    Diagonal();
    if(!blanks.isEmpty())
    {
      action = blanks.get(0);
      predicate = true;  
    }
   }
  
  
    private void Horizontal(){
      int [] location = new int[2];
      while(index < FiniteStateMachine.NUMBEROFROWS)
      {
        for( int j = 0; j < FiniteStateMachine.NUMBEROFCOLUMNS; j++ )
        {
          if(grid[index][j] == FiniteStateMachine.State.blank){
            blankCount ++;
            location[0] = index;
            location[1] = j;
          }
          crossCount = (grid[index][j] == FiniteStateMachine.State.cross) ? crossCount + 1 : crossCount;
          
          if(crossCount == FiniteStateMachine.NUMBEROFCOLUMNS - 1 && blankCount == 1){
            blanks.add(new Action(location[0], location[1]));
            return;
          }
        }  
        index++;
        crossCount = 0;
        blankCount = 0;
      }
      index = 0;
    }
      
      
    
    
    private void Vertical(){
      int [] location = new int[2];
      while(index < FiniteStateMachine.NUMBEROFCOLUMNS)
      {
        for( int j = 0; j < FiniteStateMachine.NUMBEROFROWS; j++ )
        {
          if(grid[j][index] == FiniteStateMachine.State.blank){
            blankCount ++;
            location[0] = j;
            location[1] = index;
          }
          crossCount = (grid[j][index] == FiniteStateMachine.State.cross) ? crossCount + 1 : crossCount;
          
          if(crossCount == FiniteStateMachine.NUMBEROFCOLUMNS - 1 && blankCount == 1){
            blanks.add(new Action(location[0], location[1]));
            return;
          }
        }  
        index++;
        crossCount = 0;
        blankCount = 0;
      }
      index = 0;
    }
    
    
    private void Diagonal(){
      int [] location = new int[2];
      int rows = FiniteStateMachine.NUMBEROFROWS - 1;
      for( int j = 0; j < FiniteStateMachine.NUMBEROFCOLUMNS; j++ )
      {
        if(grid[j][j] == FiniteStateMachine.State.blank){
          blankCount ++;
          location[0] = j;
          location[1] = j;
        }
        crossCount = (grid[j][j] == FiniteStateMachine.State.cross) ? crossCount + 1 : crossCount;

        if(crossCount == FiniteStateMachine.NUMBEROFCOLUMNS - 1 && blankCount == 1){
          blanks.add(new Action(location[0], location[1]));
          return;
        }
      }  
      
      crossCount = 0;
      blankCount = 0;
      
      for( int j = 0; j < FiniteStateMachine.NUMBEROFCOLUMNS; j++ )
      {
        if(grid[rows - j][j] == FiniteStateMachine.State.blank){
          blankCount ++;
          location[0] = rows - j;
          location[1] = j;
        }
        crossCount = (grid[rows - j][j] == FiniteStateMachine.State.cross) ? crossCount + 1 : crossCount;
        
        if(crossCount == FiniteStateMachine.NUMBEROFCOLUMNS - 1 && blankCount == 1){
          blanks.add(new Action(location[0], location[1]));
        }
      }
    }
    
  
  
  
}
