package model.players;

/**
 *
 * @author Maciek
 */
public class ComputerPlayer extends Player{

    public String winReaction() {
        return "Gracz komputerowy wygrał";
    }

    public String defeatReaction() {
        return "Gracz komputerowy przegrał";
    }    
}