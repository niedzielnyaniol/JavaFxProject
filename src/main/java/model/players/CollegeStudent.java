package model.players;


/**
 *
 * @author Maciek
 */
public class CollegeStudent extends Player{

    public CollegeStudent(){
        stepOfEducation = 3;
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