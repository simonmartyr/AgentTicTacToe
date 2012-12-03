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
  public GameWonRule( FiniteStateMachine fsm )
  {
     super(fsm);
     // TODO pass the fsm reference to the superclass
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
          crossCount = (grid[index][j] == FiniteStateMachine.State.cross) ? crossCount + 1 : crossCount;
          noughtCount = (grid[index][j] == FiniteStateMachine.State.nought) ? noughtCount + 1 : noughtCount;
          if(crossCount == FiniteStateMachine.NUMBEROFCOLUMNS || noughtCount == FiniteStateMachine.NUMBEROFCOLUMNS){
            return true;
          }
        }  
        index++;
        crossCount = 0;
        noughtCount = 0;
      }
      return false;
  }
  
  private boolean Vertical(){
      int crossCount = 0, noughtCount = 0, index = 0;
      while(index < FiniteStateMachine.NUMBEROFCOLUMNS)
      {
        for( int j = 0; j < FiniteStateMachine.NUMBEROFROWS; j++ )
        {
          crossCount = (grid[j][index] == FiniteStateMachine.State.cross) ? crossCount + 1 : crossCount;
          noughtCount = (grid[j][index] == FiniteStateMachine.State.nought) ? noughtCount + 1 : noughtCount;
          if(crossCount == FiniteStateMachine.NUMBEROFCOLUMNS || noughtCount == FiniteStateMachine.NUMBEROFCOLUMNS){
            return true;
          }
        }  
        index++;
        crossCount = 0;
        noughtCount = 0;
      }
      return false;
    }
  
  private boolean Diagonal(){
      int crossCount = 0, noughtCount = 0;
      int rows = FiniteStateMachine.NUMBEROFROWS - 1;
      for( int j = 0; j < FiniteStateMachine.NUMBEROFCOLUMNS; j++ )
      {
        crossCount = (grid[j][j] == FiniteStateMachine.State.cross) ? crossCount + 1 : crossCount;
        noughtCount = (grid[j][j] == FiniteStateMachine.State.nought) ? noughtCount + 1 : noughtCount;
        if(crossCount == FiniteStateMachine.NUMBEROFCOLUMNS || noughtCount == FiniteStateMachine.NUMBEROFCOLUMNS){
          return true;
        }
      }  
      
      crossCount = 0;
      noughtCount = 0;
      
      for( int j = 0; j < FiniteStateMachine.NUMBEROFCOLUMNS; j++ )
      {
        crossCount = (grid[rows - j][j] == FiniteStateMachine.State.cross) ? crossCount + 1 : crossCount;
        noughtCount = (grid[rows - j][j] == FiniteStateMachine.State.nought) ? noughtCount + 1 : noughtCount;
        if(crossCount == FiniteStateMachine.NUMBEROFCOLUMNS || noughtCount == FiniteStateMachine.NUMBEROFCOLUMNS){
          return true;
        }
      }
      return false;
    }

}

