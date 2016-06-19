package model.players.playerDecoratorForThreeGames;

import model.players.Player;



/**
 *
 * @author Maciek
 */
public abstract class PlayerDecorator extends Player{

    protected Player player;
    protected int cash;
    protected int prize;
    protected int penalty;
    protected int choice;

    @Override
    public String getName(){
        return player.getName();
    }
    
    public int getChoice() {
        return choice;
    }
    
    public void setChoice(int choice) {
        this.choice = choice;
    }
    
    public String winReaction() {
        return player.winReaction();
    }

    public String defeatReaction() {
        return player.defeatReaction();
    }
    
    public abstract int play();

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public int getPrize() {
        return prize;
    }

    public void setPrize(int prize) {
        this.prize = prize;
    }

    public int getPenalty() {
        return penalty;
    }

    public void setPenalty(int penalty) {
        this.penalty = penalty;
    }
    
    public void lose(){
        cash += penalty;
    }
    
    public void lose(int money){
        cash += money;
    }
    
    public void win(){
        cash += prize;
    }
    
    public void win(int money){
        cash += money;
    }
}