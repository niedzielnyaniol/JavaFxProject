/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.threeGames;

import model.players.Player;
import model.players.playerDecoratorForThreeGames.PlayerDecorator;

/**
 *
 * @author Maciek
 */
public interface GameEngine {

    public String check(PlayerDecorator[] players);
    public String valueConvert(PlayerDecorator player);
    public int getNumberOfChoicePossibilities();
    
}
