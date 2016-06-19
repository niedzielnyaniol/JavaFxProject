/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import model.players.playerDecoratorForThreeGames.PlayerDecorator;

/**
 *
 * @author Maciek
 */
public class StringHelper {
    public static boolean isNullOrEmpty(String s) {
        return s == null || s.length() == 0;
    }

    public static boolean isNullOrWhitespace(String s) {
        return isNullOrEmpty(s) || isWhitespace(s);
    }
    
    public static String roundFormat(int currentRound, int numberOfRounds){
        String roundFormat = String.valueOf(currentRound);
        roundFormat += "/" + String.valueOf(numberOfRounds);
        
        return roundFormat;
    }
    
    public static String clockFormat(int time){
        String minutes = String.valueOf(time / 60);
        String seconds = String.valueOf(time % 60);
        
        if (minutes.length() == 1) {
            minutes = "0" + minutes;
        }
        if (seconds.length() == 1) {
            seconds = "0" + seconds;
        }
        
        return minutes + ":" + seconds;
    }
    
    public static String accountState(PlayerDecorator[] players){
            return "\nStan konta gracza " 
                + ": " + players[0].getCash()
                + "\nStan konta gracza " 
                + players[1].getName() 
                + ": " + players[1].getCash();
    }
    
    private static boolean isWhitespace(String s) {
        int length = s.length();
        if (length > 0) {
            for (int i = 0; i < length; i++) {
                if (!Character.isWhitespace(s.charAt(i))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
