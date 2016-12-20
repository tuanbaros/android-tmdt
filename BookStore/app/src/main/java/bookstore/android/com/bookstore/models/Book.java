package bookstore.android.com.bookstore.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by vxhuy176 on 05/12/2016.
 */

public class Book {
    @SerializedName("id")
    private int id = 0;
    @SerializedName("title")
    private String title;
    @SerializedName("author")
    private Author author;
    @SerializedName("status")
    private String status;
    @SerializedName("image_url")
    private String images;
    @SerializedName("new_price")
    private float price;
    @SerializedName("price")
    private float oldPrice;
    @SerializedName("language")
    private String language;
    @SerializedName("description")
    private String description;
    @SerializedName("rate_average")
    private float rateAverage;
    @SerializedName("quantityBuy")
    private int quantityBuy;
    @SerializedName("date_releases")
    private String time;
    @SerializedName("userRate")
    private int userRate;
    @SerializedName("author_id")
    private int authorId;
    @SerializedName("category_id")
    private int categoryId;
    @SerializedName("discount_percent")
    private float discount;
    @SerializedName("quantity_selling")
    private int quantitySelling;
    @SerializedName("quantity_remain")
    private int quantityRemain;
    @SerializedName("quantityRating")
    private int quantityRating;


    public Book(String title, Author author, float price, float oldPrice) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.oldPrice = oldPrice;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public int getQuantitySelling() {
        return quantitySelling;
    }

    public void setQuantitySelling(int quantitySelling) {
        this.quantitySelling = quantitySelling;
    }

    public int getQuantityRemain() {
        return quantityRemain;
    }

    public void setQuantityRemain(int quantityRemain) {
        this.quantityRemain = quantityRemain;
    }

    public int getQuantityRating() {
        return quantityRating;
    }

    public void setQuantityRating(int quantityRating) {
        this.quantityRating = quantityRating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getRateAverage() {
        return rateAverage;
    }

    public void setRateAverage(float rateAverage) {
        this.rateAverage = rateAverage;
    }

    public int getQuantityBuy() {
        return quantityBuy;
    }

    public void setQuantityBuy(int quantityBuy) {
        this.quantityBuy = quantityBuy;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public int getUserRate() {
        return userRate;
    }

    public void setUserRate(int userRate) {
        this.userRate = userRate;
    }
}
