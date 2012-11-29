/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agent;

import logic.FiniteStateMachine;

/**
 *
 * @author Simon
 */
public abstract class Belief {
  /* some finals for convenience, to save typing */
  protected static final FiniteStateMachine.State
    BLANKSTATE = FiniteStateMachine.State.blank;
  protected static final FiniteStateMachine.State
    NOUGHTSTATE = FiniteStateMachine.State.nought;
  protected static final FiniteStateMachine.State
    CROSSSTATE = FiniteStateMachine.State.cross;
    
  /** boolean to hold truth of belief */
  protected boolean predicate;
  /** reference to environment i.e. the grid of cells */
  protected FiniteStateMachine.State[ ][ ] grid;
  /** based on our beliefs, we determine the correct action */
  protected Action action;
  /** Creates a new instance of Belief */
  public Belief( FiniteStateMachine fsm )
  {
    grid = fsm.getGrid();
   // TODO
  }
  /** 
   *  is the belief true?
   *  @return true if belief holds, false otherwise
   */
  public boolean isTrue( )
  {
    return predicate;
  }
  /**
   *  update the belief
   */
  
  public void updateFSM(FiniteStateMachine fsm)
  {
    grid = fsm.getGrid();
  }
  
  public abstract void update( );
  /**
   *  get the action as determined from the belief
   *  Note: this is null until the Beliefs are updated
   *  @return action
   */
  public Action getAction( )
  {
    return action;
  }
  
}
