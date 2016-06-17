/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import helpers.SetScene;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import model.games.DeerHuntingGameEngine;
import model.games.GameEngine;
import model.games.HeadAndTailsGameEngine;
import model.games.RockPaperScisorsGameEngine;
import model.players.Player;
import model.players.PlayerSimpleFactory;
import model.players.playerDecorator.AIPlayerDecorator;
import model.players.playerDecorator.PlayerDecorator;
import model.players.playerDecorator.RealPlayerDecorator;

/**
 * FXML Controller class
 *
 * @author Maciek
 */
public class FXMLGameInitController implements Initializable {

    private Player player;
    private PlayerDecorator[] players;
    private String gameName;
    private TextField[] allTextFields;
    private boolean wageSystem;
    private SetScene setter;
    
    @FXML
    private Label nameLabel;
    @FXML
    private ComboBox playersComboBox;
    @FXML
    private TextField firstPlayerCash;
    @FXML
    private TextField firstPlayerPrize;
    @FXML
    private TextField firstPlayerPenalty;
    @FXML
    private TextField secondPlayerCash;
    @FXML
    private TextField secondPlayerPrize;
    @FXML
    private TextField secondPlayerPenalty;
    @FXML
    private TextField roundsNumber;
    @FXML
    private Button playBtn;
            
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setter = new SetScene();
        
        allTextFields = new TextField[]{ 
            firstPlayerCash, 
            firstPlayerPrize, 
            firstPlayerPenalty,
            secondPlayerCash, 
            secondPlayerPrize, 
            secondPlayerPenalty
        };
        
        ObservableList<String> players =
                FXCollections.observableArrayList(
                        "Gracz Komputerowy",
                        "Drugi gracz"
                );
        playersComboBox.setItems(players);
    }    

    public void initData(String gameName, Player player, boolean wageSystem) {
        this.player = player;
        this.gameName = gameName;
        this.wageSystem = wageSystem;
        
        if(wageSystem){
            nameLabel.setText(this.player.getName());
        }
        canExecute();
    }
    
    @FXML
    private void isBadValue(ActionEvent event){
        canExecute();
    }
    
    @FXML
    private void isBadText(KeyEvent event){
        canExecute();
    }
    
    @FXML
    private void goToPreviousSceneAction(ActionEvent event) throws IOException{
        setter.goToScene("/fxml/FXMLMainScene.fxml", event);
    }
    
    @FXML
    private void playBtnClick(ActionEvent event) throws IOException{
        
        RealPlayerDecorator firstPlayer = new RealPlayerDecorator(player);
        PlayerDecorator secondPlayer = null;
        
        players = new PlayerDecorator[]{
            firstPlayer,
            secondPlayer
        };
        
        playersInit();
        goToNextScene(event);
    }
    
    private void canExecute(){
        String tmpText;
        boolean error = false;
        
        if (wageSystem) {
            for (int i = 0; i < allTextFields.length; i++) {
                tmpText = allTextFields[i].getText();
                if(!tmpText.matches("[-+]?[0-9]+")){
                    error = true;
                }
            }
        }
        
        tmpText = roundsNumber.getText();
        if(!tmpText.matches("[0-9]+")){
            error = true;
        }
        
        if (playersComboBox.getValue() == null) {
            error = true;
        }
        
        playBtn.setDisable(error);
    }

    private void playersInit() {
        PlayerSimpleFactory factory = new PlayerSimpleFactory();
        
        boolean computerPlayer = ((String)playersComboBox.getValue())
                .equals("Gracza Komputerowego");
        
        Player tmpPlayer = null;
        
        if (computerPlayer) {
            tmpPlayer = factory.createPlayer(-1);
            tmpPlayer.setName("Gracz Komputerowy");
            players[1] = new AIPlayerDecorator(tmpPlayer);
        }
        else{
            tmpPlayer = factory.createPlayer(-2);
            tmpPlayer.setName("Drugi Gracz");
            players[1] = new RealPlayerDecorator(tmpPlayer);
        }
        
        if (wageSystem) {
            initWageSystem();
        }
        else{
            standardWageSystem();
        }
    }

    private void goToNextScene(ActionEvent event) throws IOException {
        
        GameEngine gameEngine = null;
        String path = null;
        int tmpRoundsNumber = Integer.valueOf(roundsNumber.getText());
        
        if (gameName.equals("Gra w wybieranie monety")) {
            gameEngine = new HeadAndTailsGameEngine();
            path = "/fxml/FXMLHeadAndTailsScene.fxml";
        }
        else if (gameName.equals("Kamień, papier, nożyce")){
            gameEngine = new RockPaperScisorsGameEngine();
            path = "/fxml/FXMLRockPaperScissors.fxml";
        }
        else if (gameName.equals("Gra w jelenie")){
            gameEngine = new DeerHuntingGameEngine();
            path = "/fxml/FXMLDeerHuntingScene.fxml";
        }
        
        setter.goToGameScene(path, 
                    event, 
                    players, 
                    tmpRoundsNumber, 
                    gameEngine);
    }

    private void initWageSystem() {
        int[] tmpValues = new int[6];
        
        for (int i = 0; i < allTextFields.length; i++) {
            tmpValues[i] = Integer.valueOf(allTextFields[i].getText());
        }        
        
        for (int i = 0, j = 0; i < players.length; i++) {
            players[i].setCash(tmpValues[j++]);
            players[i].setPrize(tmpValues[j++]);
            players[i].setPenalty(tmpValues[j++]);
        }
    }

    private void standardWageSystem() {
        for (int i = 0, j = 0; i < players.length; i++) {
            players[i].setCash(0);
            players[i].setPrize(0);
            players[i].setPenalty(0);
        }
    }
        
    
}
