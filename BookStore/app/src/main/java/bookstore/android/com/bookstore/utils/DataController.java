package bookstore.android.com.bookstore.utils;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import java.util.ArrayList;

import bookstore.android.com.bookstore.models.Bill;
import bookstore.android.com.bookstore.models.Book;
import bookstore.android.com.bookstore.models.ItemBookSimple;
import bookstore.android.com.bookstore.models.User;
import bookstore.android.com.bookstore.network.ApiBookStore;
import bookstore.android.com.bookstore.network.RestClient;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by vxhuy176 on 20/12/2016.
 */

public class DataController {
    public static ArrayList<Bill> listBill = new ArrayList<>();
    public static ApiBookStore apiBookStore = RestClient.getClient().create(ApiBookStore.class);
    public static User user=null;

}
