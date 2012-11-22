/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

/**
 *
 * @author Si
 */
public class RuleEngine {
  
  public boolean GameWon(FiniteStateMachine fsm){
    GameWonRule won = new GameWonRule(fsm);
    return won.fire();
  }
   
  public boolean GameDraw(FiniteStateMachine fsm){
   GameDrawnRule draw = new GameDrawnRule(fsm);
   return draw.fire();
  }
}
