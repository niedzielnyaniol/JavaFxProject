/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.games;

import model.players.Player;
import model.players.playerDecorator.PlayerDecorator;

/**
 *
 * @author Maciek
 */
public class Winner {
    
    public static String getWinner(PlayerDecorator player1, 
            PlayerDecorator player2){
        if (player1.getCash() > player2.getCash()) {
            return player1.getName();
        }
        else if (player1.getCash() < player2.getCash()) {
            return player2.getName();
        }
        else{
            return "Remis";
        }
    }
    
}
