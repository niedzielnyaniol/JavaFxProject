package model.players.playerDecoratorForThreeGames;

import model.players.Player;


/**
 *
 * @author Maciek
 */
public class RealPlayerDecorator extends PlayerDecorator{
    

    public RealPlayerDecorator(Player player){
        this.player = player;
    }
    
    @Override
    public int play() {        
        return choice;
    }
}