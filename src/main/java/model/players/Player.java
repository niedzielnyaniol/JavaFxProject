package model.players;

import model.players.interfaces.Defeat;
import model.players.interfaces.Win;

/**
 *
 * @author Maciek
 */
public abstract class Player implements Win, Defeat{
    protected String name;
    protected int stepOfEducation;

    public int getStepOfEducation() {
        return stepOfEducation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }    
}