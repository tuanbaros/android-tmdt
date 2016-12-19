package bookstore.android.com.bookstore.models;

import java.util.ArrayList;

/**
 * Created by vxhuy176 on 05/12/2016.
 */

public class Cart {
    private int id;
    private ArrayList<CartBook> listCartBooks = new ArrayList<>();
    private float totalCost;
    private String time;

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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


}
