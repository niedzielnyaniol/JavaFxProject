/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.threeGames;

import model.players.playerDecoratorForThreeGames.PlayerDecorator;

/**
 *
 * @author Maciek
 */
public class Winner {
    
    public static String getWinner(PlayerDecorator[] players){
        
        int winner = 0;
        int looser = 1;
        StringBuilder returnString = new StringBuilder();
        returnString.append("---------------------------\n");
        
        if (players[0].getCash() == players[1].getCash()){
            returnString.append("Remis\n");
        }
        else{
            if (players[0].getCash() > players[1].getCash()) {
                winner = 0;
                looser = 1;
            }
            
            returnString.append("Grę wygrał ")
                    .append(players[winner].getName())
                    .append("\n")
                    .append(players[winner].winReaction())
                    .append("\n")
                    .append(players[looser].defeatReaction())
                    .append("\n")
                    .append("---------------------------");
        }
        
        return returnString.toString();
    }
    
}
