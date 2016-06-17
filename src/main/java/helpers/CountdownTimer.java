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
public class CountdownTimer {
    private Timer timer;
    private int mainTime;
    
    public CountdownTimer(){
        timer = new Timer();
        mainTime = 5;
    }
    
    public void start(final Label clockLabel){
        if (timer != null) {
            mainTime = 5;
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            if(mainTime >= 0){
                                clockLabel.setText(
                                    StringHelper.clockFormat(mainTime--));
                            }
                            else{
                                clockLabel.setText("Koniec");
                                stop();
                            }
                        }
                    });
                }
            }, 0, 1000);
        }
    }
    
    public void reset(){
        timer.cancel();
        timer = null;
        timer = new Timer();
    }
    
    
    public void stop(){
        timer.cancel();
        timer = null;
    }
}
