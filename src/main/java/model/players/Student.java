package model.players;

/**
 *
 * @author Maciek
 */
public class Student extends Player{

    public Student(){
        stepOfEducation = 2;
    }
    
    @Override
    public String winReaction() {
        return "Student wygrał";
    }

    @Override
    public String defeatReaction() {
        return "Student przegrał";
    }    
}