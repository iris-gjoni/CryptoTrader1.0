package sample;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import javax.jws.soap.SOAPBinding;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.security.CryptoPrimitive;
import java.text.DecimalFormat;
import java.util.ArrayList;


public class Controller {

    private ArrayList<CoinClass> UserCoins = new ArrayList<>();
    private GridPane g;
    private static int selected =0;
    private Text amount;
    private TextField atoken;
    private Label aprice;
    private Scene scene;

    /*
    huge code in here, instantiate the page, along with all conponents
    creates the logos for each of the 6 cryptos
    add event handlers for the buttons
    grid pane used to organise

    1. implement portfolio

    2. implement load button, save button
     */

    Controller(Stage primaryStage) throws Exception{
        Stage screen = new Stage();
        screen.setResizable(true);
        screen.setTitle("Crypto Trader");
        initialise();

        Text title= new Text("Crypto Balance Calculator") ;
        title.getStyleClass().add("title");
        title.setTextAlignment(TextAlignment.CENTER);

        Button refresh = new Button("refresh"); // used to ping the coin base API, generate USD balance
        refresh.getStyleClass().add("button1");
        refresh.setPrefWidth(70);
        refresh.setPrefHeight(40);

        Button load = new Button("load"); // used to load token balances
        load.getStyleClass().add("button1");
        load.setPrefWidth(70);
        load.setPrefHeight(40);

        Button save = new Button("save"); // will be used to save the aounts entered in the token balaces
        save.getStyleClass().add("button1");
        save.setPrefWidth(70);
        save.setPrefHeight(40);

        Label ltoken = new Label("tokens:");
        ltoken.getStyleClass().add("text1");

        Label lprice = new Label("price:");
        lprice.getStyleClass().add("text1");

        Label total = new Label("Total:");
        total.getStyleClass().add("text1");

        amount = new Text();
        amount.setFill(Color.valueOf("#008000"));
        amount.setFont(Font.font("impact", 34));

        ListView cryptoList = new ListView();
        cryptoList.setPrefWidth(60);
        cryptoList.getItems().add("your porfolio");
        for ( int i = 0; i < UserCoins.size(); i++) {
            cryptoList.getItems().add(UserCoins.get(i).getName());
        }

        ImageView coinpic = new ImageView();
        Image image = new Image("bitcoin.png");
        coinpic.setImage(image);
        coinpic.setFitHeight(90);
        coinpic.setFitWidth(90);

        atoken  = new TextField("");
        atoken.getStyleClass().add("text1");
        atoken.setPrefWidth(80);

        aprice = new Label("");
        aprice.getStyleClass().add("text1");

        //----------------------                         event handlers begin here

        refresh.setOnAction(e ->{
            //ping APIs
            refreshPrice();
        });

        cryptoList.setOnMouseClicked(e -> {
            /*
            used to load the coin selected y the user from the list view
             */
            //get int index of the current
            if(cryptoList.getSelectionModel().getSelectedIndex() != 0) {
                selected = Integer.valueOf(cryptoList.getSelectionModel().getSelectedIndex());
                selected -= 1;

                UserCoins.get(selected).generateImage();
                coinpic.setImage(UserCoins.get(selected).getImage());
                refreshPrice();


            } else {
                // code to handle the user portfolio
            }

        });

        g = new GridPane(); // grid pane gives an easy to organise structure
        //add all the components
        g.setPadding(new Insets(10, 10, 10, 10));

        g.add(title, 0, 0, 8, 1);
        g.add(refresh, 3, 7);
        g.add(load, 0, 1);
        g.add(save, 0, 7);
        g.add(coinpic, 2, 1);

        g.add(total, 2, 5);
        g.add(amount, 3, 5);
        g.add(aprice, 3 ,4);
        g.add(atoken, 3, 3);
        g.add(ltoken, 2, 3);
        g.add(lprice, 2, 4);
        g.add(cryptoList, 0,2,1,5);

        g.setHgap(40);
        g.setVgap(30);

        g.getColumnConstraints().add(new ColumnConstraints(100));
        g.getColumnConstraints().add(new ColumnConstraints(50));
        g.getColumnConstraints().add(new ColumnConstraints(100));
        g.getColumnConstraints().add(new ColumnConstraints(100));

        g.getRowConstraints().add(new RowConstraints(50));
        g.getRowConstraints().add(new RowConstraints(50));
        g.getRowConstraints().add(new RowConstraints(50));
        g.getRowConstraints().add(new RowConstraints(50));
        g.getRowConstraints().add(new RowConstraints(50));
        g.getRowConstraints().add(new RowConstraints(50));
        g.getRowConstraints().add(new RowConstraints(50));
        g.getRowConstraints().add(new RowConstraints(50));

//        g.setGridLinesVisible(true);
        scene = new Scene(g, 700,650);
        scene.getStylesheets().add("DarkTheme.css");
        screen.setScene(scene);
        screen.show();
    }

    //calls the HttpRequestHandler to ping the API
    private String sendRequest(String name){
        HttpRequetHandler handler = new HttpRequetHandler();
        String response = handler.getdata(name);
        return response;
    }

    //looks in the rescouce folder -> LoadUser file to see if the name searched for is there
    private String loadDetails(String name) throws Exception {

        //name not implemented yet

        String irisDetails = "resources\\loadUser.txt";
        File userDetails = new File(irisDetails);

        BufferedReader reader = new BufferedReader(new FileReader(userDetails));
        String line;
        boolean found= false;
        while((line = reader.readLine()) != null){
            if (found) {
                return line;
            }
            if (line.equals("User: Iris")){
                found = true;
            }

        }
        return "fail";
    }

    public void saveDetails(){

    }

    //returns a 2dp float
    private float calcBalance(float price, float tokens){
        DecimalFormat df2 = new DecimalFormat(".##");
        float temp = Float.valueOf(df2.format(price * tokens));
        return temp;
    }

    //validate is the String read from the text is actually a float
    private boolean isFloat(String num) {
        char[] num2 = num.toCharArray();
        boolean dotused = false;

        for (int i = 0; i < num.length(); i++) {
            switch (num2[i]) {
                case '0': {
                    break;
                }
                case '1': {
                    break;
                }
                case '2': {
                    break;
                }
                case '3': {
                    break;
                }
                case '4': {
                    break;
                }
                case '5': {
                    break;
                }
                case '6': {
                    break;
                }
                case '7': {
                    break;
                }
                case '8': {
                    break;
                }
                case '9': {
                    break;
                }
                case '.': {
                    if (dotused) {
                        return false; // only one allowed
                    }
                    dotused = true;
                    break;
                }
                default: {
                    return false;
                }
            }
        }
        return true;
    }

    private void refreshPrice(){
        UserCoins.get(selected).setPrice(Float.valueOf(sendRequest(UserCoins.get(selected).getUrl())));
        float currenttotal = UserCoins.get(selected).getPrice();
        aprice.setText("$" + currenttotal);

        String stringtoken = atoken.getText().toString();
        String stringprice = aprice.getText().toString();
        if(isFloat(stringtoken) && !stringtoken.equals("")) {
            float money = calcBalance(Float.valueOf(stringprice.substring(1,stringprice.length())), Float.valueOf(stringtoken));
            amount.setText("$" + String.valueOf(money));
            UserCoins.get(selected).setBalance(money); // to be used in wallet later
            UserCoins.get(selected).setTokens(Float.valueOf(stringtoken)); // to be used in wallet later
        }
    }

    private void initialise(){
        UserCoins.add(new BitCoinClass());
        UserCoins.add(new EthereumClass());
        UserCoins.add(new LiteCoinClass());
        UserCoins.add(new VertCoinClass());
        UserCoins.add(new _0xCoinClass());
        UserCoins.add(new BlackMoonClass());

    }

}
