/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

/**
 *
 * @author s2-martyr
 */
public class GameLogic {
  private boolean turn = true; // ture == player1, false == player 2
  
  public boolean makeMove(){
    boolean move = turn; //whos turn is it?
    turn = !turn; 
    return move; 
  }
  
  
}
