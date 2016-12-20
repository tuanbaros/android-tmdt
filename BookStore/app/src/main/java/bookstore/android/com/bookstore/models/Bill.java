package bookstore.android.com.bookstore.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by vxhuy176 on 05/12/2016.
 */

public class Bill {
    @SerializedName("id")
    private int id;
    @SerializedName("status")
    private String status;
    @SerializedName("time")
    private String time;
    @SerializedName("listbooks")
    private ArrayList<ItemBookOfBill> listBooks = new ArrayList<>();

    public Bill(int id, String status, String time, ArrayList<ItemBookOfBill> listBooks) {
        this.id = id;
        this.status = status;
        this.time = time;
        this.listBooks = listBooks;
    }

    public ArrayList<ItemBookOfBill> getListBooks() {
        return listBooks;
    }

    public void setListBooks(ArrayList<ItemBookOfBill> listBooks) {
        this.listBooks = listBooks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


}
