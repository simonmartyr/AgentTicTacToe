/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import agent.Agent; 
import agent.Action;
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
    private Agent james; 
    private boolean move = true;
    
    public boolean makeMove(String location) { //player
      if(move != true){
        move = !move; // next player
        return false;
      }
      
      gameState =  State.cross; //state set depending on who is playing
      location = location.replace("grid", ""); //remove grid left with 22 etc.
      finny.updateCell(Integer.parseInt(String.valueOf(location.charAt(0))) - 1 , Integer.parseInt(String.valueOf(location.charAt(1))) - 1 , gameState); //-1 due to arrays starting @ 0
      
      move = !move; // next player
      return true;
    }
    
    public int[] agentsMove(){
      int[] location = new int[2];
      james = new Agent(finny);
      Action acmove = james.getNextMove();
      location[0] = acmove.getRow();
      location[1] = acmove.getColumn();
      finny.updateCell(location[0], location[1], State.nought); //update
      return location;
    }
    
    public boolean gameOver() {
      boolean over = (thomas.GameWon(finny) == true || thomas.GameDraw(finny) == true ) ? true : false;
      return over;
    }
    
    public boolean win() {
      return thomas.GameWon(finny);
    }
    
    public boolean winner(){
      return move; //last person to make a move
    }
    
    public void newGame(){ //reset everything
     finny = new FiniteStateMachine();
     
    }
}
