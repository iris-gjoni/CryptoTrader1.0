package sample;


/**
 * Created by irisg on 26/09/2017.
 */
public class BitCoinClass extends CoinClass{

    BitCoinClass() {
        this.setName("bitcoin");
        this.setUrl("https://api.coinmarketcap.com/v1/ticker/bitcoin/");
        this.setImagePath("bitcoin.png");
        //load tokens from file
        //price ffor refresh button
        //store balance fro efficiency

    }



}
