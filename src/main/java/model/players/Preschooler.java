package model.players;

/**
 *
 * @author Maciek
 */
public class Preschooler extends Player{

    public Preschooler(){
        stepOfEducation = 1;
    }
    
    @Override
    public String winReaction() {
        return "Przedszkolak wygrał";
    }

    @Override
    public String defeatReaction() {
        return "Przedszkolak przegrał";
    }
}