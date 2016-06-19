/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import helpers.CountdownTimer;
import helpers.GameHistoryMaker;
import helpers.MainTimer;
import helpers.SetScene;
import helpers.StringHelper;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import model.threeGames.GameEngine;
import model.threeGames.Winner;
import model.players.playerDecoratorForThreeGames.AIPlayerDecorator;
import model.players.playerDecoratorForThreeGames.PlayerDecorator;
import model.players.playerDecoratorForThreeGames.RealPlayerDecorator;

/**
 * FXML Controller class
 *
 * @author Maciek
 */
public class FXMLGameController implements Initializable {

    private PlayerDecorator[] players;
    private GameEngine gameEngine; 
    
    private Control[] firstPlayerControlls;
    private Control[] secondPlayerControlls;
    private int numberOfRounds;
    private int currentRound;
    private int playerTurn;
    private MainTimer mainTimer;
    private CountdownTimer countdownTimer;
    private boolean runable;
    private GameHistoryMaker gameHistory;
    private Random rand;
    
    @FXML Label firstPlayerLabel;
    @FXML Label secondPlayerLabel;
    @FXML Label roundsLabel;
    @FXML Label firstPlayerClock;
    @FXML Label secondPlayerClock;
    @FXML Label clockLabel;
    @FXML Button firstPlayerBtn1;
    @FXML Button firstPlayerBtn2;
    @FXML Button firstPlayerBtn3;
    @FXML Button secondPlayerBtn1;
    @FXML Button secondPlayerBtn2;
    @FXML Button secondPlayerBtn3;
    @FXML Button startButton;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        firstPlayerControlls = new Control[]{
            firstPlayerLabel,
            firstPlayerBtn1,
            firstPlayerBtn2,
            firstPlayerBtn3,
            firstPlayerClock
        };
        
        secondPlayerControlls = new Control[]{
            secondPlayerLabel,
            secondPlayerBtn1,
            secondPlayerBtn2,
            secondPlayerBtn3,
            secondPlayerClock
        };
        
        gameHistory = new GameHistoryMaker();
        
        rand = new Random();
        
        disableAllBtn(true);
    }    

    public void initData(PlayerDecorator[] players, int roundsNumber
            , GameEngine gameEngine) {
        
        this.players = players;
        this.gameEngine = gameEngine;
        numberOfRounds = roundsNumber;
        
        currentRound = 1;
        updateRoundLabel(currentRound);
        
        firstPlayerLabel.setText(players[0].getName());
        secondPlayerLabel.setText(players[1].getName());
    }
    
    @FXML
    private void startAction(ActionEvent event) throws IOException{
        startButton.setVisible(false);
        
        countdownTimer = new CountdownTimer();
        
        playerTurn = 1;
        
        mainTimer = new MainTimer();
        mainTimer.mainClockStart(clockLabel);
        
        disableAllBtn(false);
        
        gameHistory.appendNewLine("Runda 1:");
        
        runable = true;
        
        if (players[1] instanceof AIPlayerDecorator) {
            for (int i = 0; i < firstPlayerControlls.length; i++) {
                firstPlayerControlls[i].setDisable(false);
                secondPlayerControlls[i].setDisable(true);
            }
        }
        
        setTurn(event);
        
    }
    
    @FXML
    private void player1Btn1Action(ActionEvent event) throws IOException{
        setChoice(event, 0, 0);
    }
    
    @FXML
    private void player1Btn2Action(ActionEvent event) throws IOException{
        setChoice(event, 0, 1);
    }
    
    @FXML
    private void player1Btn3Action(ActionEvent event) throws IOException{
        setChoice(event, 0, 2);
    }
    
    @FXML
    private void player2Btn1Action(ActionEvent event) throws IOException{
        setChoice(event, 1, 0);
    }
    
    @FXML
    private void player2Btn2Action(ActionEvent event) throws IOException{
        setChoice(event, 1, 1);
    }
        
    @FXML
    private void player2Btn3Action(ActionEvent event) throws IOException{
        setChoice(event, 1, 2);
    }

    private void updateRoundLabel(int currentRound) {
        if (currentRound > numberOfRounds) {
            endTheGame();
        }
        else{
            if (currentRound != 1) {
                gameHistory.appendNewLine("Runda " + currentRound + ":");
            }
            
            roundsLabel.setText(StringHelper
                    .roundFormat(currentRound, numberOfRounds)
            );
        }
    }
    
    private void setChoice(ActionEvent event, int playerNumber, int choiceNumber) throws IOException{
        players[playerNumber].setChoice(choiceNumber);
        
        gameHistory.appendNewLine(
                gameEngine.valueConvert(
                        players[playerNumber]
                )
        );
        
        setTurn(event);
    }
    
    private void setTurn(ActionEvent event) throws IOException{
        
        if (playerTurn == 1) {
            
            if (currentRound != 1) {
                gameHistory.appendNewLine(
                        gameEngine.check(players)
                );
            }
            
            updateRoundLabel(currentRound++);
            
            if(runable){
                playerOneScenario(event);
            }
            else{             
                endGame(event);
            }
        }
        else{
            playerTurn = 1;
            
            checkCountDownTimer(event, players[1]);
            
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

    private void endGame(ActionEvent event) throws IOException {
        SetScene setter = new SetScene();
        setter.goToEndingScene(event, gameHistory.getGameHistory());
    }

    private void checkCountDownTimer(ActionEvent event, PlayerDecorator player) throws IOException {
        try{
            countdownTimer.reset();
        }catch(RuntimeException ee){
            
            gameHistory.appendNewLine("-------------------------------");
            gameHistory.appendNewLine("Gra zakończona, wygrywa " 
                    + player.getName() + "\nprzeciwnikowi skończył się czas.");
            
            gameHistory.appendNewLine("-------------------------------");
            
            endGame(event);
        }
    }

    private void endTheGame() {
        mainTimer.stop();
        countdownTimer.stop();

        gameHistory.appendNewLine(
                Winner.getWinner(players)
        );

        gameHistory.appendNewLine("Gra trwała " 
                + StringHelper.clockFormat(mainTimer.getTime())
                + "mm:ss");

        disableAllBtn(true);

        runable = false;
    }

    private void playerOneScenario(ActionEvent event) throws IOException {
        
        if(players[1] instanceof RealPlayerDecorator){
            playerTurn = 0;
            
            checkCountDownTimer(event, players[0]);
            secondPlayerClock.setText(StringHelper.clockFormat(5));

            for (int i = 0; i < firstPlayerControlls.length; i++) {
                firstPlayerControlls[i].setDisable(false);
                secondPlayerControlls[i].setDisable(true);
            }
        }
        else{ //instance of AIPlayerDecorator
            
            checkCountDownTimer(event, players[1]);

            int choice = rand.nextInt(gameEngine.
                    getNumberOfChoicePossibilities());
            players[1].setChoice(choice);

            gameHistory.appendNewLine(
                gameEngine.valueConvert(
                        players[1]
                )
            );
        }

        countdownTimer.start(firstPlayerClock);        
    }
}
