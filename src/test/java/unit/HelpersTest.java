/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unit;

import helpers.AvalibleGamesHelper;
import helpers.GameHistoryMaker;
import helpers.StringHelper;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Maciek
 */
public class HelpersTest {

    @Test
    public void stringHelper_StringCheckersTest(){
        String empty = "";
        String whiteSpace = " ";
        String text = " text";
       
        Assert.assertTrue(StringHelper.isNullOrEmpty(empty));
        Assert.assertFalse(StringHelper.isNullOrEmpty(whiteSpace));
        Assert.assertFalse(StringHelper.isNullOrEmpty(text));
        
        Assert.assertTrue(StringHelper.isNullOrWhitespace(empty));
        Assert.assertTrue(StringHelper.isNullOrWhitespace(whiteSpace));
        Assert.assertFalse(StringHelper.isNullOrWhitespace(text));
    }
    
    @Test
    public void stringHelper_ClockFormatTest(){
        int oneMinute = 60;
        int eightySecond = 80;
        int almostTwoMinutes = 117;
        
        Assert.assertEquals("01:00", StringHelper.clockFormat(oneMinute));
        Assert.assertEquals("01:20", StringHelper.clockFormat(eightySecond));
        Assert.assertEquals("01:57", StringHelper.clockFormat(almostTwoMinutes));
    }
    
    @Test
    public void gameHistoryTest(){
        GameHistoryMaker gameHistory = new GameHistoryMaker();
        
        Assert.assertTrue(StringHelper.isNullOrEmpty(
                gameHistory.getGameHistory())
        );
        
        gameHistory.appendNewLine("New Line");
        gameHistory.appendNewWord("new Word");
        
        Assert.assertEquals(gameHistory.getGameHistory(), "New Line\nnew Word ");
    }
    
    public void avalibleGamesHelperTest(){
        Assert.assertEquals(AvalibleGamesHelper.forPreschooler().size(), 2);
        Assert.assertEquals(AvalibleGamesHelper.forStudent().size(), 3);
        Assert.assertEquals(AvalibleGamesHelper.forCollegeStudent().size(), 4);
        
        Assert.assertTrue(AvalibleGamesHelper.forCollegeStudent()
                .contains("Gra w jelenie"));
        Assert.assertFalse(AvalibleGamesHelper.forPreschooler()
                .contains("Gra w jelenie"));
        Assert.assertTrue(AvalibleGamesHelper.forStudent()
                .contains("Kółko i krzyżyk"));
    }
}
