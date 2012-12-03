/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

/**
 *
 * @author Si
 */
public class GameWonRule extends Rule {
  private String[] Capwin = new String[3];
  
  public GameWonRule( FiniteStateMachine fsm )
  {
     super(fsm);
     // TODO pass the fsm reference to the superclass
  }
  
  public String[] getWin(){
    return Capwin;
  }
  
  @Override
  public boolean fire( )
  {
    if(Horizontal()){
      return true;
    }
    if(Vertical()){
      return true;
    }
    if(Diagonal()){
      return true;
    }
    
    return false;
  }
  
  private boolean Horizontal(){
      int crossCount = 0, noughtCount = 0, index = 0;
      while(index < FiniteStateMachine.NUMBEROFROWS)
      {
        for( int j = 0; j < FiniteStateMachine.NUMBEROFCOLUMNS; j++ )
        {
          Capwin[j] = Integer.toString(index + 1) + Integer.toString(j + 1);
          crossCount = (grid[index][j] == FiniteStateMachine.State.cross) ? crossCount + 1 : crossCount;
          noughtCount = (grid[index][j] == FiniteStateMachine.State.nought) ? noughtCount + 1 : noughtCount;
          if(crossCount == FiniteStateMachine.NUMBEROFCOLUMNS || noughtCount == FiniteStateMachine.NUMBEROFCOLUMNS){
            
            return true;
          }
        }  
        index++;
        crossCount = 0;
        noughtCount = 0;
        Capwin = new String[3];
      }
      Capwin = new String[3];
      return false;
  }
  
  private boolean Vertical(){
      int crossCount = 0, noughtCount = 0, index = 0;
      while(index < FiniteStateMachine.NUMBEROFCOLUMNS)
      {
        for( int j = 0; j < FiniteStateMachine.NUMBEROFROWS; j++ )
        {
          Capwin[j] = Integer.toString(j + 1) + Integer.toString(index + 1);
          crossCount = (grid[j][index] == FiniteStateMachine.State.cross) ? crossCount + 1 : crossCount;
          noughtCount = (grid[j][index] == FiniteStateMachine.State.nought) ? noughtCount + 1 : noughtCount;
          if(crossCount == FiniteStateMachine.NUMBEROFCOLUMNS || noughtCount == FiniteStateMachine.NUMBEROFCOLUMNS){
            return true;
          }
        }  
        index++;
        crossCount = 0;
        noughtCount = 0;
        Capwin = new String[3];
      }
      Capwin = new String[3];
      return false;
    }
  
  private boolean Diagonal(){
      int crossCount = 0, noughtCount = 0;
      int rows = FiniteStateMachine.NUMBEROFROWS - 1;
      for( int j = 0; j < FiniteStateMachine.NUMBEROFCOLUMNS; j++ )
      {
        Capwin[j] = Integer.toString(j + 1) + Integer.toString(j + 1);
        crossCount = (grid[j][j] == FiniteStateMachine.State.cross) ? crossCount + 1 : crossCount;
        noughtCount = (grid[j][j] == FiniteStateMachine.State.nought) ? noughtCount + 1 : noughtCount;
        if(crossCount == FiniteStateMachine.NUMBEROFCOLUMNS || noughtCount == FiniteStateMachine.NUMBEROFCOLUMNS){
          return true;
        }
      }  
      Capwin = new String[3];
      crossCount = 0;
      noughtCount = 0;
      
      for( int j = 0; j < FiniteStateMachine.NUMBEROFCOLUMNS; j++ )
      {
        Capwin[j] = Integer.toString(rows - j + 1) + Integer.toString(j + 1);
        crossCount = (grid[rows - j][j] == FiniteStateMachine.State.cross) ? crossCount + 1 : crossCount;
        noughtCount = (grid[rows - j][j] == FiniteStateMachine.State.nought) ? noughtCount + 1 : noughtCount;
        if(crossCount == FiniteStateMachine.NUMBEROFCOLUMNS || noughtCount == FiniteStateMachine.NUMBEROFCOLUMNS){
          return true;
        }
      }
      Capwin = new String[3];
      return false;
    }

}

