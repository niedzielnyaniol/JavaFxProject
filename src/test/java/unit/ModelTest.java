/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unit;

import model.players.Player;
import model.players.PlayerSimpleFactory;
import model.players.playerAdapterForThreeGames.PlayerAdapter;
import model.players.playerAdapterForThreeGames.RealPlayerAdapter;
import model.threeGames.GameEngine;
import model.threeGames.HeadAndTailsGameEngine;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author Maciek
 */
public class ModelTest {
    
    private PlayerAdapter[] players;
    
    @Before
    public void setUp(){
        PlayerSimpleFactory factory = new PlayerSimpleFactory();
        Player player1 = factory.createPlayer(1);
        player1.setName("player1");
        Player player2 = factory.createPlayer(1);
        player2.setName("player2");
        
        players = new PlayerAdapter[]{
            new RealPlayerAdapter(player1),
            new RealPlayerAdapter(player2)
        };
    }
    
    @Test
    public void valueConvert_ForHeadAndTails(){
        GameEngine gameEngine = new HeadAndTailsGameEngine();
        players[0].setChoice(1);
        
        assertEquals("Gracz player1 wybrał Reszkę", 
                gameEngine.valueConvert(players[0]));
    }
    
}
