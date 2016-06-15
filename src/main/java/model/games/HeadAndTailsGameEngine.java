/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.games;

import model.players.playerDecorator.PlayerDecorator;

/**
 *
 * @author Maciek
 */
public class HeadAndTailsGameEngine {
    
    public static String check(PlayerDecorator player1, PlayerDecorator player2) {

        if(player1.getChoice() == player2.getChoice()){

            player1.win();
            player2.lose();

            return "Wygrał " + player1.getName(); 
        }
        else{
            player1.lose();
            player2.win();

            return "Wygrał " + player2.getName();
        }

    }
    
}
