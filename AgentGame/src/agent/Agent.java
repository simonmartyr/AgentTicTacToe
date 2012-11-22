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
public class Agent {
  private ICanWin win;
  private ICanMakeAMove move;
  private ICanBlockAWin block;
  
  public Agent(FiniteStateMachine fsm){
    win = new ICanWin(fsm);
    move = new ICanMakeAMove(fsm);
    block = new ICanBlockAWin(fsm);
  }
  
  public Action getNextMove( ){
    Action result = null;
    win.update();
    move.update();
    block.update();
    
    if(win.isTrue()){
      return win.getAction();
    }
    if(block.isTrue()){
      return block.getAction();
    }
    if(move.isTrue()){
      result = move.getAction();
    }
    
    return result;
  }
  
}
