/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import agent.Action;
import agent.Agent;
import logic.FiniteStateMachine.State;

/**
 *
 * @author simon-martyr
 */
public class Facade {
    private GameLogic game = new GameLogic();
    private FiniteStateMachine finny = new FiniteStateMachine();
    private FiniteStateMachine.State gameState; 
    private RuleEngine thomas = new RuleEngine();
    private Agent james = new Agent(finny); 
    private boolean move = true;
    
    public boolean makeMove(String location) { //player
      location = location.replace("grid", ""); //remove grid left with 22 etc.
      finny.updateCell(Integer.parseInt(String.valueOf(location.charAt(0))) - 1 , 
                       Integer.parseInt(String.valueOf(location.charAt(1))) - 1 , State.cross); //-1 due to arrays starting @ 0
      move = !move; // next player
      return true;
    }
    
    public int[] agentsMove(){
      int[] location = new int[2];
      Action acmove = james.getNextMove(finny);
      location[0] = acmove.getRow();
      location[1] = acmove.getColumn();
      finny.updateCell(location[0], location[1], State.nought); //update
      move = !move; // next player
      return location;
    }
    public boolean whosMove(){
      return move;
    }
    
    public boolean gameOver() {
      boolean over = (thomas.GameWon(finny) == true || thomas.GameDraw(finny) == true ) ? true : false;
      return over;
    }
    
    public boolean win() {
      return thomas.GameWon(finny);
    }
    
    public String[] winingMove(){
      return thomas.winningMove(); //last person to make a move
    }
    
    public void newGame(){ //reset everything
     finny = new FiniteStateMachine();
     james = new Agent(finny);
     
    }
    
    public String plan(){
      return james.whatWasPlan();
    }
}
