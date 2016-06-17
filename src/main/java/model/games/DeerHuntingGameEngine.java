/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.games;

import helpers.StringHelper;
import model.players.playerDecorator.PlayerDecorator;

/**
 *
 * @author Maciek
 */
public class DeerHuntingGameEngine implements GameEngine{
    
    private final int[][] player1Matrix;
    private final int[][] player2Matrix;
    
    public DeerHuntingGameEngine(){
        player1Matrix = new int[2][2];
        player1Matrix[0][0] = 2;
        player1Matrix[0][1] = 0;
        player1Matrix[1][0] = 1;
        player1Matrix[1][1] = 1;
        
        player2Matrix = new int[2][2];
        player2Matrix[0][0] = 2;
        player2Matrix[0][1] = 1;
        player2Matrix[1][0] = 0;
        player2Matrix[1][1] = 1;        
    }


    @Override
    public String check(PlayerDecorator[] players) {
        StringBuilder returnString = new StringBuilder();
            
        int player1prize = player1Matrix[players[0].getChoice()]
                [players[1].getChoice()];
        int player2prize = player2Matrix[players[0].getChoice()]
                [players[1].getChoice()];

        players[0].win(player1prize);
        players[1].win(player2prize);

        returnString.append(players[0].getName())
                .append(" otrzymał ")
                .append(player1prize)
                .append("\n")
                .append(players[1].getName())
                .append(" otrzymał ")
                .append(player2prize);

        return returnString.toString() + StringHelper.accountState(players);
    }
//"Gracz " + players[1].getName() + " wybrał orła"
    @Override
    public String valueConvert(PlayerDecorator player) {
        StringBuilder returnString = new StringBuilder();
        
        returnString.append("Gracz ")
                .append(player.getName())
                .append(" wybrał ");
        
        if (player.getChoice() == 0) {
            returnString.append("Jelenia");
        }
        else{
            returnString.append("Zająca");
        }
        
        return returnString.toString();
    }

    @Override
    public int getNumberOfChoicePossibilities() {
        return 2;
    }
}
