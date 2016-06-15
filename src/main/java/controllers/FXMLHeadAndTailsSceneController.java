/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import helpers.CountdownTimer;
import helpers.GameHistoryMaker;
import helpers.MainTimer;
import helpers.StringHelper;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.players.playerDecorator.PlayerDecorator;

/**
 * FXML Controller class
 *
 * @author Maciek
 */
public class FXMLHeadAndTailsSceneController implements Initializable {

    private PlayerDecorator[] players;
    private Control[] firstPlayerControlls;
    private Control[] secondPlayerControlls;
    private int numberOfRounds;
    private int currentRound;
    private int playerTurn;
    private int players1Choice;
    private int players2Choice;
    private MainTimer timer;
    private CountdownTimer countdownTimer;
    private boolean runable;
    private GameHistoryMaker gameHistory;
    
    @FXML
    private Label firstPlayerLabel;
    @FXML
    private Label secondPlayerLabel;
    @FXML
    private Label roundsLabel;
    @FXML
    private Label firstPlayerClock;
    @FXML
    private Label secondPlayerClock;
    @FXML
    private Label clockLabel;
    @FXML
    private Button firstPlayerBtn1;
    @FXML
    private Button firstPlayerBtn2;
    @FXML
    private Button secondPlayerBtn1;
    @FXML
    private Button secondPlayerBtn2;
    @FXML
    private Button startButton;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    void initData(PlayerDecorator[] players, TextField roundsNumber) {
        this.players = players;
        numberOfRounds = Integer.valueOf(roundsNumber.getText());
        
        currentRound = 1;
        updateRoundLabel(currentRound);
        
        firstPlayerLabel.setText(players[0].getName());
        secondPlayerLabel.setText(players[1].getName());
        
        firstPlayerControlls = new Control[]{
            firstPlayerLabel,
            firstPlayerBtn1,
            firstPlayerBtn2,
            firstPlayerClock
        };
        
        secondPlayerControlls = new Control[]{
            secondPlayerLabel,
            secondPlayerBtn1,
            secondPlayerBtn2,
            secondPlayerClock
        };
        
        gameHistory = new GameHistoryMaker();
        
        disableAllBtn(true);
    }
    
    @FXML
    private void startAction(ActionEvent event){
        startButton.setVisible(false);
        
        countdownTimer = new CountdownTimer();
        playerTurn = 1;
        
        timer = new MainTimer();
        timer.mainClockStart(clockLabel);
        
        disableAllBtn(false);
        gameHistory.appendNewLine("Runda 1:");
        
        runable = true;
        
        setTurn();
    }
    
    @FXML
    private void player1Btn1Action(ActionEvent event){
        players1Choice = 1;
        gameHistory.appendNewLine("Gracz " + players[0].getName() +
                " wybrał orła");
        setTurn();
    }
    
    @FXML
    private void player1Btn2Action(ActionEvent event){
        players1Choice = 2;
        gameHistory.appendNewLine("Gracz " + players[0].getName() +
                " wybrał reszkę");
        setTurn();
    }
    
    @FXML
    private void player2Btn1Action(ActionEvent event){
        players2Choice = 1;
        gameHistory.appendNewLine("Gracz " + players[1].getName() +
                " wybrał orła");
        setTurn();
    }
    
    @FXML
    private void player2Btn2Action(ActionEvent event){
        players2Choice = 2;
        gameHistory.appendNewLine("Gracz " + players[1].getName() +
                " wybrał reszkę");
        setTurn();
    }

    private void updateRoundLabel(int currentRound) {
        if (currentRound > numberOfRounds) {
            timer.stop();
            countdownTimer.stop();
            disableAllBtn(true);
            System.out.print(gameHistory.getGameHistory());
            runable = false;
        }
        else{
            if (currentRound != 1) {
                gameHistory.appendNewLine("Runda " + currentRound + ":");
            }
            
            roundsLabel.setText(StringHelper
                    .roundFormat(currentRound, numberOfRounds));
        }
    }
    
    private void setTurn(){
        if (playerTurn == 1) {
            if (currentRound != 1) {
                //todo sprawdzenie kto wygral
            }
            
            updateRoundLabel(currentRound++);
            
            if(runable){
                playerTurn = 0;

                countdownTimer.reset();

                secondPlayerClock.setText(StringHelper.clockFormat(5));

                for (int i = 0; i < firstPlayerControlls.length; i++) {
                    firstPlayerControlls[i].setDisable(false);
                    secondPlayerControlls[i].setDisable(true);
                }

                countdownTimer.start(firstPlayerClock);
            }
        }
        else{
            playerTurn = 1;
            
            countdownTimer.reset();
            
            firstPlayerClock.setText(StringHelper.clockFormat(5));
            
            for (int i = 0; i < firstPlayerControlls.length; i++) {
                firstPlayerControlls[i].setDisable(true);
                secondPlayerControlls[i].setDisable(false);
            }
            
            countdownTimer.start(secondPlayerClock);
        }
    }
    
    private void disableAllBtn(boolean bool){
        for (int i = 0; i < firstPlayerControlls.length; i++) {
            firstPlayerControlls[i].setDisable(bool);
            secondPlayerControlls[i].setDisable(bool);
        }
    }
}
