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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.MainApp;
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
public class FXMLGameWithPaymentSceneController implements Initializable {

    private Player player;
    private PlayerDecorator[] players;
    private String gameName;
    private TextField[] allTextFields;
    
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
    }    

    void initData(String gameName, Player player) {
        this.player = player;
        this.gameName = gameName;
        
        nameLabel.setText(this.player.getName());
        
        ObservableList<String> players =
                FXCollections.observableArrayList(
                        "Gracza Komputerowego",
                        "Drugiego Gracza"
                );
        playersComboBox.setItems(players);
        
        allTextFields = new TextField[]{ 
            firstPlayerCash, 
            firstPlayerPrize, 
            firstPlayerPenalty,
            secondPlayerCash, 
            secondPlayerPrize, 
            secondPlayerPenalty
        };
        
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
        SetScene setter = new SetScene();
        setter.previousScene("/fxml/FXMLMainScene.fxml", event);
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
        
        for (int i = 0; i < allTextFields.length; i++) {
            tmpText = allTextFields[i].getText();
            if(!tmpText.matches("[-+]?[0-9]+")){
                error = true;
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
        
        boolean condition = ((String)playersComboBox.getValue())
                .equals("Gracza Komputerowego");
        
        Player tmpPlayer = null;
        
        if (condition) {
            tmpPlayer = factory.createPlayer(-1);
            tmpPlayer.setName("Gracz Komputerowy");
            players[1] = new AIPlayerDecorator(tmpPlayer);
        }
        else{
            tmpPlayer = factory.createPlayer(-2);
            tmpPlayer.setName("Drugi Gracz");
            players[1] = new RealPlayerDecorator(factory.createPlayer(-2));
        }
        
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

    private void goToNextScene(ActionEvent event) throws IOException {
        if (gameName.equals("Gra w wybieranie monety")) {
            goToHeadAndTails(event);
        }
        else{
            goToRockPaperScissors(event);
        }
    }

    private void goToHeadAndTails(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(
            getClass().getResource(
                "/fxml/FXMLHeadAndTailsScene.fxml"
            )
        );

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(
                new Scene((Pane)loader.load())
        );

        FXMLHeadAndTailsSceneController controller = 
                loader.<FXMLHeadAndTailsSceneController>getController();
        controller.initData(players, roundsNumber);
        
        stage.show();
    }

    private void goToRockPaperScissors(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(
            getClass().getResource(
                "/fxml/FXMLRockPaperScissors.fxml"
            )
        );

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(
                new Scene((Pane)loader.load())
        );

        FXMLRockPaperScissorsController controller = 
                loader.<FXMLRockPaperScissorsController>getController();
        controller.initData(players, roundsNumber);
        
        stage.show();
    }
    
}
