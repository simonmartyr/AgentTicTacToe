/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

/**
 *
 * @author Si
 */

public class GameDrawnRule extends Rule {
  /** 
   * Creates a new instance of GameDrawnRule 
   *  @param reference to Finite State Machine
   */
  
  public GameDrawnRule( FiniteStateMachine fsm )
  {
     super(fsm);
        // TODO pass the fsm reference to the superclass
  }
  /**
   *  does the game drawn rule fire?
   *  (implementation of abstract method)
   *  looks for the absence of cells in the blank state
   *  @param true if game is drawn, false otherwise 
   */
  public boolean fire( )
  {
    boolean found = false;
    for( int i = 0; 
    i < FiniteStateMachine.NUMBEROFROWS; 
    i++ )
    {
      for( int j = 0; 
      j < FiniteStateMachine.NUMBEROFCOLUMNS; 
      j++ )
      {
        if( grid[ i ][ j ] == 
            FiniteStateMachine.State.blank )
        {
          found = true;
        }
      }
    }
    return ! found;
  }
  
  
}
