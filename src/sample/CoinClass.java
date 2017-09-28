package sample;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Created by irisg on 26/09/2017.
 */
public abstract class CoinClass {

    private String name;
    private String url;         //for GET http
    private float price;
    private float tokens;       //efficiency
    private float balance;      //efficiency
    private Image image;
    private String imagePath;

    private boolean Used;


    public void generateImage(){
        Image bit = new Image(this.getImagePath());
        this.setImage(bit);
    }


    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public boolean isUsed() {
        return Used;
    }

    public void setUsed(boolean used) {
        Used = used;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public String getName(){
        return name;
    }

    public float getPrice(){
        return price;
    }

    public float getTokens(){
        return tokens;
    }

    public String getUrl(){
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(float price) {
        this.price = price;

    }

    public void setTokens(float tokens){
         this.tokens = tokens ;
         this.setUsed(true);
    }

}
