/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unit;

import model.players.CollegeStudent;
import model.players.ComputerPlayer;
import model.players.Player;
import model.players.PlayerSimpleFactory;
import model.players.Preschooler;
import model.players.SecondPlayer;
import model.players.Student;
import model.players.playerDecoratorForThreeGames.AIPlayerDecorator;
import model.players.playerDecoratorForThreeGames.PlayerDecorator;
import model.players.playerDecoratorForThreeGames.RealPlayerDecorator;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Maciek
 */

public class PlayersTest {

    @Test
    public void playerSimpleFactoryTest(){
        PlayerSimpleFactory factory = new PlayerSimpleFactory();
        
        Player preshooler       = factory.createPlayer(1);
        Player student          = factory.createPlayer(2);
        Player collegeStudent   = factory.createPlayer(3);
        Player computerPlayer   = factory.createPlayer(-1);
        Player secondPlayer     = factory.createPlayer(-2);
        
        Assert.assertTrue(preshooler instanceof Preschooler);
        Assert.assertTrue(student instanceof Student);
        Assert.assertTrue(collegeStudent instanceof CollegeStudent);
        Assert.assertTrue(computerPlayer instanceof ComputerPlayer);
        Assert.assertTrue(secondPlayer instanceof SecondPlayer);
    }
    
    @Test
    public void playerDecoratorTest(){
        PlayerSimpleFactory factory = new PlayerSimpleFactory();
        
        PlayerDecorator AIPlayer = new AIPlayerDecorator(
                factory.createPlayer(1)
        );
        
        PlayerDecorator RealPlayer = new RealPlayerDecorator(
                factory.createPlayer(1)
        );
        
        Assert.assertNotEquals(AIPlayer.getClass(), Player.class);
        Assert.assertNotEquals(RealPlayer.getClass(), Player.class);
        Assert.assertTrue(AIPlayer instanceof PlayerDecorator);
    }
}
