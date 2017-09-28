package sample;

/**
 * Created by irisg on 27/09/2017.
 */
public class LiteCoinClass extends CoinClass{


    LiteCoinClass(){
        this.setName("litecoin");
        this.setImagePath("litecoin.png");
        this.setUrl("https://api.coinmarketcap.com/v1/ticker/litecoin/");
    }
}
