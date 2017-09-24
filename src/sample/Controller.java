package sample;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.DecimalFormat;


public class Controller {

    /*
    huge code in here, instantiate the page, along with all conponents
    creates the logos for each of the 6 cryptos
    add event handlers for the buttons
    grid pane used to organise
     */
    Controller(Stage primaryStage) throws Exception{
        Stage screen = new Stage();
        screen.initModality(Modality.APPLICATION_MODAL);
        screen.setTitle("hello");

        Text title= new Text("Crypto Trader") ;
        title.getStyleClass().add("title");
        title.setTextAlignment(TextAlignment.CENTER);

        // t<name> holds the tokens
        TextField tether = new TextField();
        tether.getStyleClass().add("text2");
        TextField tbit = new TextField();
        tbit.getStyleClass().add("text2");
        TextField tvert = new TextField();
        tvert.getStyleClass().add("text2");
        TextField t0x = new TextField();
        t0x.getStyleClass().add("text2");
        TextField tlite = new TextField();
        tlite.getStyleClass().add("text2");
        TextField tbmoon = new TextField();
        tbmoon.getStyleClass().add("text2");

        // P<name> holds the price
        Text pether = new Text();
        pether.getStyleClass().add("text2");
        Text pbit = new Text();
        pbit.getStyleClass().add("text2");
        Text pvert = new Text();
        pvert.getStyleClass().add("text2");
        Text p0x = new Text();
        p0x.getStyleClass().add("text2");
        Text plite = new Text();
        plite.getStyleClass().add("text2");
        Text pbmoon = new Text();
        pbmoon.getStyleClass().add("text2");

        // b<name> holds the Balance
        Text bether = new Text();
        bether.getStyleClass().add("text2");
        Text bbit = new Text();
        bbit.getStyleClass().add("text2");
        Text bvert = new Text();
        bvert.getStyleClass().add("text2");
        Text b0x = new Text();
        b0x.getStyleClass().add("text2");
        Text blite = new Text();
        blite.getStyleClass().add("text2");
        Text bbmoon = new Text();
        bbmoon.getStyleClass().add("text2");

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


        // l<name> holds the label
        Label lether = new Label("ether: ");
        lether.getStyleClass().add("text1");
        Label lbit = new Label("bitcoin: ");
        lbit.getStyleClass().add("text1");
        Label llite = new Label("litecoin: ");
        llite.getStyleClass().add("text1");
        Label lvert = new Label("vertcoin: ");
        lvert.getStyleClass().add("text1");
        Label l0x = new Label("0x: ");
        l0x.getStyleClass().add("text1");
        Label lbmoon = new Label("blackmoon: ");
        lbmoon.getStyleClass().add("text1");

        Label ltoken = new Label("tokens:");
        ltoken.getStyleClass().add("text1");
        Label lprice = new Label("price:");
        lprice.getStyleClass().add("text1");
        Label lbalance = new Label("balance:");
        lbalance.getStyleClass().add("text1");
        Label total = new Label("Total:");
        total.getStyleClass().add("text1");
        Text amount = new Text();
        amount.setFill(Color.valueOf("#008000"));
        amount.setFont(Font.font("impact", 34));

        //--------------------------                         images used in project

        Image eth = new Image("ether.png");
        ImageView iether = new ImageView();
        iether.setImage(eth);
        iether.setFitHeight(40);
        iether.setFitWidth(40);

        Image bit = new Image("bitcoin.png");
        ImageView ibit = new ImageView();
        ibit.setImage(bit);
        ibit.setFitHeight(40);
        ibit.setFitWidth(40);

        Image lite = new Image("litecoin.png");
        ImageView ilite = new ImageView();
        ilite.setImage(lite);
        ilite.setFitHeight(40);
        ilite.setFitWidth(40);

        Image vert = new Image("vertcoin2.png");
        ImageView ivert = new ImageView();
        ivert.setImage(vert);
        ivert.setFitHeight(50);
        ivert.setFitWidth(50);

        Image _0x = new Image("0x.png");
        ImageView i0x = new ImageView();
        i0x.setImage(_0x);
        i0x.setFitHeight(40);
        i0x.setFitWidth(40);

        Image bmn = new Image("blackmoon-crypto.png");
        ImageView ibmn = new ImageView();
        ibmn.setImage(bmn);
        ibmn.setFitHeight(40);
        ibmn.setFitWidth(40);


        //----------------------                         event handlers begin here

        refresh.setOnAction(e ->{

            //ping APIs
            pether.setText(sendRequest("ether"));
            pbit.setText(sendRequest("bitcoin"));
            plite.setText(sendRequest("litecoin"));
            pvert.setText(sendRequest("vertcoin"));
            p0x.setText(sendRequest("0x"));
            pbmoon.setText(sendRequest("blackmoon-crypto"));

            float currenttotal = 0;
            //calculate balances

            String token = tether.getText().toString();
            String price = pether.getText().toString();

            if (isFloat(token) && !token.equals("")) {
                float num = Float.valueOf(token);
                float num2 = Float.valueOf(price.substring(1,price.length()));
                currenttotal += calcBalance(num, num2);
                bether.setText("$" +String.valueOf(calcBalance(num, num2)));
            }

             token = tbit.getText().toString();
             price = pbit.getText().toString();

            if (isFloat(token) && !token.equals("")) {
                float num = Float.valueOf(token);
                float num2 = Float.valueOf(price.substring(1,price.length()));
                currenttotal += calcBalance(num, num2);
                bbit.setText("$" +String.valueOf(calcBalance(num, num2)));
            }

             token = tlite.getText().toString();
             price = plite.getText().toString();

            if (isFloat(token) && !token.equals("")) {
                float num = Float.valueOf(token);
                float num2 = Float.valueOf(price.substring(1,price.length()));
                currenttotal += calcBalance(num, num2);
                blite.setText("$" +String.valueOf(calcBalance(num, num2)));
            }

             token = tvert.getText().toString();
             price = pvert.getText().toString();

            if (isFloat(token) && !token.equals("")) {
                float num = Float.valueOf(token);
                float num2 = Float.valueOf(price.substring(1,price.length()));
                currenttotal += calcBalance(num, num2);
                bvert.setText("$" +String.valueOf(calcBalance(num, num2)));
            }

             token = t0x.getText().toString();
             price = p0x.getText().toString();

            if (isFloat(token) && !token.equals("")) {
                float num = Float.valueOf(token);
                float num2 = Float.valueOf(price.substring(1,price.length()));
                currenttotal += calcBalance(num, num2);
                b0x.setText("$" +String.valueOf(calcBalance(num, num2)));
            }

             token = tbmoon.getText().toString();
             price = pbmoon.getText().toString();

            if (isFloat(token) && !token.equals("")) {
                float num = Float.valueOf(token);
                float num2 = Float.valueOf(price.substring(1,price.length()));
                currenttotal += calcBalance(num, num2);
                bbmoon.setText("$" + String.valueOf(calcBalance(num, num2)));
            }

            DecimalFormat d = new DecimalFormat(".##");
            amount.setText("$" + d.format(currenttotal));

        });


        load.setOnAction(e ->{
            String[] data;
            /*
            at the moment only loads my pre-set
            Eventually need a new pop up screen with a drop down list of stored pe-sets
            may even need a delete feature...
             */

            try {
                 data = loadDetails("iris").split(",");
                if (!data.equals("fail")) {
                    tbit.setText(data[0]);
                    tether.setText(data[1]);
                    tlite.setText(data[2]);
                    tvert.setText(data[3]);
                    t0x.setText(data[4]);
                    tbmoon.setText(data[5]);
                } else{
                    System.out.println("failed to load data");
                }
            } catch (Exception e2) {e2.printStackTrace();}
        });

        GridPane g = new GridPane(); // grid pane gives an easy to organise structure
        //add all the components
        g.setHgap(10);
        g.setVgap(10);
        g.setPadding(new Insets(10, 10, 10, 10));

        g.add(title, 0, 0, 8, 1);
        g.add(refresh, 8, 1);
        g.add(load, 0, 1);
        g.add(save, 0, 2);

        g.add(tbit, 2, 3);
        g.add(tether, 3, 3);
        g.add(tlite, 4, 3);
        g.add(tvert, 5, 3);
        g.add(t0x, 6, 3);
        g.add(tbmoon, 7, 3);
        g.add(pbit, 2, 4);
        g.add(pether, 3, 4);
        g.add(plite, 4, 4);
        g.add(pvert, 5, 4);
        g.add(p0x, 6, 4);
        g.add(pbmoon, 7, 4);
        g.add(bbit, 2, 5);
        g.add(bether, 3, 5);
        g.add(blite, 4, 5);
        g.add(bvert, 5, 5);
        g.add(b0x, 6, 5);
        g.add(bbmoon, 7, 5);
        g.add(total, 2, 6);
        g.add(amount, 3, 6);

        g.add(ibit, 2,2);
        g.add(iether, 3,2);
        g.add(ilite, 4,2);
        g.add(ivert, 5,2);
        g.add(i0x, 6,2);
        g.add(ibmn, 7,2);

        g.add(lbit, 2, 1);
        g.add(lether, 3, 1);
        g.add(llite, 4, 1);
        g.add(lvert, 5, 1);
        g.add(l0x, 6, 1);
        g.add(lbmoon, 7, 1);

        g.add(ltoken, 1, 3);
        g.add(lprice, 1, 4);
        g.add(lbalance, 1, 5);

        ColumnConstraints column = new ColumnConstraints(70);
        g.getColumnConstraints().add(column);
        g.getColumnConstraints().add(column);
        g.getColumnConstraints().add(column);
        g.getColumnConstraints().add(column);
        g.getColumnConstraints().add(column);
        g.getColumnConstraints().add(column);
        g.getColumnConstraints().add(column);
        g.getColumnConstraints().add(column);

        RowConstraints row = new RowConstraints(40);
        g.getRowConstraints().add(row);
        g.getRowConstraints().add(row);
        g.getRowConstraints().add(row);
        g.getRowConstraints().add(row);
        g.getRowConstraints().add(row);
        g.getRowConstraints().add(row);

        g.setHgap(20);
        g.setVgap(20);

//        g.setGridLinesVisible(true);
        Scene scene = new Scene(g, 850,600);
//        scene.getStylesheets().add(this.getClass().getResource("resources\\DarkTheme.css").toExternalForm()); // uses the css sheet
        scene.getStylesheets().add("DarkTheme.css");
        screen.setScene(scene);
        screen.showAndWait();
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

        String irisDetails = "resources\\loadUser";
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


}
