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

    public String getdata(String url){

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
                    response.append(inputLine.substring(22,inputLine.length() -3)); // gets only the value of the price
                }
            }
            in.close();

        } catch (Exception e){e.printStackTrace();}

        return response.toString();
    }


}
