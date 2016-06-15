/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Maciek
 */
public class SetScene {
    public void previousScene(String path, ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(
            getClass().getResource(path));

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(
                new Scene((Pane)loader.load())
        );
        
        stage.show();
    }
}
