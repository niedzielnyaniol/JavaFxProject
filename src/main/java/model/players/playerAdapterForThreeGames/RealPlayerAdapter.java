package model.players.playerAdapterForThreeGames;

import model.players.Player;


/**
 *
 * @author Maciek
 */
public class RealPlayerAdapter extends PlayerAdapter{
    

    public RealPlayerAdapter(Player player){
        this.player = player;
    }
    
    @Override
    public int play() {        
        return choice;
    }
}