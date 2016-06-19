/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import helpers.GameHistoryMaker;
import helpers.MainTimer;
import helpers.SetScene;
import helpers.StringHelper;
import java.io.IOException;
import java.util.Timer;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import model.players.Player;
import model.players.PlayerSimpleFactory;

public class FXMLTicTacToeSceneController {
   
    @FXML Button b1; 
    @FXML Button b2;
    @FXML Button b3;
    @FXML Button b4;
    @FXML Button b5;
    @FXML Button b6;
    @FXML Button b7;
    @FXML Button b8;
    @FXML Button b9;
    @FXML Label clockLabel;

    @FXML GridPane gameBoard;

    private Player[] players;
    private boolean isFirstPlayer;
    private int roundNumber;
    private GameHistoryMaker gameHistory;
    private SetScene setter;
    private MainTimer timer;
   
    public void initData(Player firstPlayer) {
        timer = new MainTimer();
        timer.mainClockStart(clockLabel);
        
        PlayerSimpleFactory factory = new PlayerSimpleFactory();
        Player secPlayer = factory.createPlayer(-2); //-2 = second player;
        secPlayer.setName("Drugi Gracz");
        
        players = new Player[] { firstPlayer, secPlayer };
        
        roundNumber = 0;
        isFirstPlayer = true;
        
        setter = new SetScene();
        gameHistory = new GameHistoryMaker();
    }
   
    public void buttonClickHandler(ActionEvent event) throws IOException{

        Button clickedButton = (Button) event.getTarget();
        String buttonLabel = clickedButton.getText();

        
        if ("".equals(buttonLabel) && isFirstPlayer){
            clickedButton.setText("X");
            isFirstPlayer = false;
            roundNumber++;
        } else if("".equals(buttonLabel) && !isFirstPlayer){
            clickedButton.setText("O");
            isFirstPlayer = true;
            roundNumber++;
        }

        if(find3InARow(isFirstPlayer)){
            timer.stop();
            gameHistory.appendNewLine("Gra trwała " 
                    + StringHelper.clockFormat(timer.getTime()));
            setter.goToEndingScene(event, gameHistory.getGameHistory());
        }
        else if (roundNumber == 9) {
            timer.stop();
            gameHistory.appendNewLine("Remis");
            gameHistory.appendNewLine("Gra trwała " 
                    + StringHelper.clockFormat(timer.getTime()));
            setter.goToEndingScene(event, gameHistory.getGameHistory());
        }
    }	

    private boolean find3InARow(boolean firstPlayer1){
        //Row 1
        boolean returnVal = false;
        if (""!=b1.getText() && b1.getText() == b2.getText() 
                && b2.getText() == b3.getText()){
            
            returnVal = true;
        }
        //Row 2
        if (""!=b4.getText() && b4.getText() == b5.getText() 
                && b5.getText() == b6.getText()){
            returnVal = true;
        }
        //Row 3
        if (""!=b7.getText() && b7.getText() == b8.getText() 
                && b8.getText() == b9.getText()){
            returnVal = true;
        }
        //Column 1
        if (""!=b1.getText() && b1.getText() == b4.getText() 
                && b4.getText() == b7.getText()){
            returnVal = true;
        }
        //Column 2
        if (""!=b2.getText() && b2.getText() == b5.getText() 
                && b5.getText() == b8.getText()){
            returnVal = true;
        }
        //Column 3
        if (""!=b3.getText() && b3.getText() == b6.getText() 
                && b6.getText() == b9.getText()){
            returnVal = true;
        }
        //Diagonal 1
        if (""!=b1.getText() && b1.getText() == b5.getText() 
                && b5.getText() == b9.getText()){
            returnVal = true;
        }
        //Diagonal 2
        if (""!=b3.getText() && b3.getText() == b5.getText() 
                && b5.getText() == b7.getText()){
            returnVal = true;
        }	   
        
        if (returnVal) {
            int playerNumber = isFirstPlayer ? 0 : 1;
            gameHistory.appendNewLine("Wygrał " 
                    + players[playerNumber].getName()
                    + " w " + roundNumber / 2 + " ruchach.");
        }
        
        return returnVal;
    }
}
