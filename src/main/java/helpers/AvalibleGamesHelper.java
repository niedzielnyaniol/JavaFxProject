/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Maciek
 */
public class AvalibleGamesHelper {
    
    public static ObservableList<String> forPreschooler(){
        ObservableList<String> games =
                FXCollections.observableArrayList(
                        "Gra w wybieranie monety"
                );
        return games;
    }
    
    public static ObservableList<String> forStudent(){
        ObservableList<String> games =
                FXCollections.observableArrayList(
                        "Gra w wybieranie monety",
                        "Kamień, papier, nożyce"
                );
        return games;
    }
        
    public static ObservableList<String> forCollegeStudent(){
        ObservableList<String> games =
                FXCollections.observableArrayList(
                        "Gra w wybieranie monety",
                        "Kamień, papier, nożyce",
                        "Gra w jelenie"
                );
        return games;
    }
    
}
