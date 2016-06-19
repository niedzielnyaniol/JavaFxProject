package model.players.playerDecoratorForThreeGames;

import java.util.Random;
import model.players.Player;


/**
 *
 * @author Maciek
 */
public class AIPlayerDecorator extends PlayerDecorator{
    
    private final Random rand;
    
    public AIPlayerDecorator(Player player){
        this.player = player;
        rand = new Random();
    }
    
    public int play(){
        choice = rand.nextInt(2);
        
        return choice;
    }
}