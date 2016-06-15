/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

/**
 *
 * @author Maciek
 */
public class GameHistoryMaker {
    private String gameHistory;
    
    public GameHistoryMaker(){
        gameHistory = "";
    }
    
    public void appendNewLine(String text){
        gameHistory += text;
        gameHistory += "\n";
    }
    
    public void appendNewWord(String word){
        gameHistory += word;
        gameHistory += " ";
    }
    
    public String getGameHistory(){
        return gameHistory;
    }
    
}
