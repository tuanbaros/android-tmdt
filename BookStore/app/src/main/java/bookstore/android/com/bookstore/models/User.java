package bookstore.android.com.bookstore.models;

import java.util.ArrayList;

/**
 * Created by vxhuy176 on 05/12/2016.
 */

public class User {
    private int id;
    private String name;
    private String email;
    private String password;
    private String address;
    private String phone;
    private String time;
    private ArrayList<Rate> listRate = new ArrayList<>();
    private ArrayList<View> listView = new ArrayList<>();
    private ArrayList<Bill> listBills = new ArrayList<>();
    private ArrayList<CustomerReview> listReviews = new ArrayList<>();
    private Cart cart;

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public int getId() {
        return id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public ArrayList<Rate> getListRate() {
        return listRate;
    }

    public void setListRate(ArrayList<Rate> listRate) {
        this.listRate = listRate;
    }

    public ArrayList<View> getListView() {
        return listView;
    }

    public void setListView(ArrayList<View> listView) {
        this.listView = listView;
    }

    public ArrayList<Bill> getListBills() {
        return listBills;
    }

    public void setListBills(ArrayList<Bill> listBills) {
        this.listBills = listBills;
    }

    public ArrayList<CustomerReview> getListReviews() {
        return listReviews;
    }

    public void setListReviews(ArrayList<CustomerReview> listReviews) {
        this.listReviews = listReviews;
    }
}
