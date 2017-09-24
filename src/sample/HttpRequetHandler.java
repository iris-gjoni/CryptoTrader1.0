package sample;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by irisg on 23/09/2017.
 */
public class HttpRequetHandler {
    /*
    used only for HTTP GET

    pings  the coinbase API

    getdata will get the crypto price, specified by name
     */

    public String getdata(String name){
        String url = "hi";

        // currently on 6 possible choices for crypto
        if (name.equals("ether")){
            url = "https://api.coinmarketcap.com/v1/ticker/ethereum/";
        } else if (name.equals("bitcoin")){
            url = "https://api.coinmarketcap.com/v1/ticker/bitcoin/";
        } else if (name.equals("vertcoin")){
            url = "https://api.coinmarketcap.com/v1/ticker/vertcoin/";
        } else if (name.equals("litecoin")){
            url = "https://api.coinmarketcap.com/v1/ticker/litecoin/";
        } else if (name.equals("0x")){
            url = "https://api.coinmarketcap.com/v1/ticker/0x/";
        } else if (name.equals("blackmoon-crypto")){
            url = "https://api.coinmarketcap.com/v1/ticker/blackmoon-crypto/";
        } else {
            System.out.println("failed to identify name");
        }

        //once the url has been decided

        StringBuffer response = new StringBuffer();
        try {

            URL ticker = new URL(url);
            HttpsURLConnection connection = (HttpsURLConnection) ticker.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                if(inputLine.contains("price_usd")){
                    response.append("$"+ inputLine.substring(22,inputLine.length() -3)); // gets only the value of the price
                }
            }
            in.close();

        } catch (Exception e){e.printStackTrace();}

        return response.toString();
    }


}
