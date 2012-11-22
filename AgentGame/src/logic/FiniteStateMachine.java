/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

/**
 *
 * @author s2-martyr
 */
public class FiniteStateMachine {
  public static final int NUMBEROFROWS = 3;
  public static final int NUMBEROFCOLUMNS = 3; 
  private State[][] grid;
 
  public enum State { blank, nought, cross };

  public FiniteStateMachine() { //Constructor 
    grid = new State[ NUMBEROFROWS ][ NUMBEROFCOLUMNS ];
    for( int i = 0; i < NUMBEROFROWS; i++ )
    {
      for( int j = 0; j < NUMBEROFCOLUMNS; j++ )
      {
        grid[ i ][ j ] = State.blank;
      }
    }
  }
    
  public void updateCell( int row, int column, State state )
  {
    grid[row][column] = state; 
  }
  
  public State getCellState( int row, int column )
  {
    return grid[row][column];
  }
   
  public State[ ][ ] getGrid( )
  {
    return grid; 
  }
   
}
