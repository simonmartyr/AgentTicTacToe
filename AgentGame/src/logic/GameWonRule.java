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
  
  public boolean fire( )
  {
    boolean found = false;
    int index = 0; //index holder
    
    //horz win find
    while(index < FiniteStateMachine.NUMBEROFROWS)
    {
      for( int j = 0; j < FiniteStateMachine.NUMBEROFCOLUMNS; j++ )
      {
        if (j > 0){
          if(grid[ index ][ j - 1 ].equals(grid[ index ][ j ]) && grid[ index ][ j ] != FiniteStateMachine.State.blank)
          {
            if(j == FiniteStateMachine.NUMBEROFCOLUMNS - 1){
              found = true;
            }
          }
          else
          {
            break; // conflict
          }
        }  
      }
      index++;
    }
    
    index = 0; //reset index
    
    //vertical win find
    if(!found){
      while(index < FiniteStateMachine.NUMBEROFROWS)
      {
        for( int i = 0; i < FiniteStateMachine.NUMBEROFCOLUMNS; i++ )
        {
          if (i > 0)
          {
            if(grid[ i -1 ][ index ].equals(grid[ i ][ index ]) && grid[ i ][ index ] != FiniteStateMachine.State.blank)
            {
              if(i == FiniteStateMachine.NUMBEROFCOLUMNS - 1)
              {
                found = true;
              }
            }
            else
            {
              break; // conflict
            }
          }
        }
        index++;
      }
    }
    
    //Diagonal 
    if(!found)
    {
      for( int i = 0; i < FiniteStateMachine.NUMBEROFROWS; i++ )
      {
        if(i > 0)
        {
          if(grid[ i - 1 ][ i - 1 ].equals(grid[ i ][ i ]) && grid[ i ][ i ] != FiniteStateMachine.State.blank)
          {
            if(i == FiniteStateMachine.NUMBEROFCOLUMNS - 1){
              found = true;
            }
          }
          else
          {
            break; // conflict
          }
        }
      }
    }
    
    if(!found)
    {
      int row = FiniteStateMachine.NUMBEROFCOLUMNS;
      for( int i = 0; i < FiniteStateMachine.NUMBEROFROWS; i++ )
      {
        if(i > 0)
        {
          if(grid[ row - i][i - 1].equals(grid[ row - i - 1 ][ i ]) && grid[ row - i - 1 ][ i ] != FiniteStateMachine.State.blank)
          {
            if(i == FiniteStateMachine.NUMBEROFCOLUMNS - 1)
            {
              found = true;
            }
          }
          else
          {
            break; // conflict
          }
        }
      }
    }
    
    return found;
  }
}
