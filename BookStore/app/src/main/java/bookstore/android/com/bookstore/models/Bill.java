package bookstore.android.com.bookstore.models;

import java.util.ArrayList;

/**
 * Created by vxhuy176 on 05/12/2016.
 */

public class Bill {
    private int id;
    private String status;
    private String time;
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
