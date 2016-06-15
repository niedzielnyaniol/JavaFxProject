package model.players;


/**
 *
 * @author Maciek
 */
public class PlayerSimpleFactory {
    
    public Player createPlayer(int stepOfEducation){
        Player player = null;
        
        switch (stepOfEducation) {
            case 1:
                player = new Preschooler();
                break;
            case 2:
                player = new Student();
                break;
            case 3:
                player = new CollegeStudent();
                break;
            case -2:
                player = new SecondPlayer();
                break;
            case -1:
                player = new ComputerPlayer();
                break;
        }
        
        return player;
    }
    
}