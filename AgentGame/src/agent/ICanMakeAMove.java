/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agent;

/**
 *
 * @author Simon
 */
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import logic.FiniteStateMachine;

public class ICanMakeAMove extends Belief {
  
  public ICanMakeAMove( FiniteStateMachine fsm )
  {
    super(fsm);
    // TODO
  }
  
  @Override
  public void update( )
  {
    //  reset belief to default - "I don't believe it!"
    predicate = false;
    //  create a list to hold all potential actions 
    //  i.e. the current blank cells
    List< Action > blanks = new ArrayList< Action >( );
    //  find the blank cells in the grid
    //  use the Action class for convenience
    for( int i = 0; i < FiniteStateMachine.NUMBEROFROWS; i++ )
    {
      for( int j = 0; j < FiniteStateMachine.NUMBEROFCOLUMNS; j++ )
      {
        if( grid[ i ][ j ] == BLANKSTATE )
        {
          blanks.add( new Action( i, j ) );
        }
      }
    }    
    assert blanks.size( ) > 1 : "impossible! no blank cells";
    // generate a random number
    final int randomNumber = getRandomInRange(  blanks.size( ) );
    // get a blank cell at random – A REALLY NAIVE WAY TO DO THIS!!!
    action = blanks.get( randomNumber );   
    predicate = true;   // now I believe it!!
    }
  
    private int getRandomInRange(  int higher )
    {
        Random r = new Random();
        return (int) r.nextInt(higher);
    }
}
