package bookstore.android.com.bookstore.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by vxhuy176 on 05/12/2016.
 */

public class Cart {
    @SerializedName("id")
    private int id;
    @SerializedName("cartbooks")
    private ArrayList<CartBook> listCartBooks = new ArrayList<>();
    @SerializedName("total_cost")
    private float totalCost;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<CartBook> getListCartBooks() {
        return listCartBooks;
    }

    public void setListCartBooks(ArrayList<CartBook> listCartBooks) {
        this.listCartBooks = listCartBooks;
    }

    public float getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(float totalCost) {
        this.totalCost = totalCost;
    }



}
