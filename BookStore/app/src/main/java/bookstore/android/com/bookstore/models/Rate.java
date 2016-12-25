package bookstore.android.com.bookstore.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by vxhuy176 on 05/12/2016.
 */

public class Rate {
    @SerializedName("idBook")
    private int idBook;
    @SerializedName("rate1star")
    private int rate1star;
    @SerializedName("rate2star")
    private int rate2star;
    @SerializedName("rate3star")
    private int rate3star;
    @SerializedName("rate4star")
    private int rate4star;
    @SerializedName("rate5star")
    private int rate5star;
    @SerializedName("listReviews")
    private ArrayList<Review> listReviews = new ArrayList<>();

    public int getIdBook() {
        return idBook;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }

    public int getRate1star() {
        return rate1star;
    }

    public void setRate1star(int rate1star) {
        this.rate1star = rate1star;
    }

    public int getRate2star() {
        return rate2star;
    }

    public void setRate2star(int rate2star) {
        this.rate2star = rate2star;
    }

    public int getRate3star() {
        return rate3star;
    }

    public void setRate3star(int rate3star) {
        this.rate3star = rate3star;
    }

    public int getRate4star() {
        return rate4star;
    }

    public void setRate4star(int rate4star) {
        this.rate4star = rate4star;
    }

    public int getRate5star() {
        return rate5star;
    }

    public void setRate5star(int rate5star) {
        this.rate5star = rate5star;
    }

    public ArrayList<Review> getListReviews() {
        return listReviews;
    }

    public void setListReviews(ArrayList<Review> listReviews) {
        this.listReviews = listReviews;
    }

    public class Status{
        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        @SerializedName("status")
        String status;

    }

}
