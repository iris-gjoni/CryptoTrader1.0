package sample;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    /*
    TO DO

    1. get the symbols for each of the crypto

    2. make a CSS to make it look similar to GDAX

    3. look at expansibility
     */

    @Override
    public void start(Stage primaryStage) throws Exception{

        //only call the controller constructor which will create the page, and all the event handlers
        Controller con = new Controller(primaryStage);


    }

    public static void main(String[] args) {
        launch(args);
    }
}
