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
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import model.players.Player;
import model.players.playerDecorator.PlayerDecorator;
import model.players.playerDecorator.RealPlayerDecorator;

/**
 * FXML Controller class
 *
 * @author Maciek
 */
public class FXMLGameWithoutPaymentSceneController implements Initializable {
    
    private Player player;
    private PlayerDecorator[] players;
    private String gameName;
    
    @FXML
    private ComboBox playersComboBox;
    @FXML
    private TextField roundsNumber;
    @FXML
    private Button playBtn;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    void initData(String gameName, Player player) {
        this.player = player;
        this.gameName = gameName;
        
        ObservableList<String> players =
                FXCollections.observableArrayList(
                        "Graczowi Komputerowemu",
                        "Drugiemu Graczowi"
                );
        playersComboBox.setItems(players);
        
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
    private void playBtnClick(ActionEvent event) throws IOException{

    }
    
    @FXML
    private void goToPreviousSceneAction(ActionEvent event) throws IOException{
        SetScene setter = new SetScene();
        setter.previousScene("/fxml/FXMLMainScene.fxml", event);
    }
    
    private void canExecute() {
        
        boolean error = false;
        String tmpText = roundsNumber.getText();
        
        if(!tmpText.matches("[0-9]+")){
            error = true;
        }
        
        if (playersComboBox.getValue() == null) {
            error = true;
        }
        
        playBtn.setDisable(error);
    }
    
}
