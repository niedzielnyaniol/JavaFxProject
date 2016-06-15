package model.players;

/**
 *
 * @author Maciek
 */
public class SecondPlayer extends Player{
    
    public String winReaction() {
        return "Drugi gracz wygrał";
    }

    public String defeatReaction() {
        return "Drugi gracz przegrał";
    }
}