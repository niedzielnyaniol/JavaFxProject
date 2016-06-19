/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import controllers.FXMLGameController;
import controllers.FXMLGameEndingController;
import controllers.FXMLGameInitController;
import controllers.FXMLTicTacToeSceneController;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.threeGames.GameEngine;
import model.players.Player;
import model.players.playerDecoratorForThreeGames.PlayerDecorator;

/**
 *
 * @author Maciek
 */
public class SetScene {
    public void goToScene(String path, ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(
            getClass().getResource(path));

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(
                new Scene((Pane)loader.load())
        );
        
        stage.show();
    }
    
    public void goToGameScene(
            String path, 
            ActionEvent event, 
            PlayerDecorator[]players,
            int roundsNumber, 
            GameEngine gameEngine) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(
                new Scene((Pane)loader.load())
        );
        
        FXMLGameController controller = 
                loader.<FXMLGameController>getController();
        
        controller.initData(players, roundsNumber, gameEngine);
        
        stage.show();
    }
    
    public void goToGameInitScene(
            String path,
            ActionEvent event,
            String gameName,
            Player player,
            boolean wageSystem) throws IOException{
        
        FXMLLoader loader = new FXMLLoader(
            getClass().getResource(path));

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(
                new Scene((Pane)loader.load())
        );

        FXMLGameInitController controller = 
                loader.<FXMLGameInitController>getController();
        controller.initData(gameName, player, wageSystem);
        
        stage.show();
    }
    
    public void goToEndingScene(
            ActionEvent event,
            String gameHistory) throws IOException{
        
        FXMLLoader loader = new FXMLLoader(
            getClass().getResource("/fxml/FXMLGameHistoryScene.fxml"));

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(
                new Scene((Pane)loader.load())
        );

        FXMLGameEndingController controller = 
                loader.<FXMLGameEndingController>getController();
        controller.initData(gameHistory);
        
        stage.show();
    }
    
    public void goToTicTacToe(ActionEvent event, Player player) throws IOException{
        FXMLLoader loader = new FXMLLoader(
            getClass().getResource("/fxml/FXMLTicTacToeScene.fxml"));

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(
                new Scene((Pane)loader.load())
        );

        FXMLTicTacToeSceneController controller = 
                loader.<FXMLTicTacToeSceneController>getController();
        controller.initData(player);
        
        stage.show();
    }
}
