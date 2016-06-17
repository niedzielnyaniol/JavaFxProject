package controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import helpers.SetScene;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author Maciek
 */
public class FXMLGameEndingController implements Initializable {

    @FXML
    private TextArea gameHistoryArea;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void initData (String gameHistory){
        gameHistoryArea.setText(gameHistory);
    }
    
    @FXML
    private void newGame(ActionEvent event) throws IOException{
        SetScene setter = new SetScene();
        setter.goToScene("/fxml/FXMLMainScene.fxml", event);
    }
    
    @FXML
    private void closeAction(ActionEvent event){
        System.exit(0);
    }
}
