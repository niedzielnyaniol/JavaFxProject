/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.threeGames;

import helpers.StringHelper;
import model.players.playerDecoratorForThreeGames.PlayerDecorator;

/**
 *
 * @author Maciek
 */
public class HeadAndTailsGameEngine implements GameEngine{
    
    public String check(PlayerDecorator[] players) {
        int winner = 1;
        int looser = 0;
        
        if(players[0].getChoice() == players[1].getChoice()){
            winner = 0;
            looser = 1;
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
                returnString.append("Orła");
                break;
            case 1:
                returnString.append("Reszkę");
                break;
        }
        
        return returnString.toString();
    }

    @Override
    public int getNumberOfChoicePossibilities() {
        return 2;
    }
    
}
