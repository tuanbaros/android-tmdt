package bookstore.android.com.bookstore.utils;

import java.util.ArrayList;

import bookstore.android.com.bookstore.models.Bill;
import bookstore.android.com.bookstore.models.User;
import bookstore.android.com.bookstore.network.ApiBookStore;
import bookstore.android.com.bookstore.network.RestClient;

/**
 * Created by vxhuy176 on 20/12/2016.
 */

public class DataController {
    public static ArrayList<Bill> listBill = new ArrayList<>();
    public static ApiBookStore apiBookStore = RestClient.getClient().create(ApiBookStore.class);



//    public static User user = new User(1,"test");
    public static User user = null;
    public static int type_search;
    public static boolean isSpecialSearch = false;

}
