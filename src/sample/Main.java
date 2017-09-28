package sample;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    /*
    TO DO

    1. get the symbols for each of the crypto - done

    2. make a CSS to give it a nice theme - done

    4. convert all coins into classes - partially complete

    5. redo interface - add list view

    6. show total balance in multiple currencies

    7. generate graphs and auto refresh

    8. re-do load button and save button - perhaps XML storage

    9. implement user profile page

    10. web deployment
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
