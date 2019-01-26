
package ch30raiseflags;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/***********************************************************************
 * Project: Raise Flags
 * Task: Creates an animation of a flag being raised using a thread and 
 *      sleep() method
 * @author Justin Mangan
 * Date: 23 April 2018
 ***********************************************************************/
public class FlagPole extends Application {
    
    private boolean raising = false;
    private double x = 0;
    private double y = 578;
    private ImageView flag = new ImageView("images/starry_plow.png");
    private ImageView pole = new ImageView("images/pole.png");
    private Button pully = new Button();
    private Pane pane = new Pane();
    private Scene scene = new Scene(pane, 500, 700);
    
    /**
     * Create Scene and add it to Stage
     * @param primaryStage 
     */
    @Override
    public void start(Stage primaryStage){
        pane.setBackground(new Background(new BackgroundFill(Color.SKYBLUE, 
                CornerRadii.EMPTY, Insets.EMPTY)));
        pane.getChildren().add(pole);
        pole.setX(x + 220);
        pane.getChildren().add(flag);
        flag.setX(x + 236);
        flag.setY(y);
        pane.getChildren().add(pully);
        pully.setLayoutX(x + scene.getWidth() / 5.5);
        pully.setLayoutY(550);
        pully.setText("RAISE");
        
        primaryStage.setTitle("Raise Flags");
        primaryStage.setScene(scene);
        primaryStage.show();   
        
        //Thread controls the animation
        new Thread(() -> {
            try{
                while(true){
                    Platform.runLater(() -> {
                        if(y > 22 && raising){
                            y -= 0.5;
                            flag.setY(y);              
                        }
                        if(y < 578 && !raising){
                            y += 0.5;
                            flag.setY(y); 
                        }              
                    });
                    
                    Thread.sleep(10);  
                }
            }
            catch(InterruptedException ex){
                
            }
        }).start();
        
        /**
         * Action handler for button
         */
        pully.setOnAction((e) -> {
                raising = !raising;
                pully.setText(raising ? "LOWER" : "RAISE");
        });
        
        /**
         * Shuts down app on window close
         */
        primaryStage.setOnCloseRequest(e -> {
                Platform.exit();
                System.exit(0);
        });

    }
    
    /**
     * Main class
     * @param args 
     */
    public static void main(String[] args){
        launch();
    }
}

