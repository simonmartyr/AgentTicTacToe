/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

/**
 *
 * @author Si
 */
public abstract class Rule {
  /**
   *  Reference to grid of Finite State Machine
   */
  protected FiniteStateMachine.State[ ][ ] grid;
 /** 
  *  Constructor
  *  @param reference to finite state machine
  */
  public Rule( FiniteStateMachine fsm )
  {
    grid = fsm.getGrid();
  }
   /**
     *  does the rule fire?
     *  @return true if rule holds, false otherwise
     */
  public abstract boolean fire( );
  
  
}
