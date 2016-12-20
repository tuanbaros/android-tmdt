package bookstore.android.com.bookstore.models;

import com.google.gson.annotations.SerializedName;

public class CartBook {
    @SerializedName("id")
    private int id;
    @SerializedName("idBook")
    private int idBook;
    @SerializedName("title")
    private String title;
    @SerializedName("author")
    private String author;
    @SerializedName("images")
    private String urlImage;
    @SerializedName("price")
    private float price;
    @SerializedName("oldprice")
    private float oldPrice;
    @SerializedName("quantity")
    private int quantity;
    @SerializedName("time")
    private String time;

    public CartBook(int idBook, String title, String author, float price, float oldPrice, int quantity) {
        this.idBook = idBook;
        this.title = title;
        this.author = author;
        this.price = price;
        this.oldPrice = oldPrice;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdBook() {
        return idBook;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(float oldPrice) {
        this.oldPrice = oldPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}