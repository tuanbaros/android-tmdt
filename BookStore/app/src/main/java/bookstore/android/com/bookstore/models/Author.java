package bookstore.android.com.bookstore.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by vxhuy176 on 05/12/2016.
 */

public class Author {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("rateBookAverage")
    private float rateBookAverage;
    @SerializedName("TotalSold")
    private int TotalSold;
    @SerializedName("introduce")
    private String recommend;
//    @SerializedName("contact")
//    private String contact;
    @SerializedName("avatar")
    private String avatar;
    @SerializedName("listBooks")
    private ArrayList<ItemBookSimple> listBooks = new ArrayList<>();

    public Author(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public float getRateBookAverage() {
        return rateBookAverage;
    }

    public void setRateBookAverage(float rateBookAverage) {
        this.rateBookAverage = rateBookAverage;
    }

    public int getTotalSold() {
        return TotalSold;
    }

    public void setTotalSold(int totalSold) {
        TotalSold = totalSold;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }

//    public String getContact() {
//        return contact;
//    }
//
//    public void setContact(String contact) {
//        this.contact = contact;
//    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }


    public ArrayList<ItemBookSimple> getListBooks() {
        return listBooks;
    }

    public void setListBooks(ArrayList<ItemBookSimple> listBooks) {
        this.listBooks = listBooks;
    }


}
