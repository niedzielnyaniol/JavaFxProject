package model.players.playerAdapterForThreeGames;

import java.util.Random;
import model.players.Player;


/**
 *
 * @author Maciek
 */
public class AIPlayerAdapter extends PlayerAdapter{
    
    private final Random rand;
    
    public AIPlayerAdapter(Player player){
        this.player = player;
        rand = new Random();
    }
    
    public int play(){
        choice = rand.nextInt(2);
        
        return choice;
    }
}