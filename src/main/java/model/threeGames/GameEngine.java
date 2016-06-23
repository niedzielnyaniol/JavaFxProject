/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.threeGames;

import model.players.Player;
import model.players.playerAdapterForThreeGames.PlayerAdapter;

/**
 *
 * @author Maciek
 */
public interface GameEngine {

    public String check(PlayerAdapter[] players);
    public String valueConvert(PlayerAdapter player);
    public int getNumberOfChoicePossibilities();
    
}
