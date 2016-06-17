/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.scene.control.Label;

/**
 *
 * @author Maciek
 */
public class MainTimer {
    private Timer timer;
    private int mainTime;
    
    public MainTimer(){
        timer = new Timer();
        mainTime = 0;
    }
    
    public void mainClockStart(final Label clockLabel){
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        clockLabel.setText(
                                StringHelper.clockFormat(mainTime++));
                    }
                });
            }
        }, 0, 1000);
    }
    
    public void stop(){
        timer.cancel();
        timer = null;
    }
    
    public int getTime(){
        return mainTime;
    }
}
