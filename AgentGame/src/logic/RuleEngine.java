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
  GameWonRule won;
  
  public boolean GameWon(FiniteStateMachine fsm){
    won = new GameWonRule(fsm);
    return won.fire();
  }
   
  public boolean GameDraw(FiniteStateMachine fsm){
   GameDrawnRule draw = new GameDrawnRule(fsm);
   return draw.fire();
  }
  
  public String[] winningMove(){
    return won.getWin();
  }
}
