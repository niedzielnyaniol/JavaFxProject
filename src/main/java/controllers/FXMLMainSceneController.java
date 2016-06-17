/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import helpers.AvalibleGamesHelper;
import helpers.StringHelper;
import helpers.SetScene;
import java.io.IOException;
import javafx.scene.input.KeyEvent;
import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import model.players.Player;
import model.players.PlayerSimpleFactory;

/**
 * FXML Controller class
 *
 * @author Maciek
 */
public class FXMLMainSceneController implements Initializable {

    private Player player;
    private int radioButtonSelectedNumber;
    
    @FXML
    private RadioButton preschoolerRadioBtn;   
    @FXML
    private Button nextBtn;
    @FXML
    private ComboBox gamesComboBox;
    @FXML
    private TextField nameTextField;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        preschoolerRadioBtn.setSelected(true);
        nextBtn.setDisable(true);
        gamesComboBox.setItems(AvalibleGamesHelper.forPreschooler());
        radioButtonSelectedNumber = 1;
    }    
    
    @FXML 
    private void preschoolerSelected(ActionEvent event){
        gamesComboBox.setItems(AvalibleGamesHelper.forPreschooler());
        radioButtonSelectedNumber = 1;
        canExecute();
    }
        
    @FXML 
    private void studentSelected(ActionEvent event){
        gamesComboBox.setItems(AvalibleGamesHelper.forStudent());
        radioButtonSelectedNumber = 2;
        canExecute();
    }
        
    @FXML 
    private void collegeStudentSelected(ActionEvent event){
        gamesComboBox.setItems(AvalibleGamesHelper.forCollegeStudent());
        radioButtonSelectedNumber = 3;
        canExecute();
    }
    
    @FXML
    private void nameTextFieldKeyTypedAction(KeyEvent event){
        canExecute();
    }
    
    @FXML
    private void gamesComboBoxSelected(ActionEvent event){
        canExecute();
    }
    
    @FXML
    private void goToNextSceneAction(ActionEvent event) throws IOException{

        PlayerSimpleFactory factory = new PlayerSimpleFactory();
        player = factory.createPlayer(radioButtonSelectedNumber);
        player.setName(nameTextField.getText());
        
        SetScene setter = new SetScene();
        String path = null;
        boolean wageSystem = false;
        
        String gameName = String.valueOf(gamesComboBox.getValue());
        
        boolean game1 = gameName.equals("Gra w wybieranie monety");
        boolean game2 = gameName.equals("Kamień, papier, nożyce");
        boolean game3 = gameName.equals("Gra w jelenie");
        
        if (game1 || game2) {
            path = "/fxml/FXMLGameWithPaymentScene.fxml";
            wageSystem = true;
        }
        else if (game3) {
            path = "/fxml/FXMLGameWithoutPaymentScene.fxml";
            wageSystem = false;
        }
        setter.goToGameInitScene(path, 
                    event, 
                    gameName, 
                    player, 
                    wageSystem);
    }
    
    private void canExecute(){
        boolean condition1 = !StringHelper
                .isNullOrWhitespace(nameTextField.getText());
        boolean condition2 = gamesComboBox.getValue() != null;
        
        if (condition1 && condition2) {
            nextBtn.setDisable(false);
        }
        else{
            nextBtn.setDisable(true);
        }
    }
}
