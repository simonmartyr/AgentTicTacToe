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
  private MakeAPlan plan;
  private boolean planTog = true;
  
  public Agent(FiniteStateMachine fsm){
    win = new ICanWin(fsm);
    move = new ICanMakeAMove(fsm);
    block = new ICanBlockAWin(fsm);
  }
  
  public void allowPlan(boolean allow){
    planTog = allow;
  }
  
  public String whatWasPlan(){
    return plan.getPlan();
  }
  public Action getNextMove(FiniteStateMachine fsm){
    win = new ICanWin(fsm);
    move = new ICanMakeAMove(fsm);
    block = new ICanBlockAWin(fsm); //reset
    
    if(planTog){
      if(plan == null){
        plan = new MakeAPlan(fsm); //dont wanna reset the plan
      }
      else{
        plan.updateFSM(fsm);
      }
    }
    Action result = null;
    
    win.update();
    if(win.isTrue()){ //hierarchy
      return win.getAction();
    }
    
    block.update();
    if(block.isTrue()){
      return block.getAction();
    }
    if(planTog){
      plan.update(); //update belifes
      if(plan.isTrue()){
        return plan.getAction();
      }
    }
    
    move.update();
    if(move.isTrue()){
      result = move.getAction();
    }
    
    return result;
  }
  
}
