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
public class RockPaperScisorsGameEngine implements GameEngine{
    
    @Override
    public String check(PlayerDecorator[] players) {
        
        int winner = 0;
        int looser = 1;
        
        if(players[1].getChoice() == players[0].getChoice()){
            return "Remis" + StringHelper.accountState(players);
        }
        else if (players[0].getChoice() == 0) { //kamien
            if(players[1].getChoice() == 1){
                winner = 1;
                looser = 0;
            }
        }
        else if (players[0].getChoice() == 1) { //papier
            if(players[1].getChoice() == 2){
                winner = 1;
                looser = 0;
            }
        }
        else{
            if(players[1].getChoice() == 0){
                winner = 1;
                looser = 0;
            }
        }
        
        players[winner].win();
        players[looser].lose();
        
        return "Wygrał " + players[winner].getName() 
                + StringHelper.accountState(players);
    }

    @Override
    public String valueConvert(PlayerDecorator player) {
        StringBuilder returnString = new StringBuilder();
        
        returnString.append("Gracz ")
                .append(player.getName())
                .append(" wybrał ");
        
        switch (player.getChoice()) {
            case 0:
                returnString.append(" Kamień");
                break;
            case 1:
                returnString.append(" Papier");
                break;
            default:
                returnString.append(" Nożyce");
                break;
        }
        
        return returnString.toString();
    }

    @Override
    public int getNumberOfChoicePossibilities() {
        return 3;
    }
}
