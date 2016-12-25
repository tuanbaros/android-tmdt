package bookstore.android.com.bookstore.network;

import java.util.ArrayList;

import bookstore.android.com.bookstore.models.Bill;
import bookstore.android.com.bookstore.models.Book;
import bookstore.android.com.bookstore.models.Cart;
import bookstore.android.com.bookstore.models.Category;
import bookstore.android.com.bookstore.models.ItemBookSimple;
import bookstore.android.com.bookstore.models.Rate;
import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by vxhuy176 on 19/12/2016.
 */

public interface ApiBookStore {
    @GET("status/top-selling")
    Call<ArrayList<ItemBookSimple>> getListTopSelling();

    @GET("status/new-releases")
    Call<ArrayList<ItemBookSimple>> getListNewReleases();

    @GET("status/top-saleoff")
    Call<ArrayList<ItemBookSimple>> getListTopSaleOff();

    @GET("category")
    Call<ArrayList<Category>> getListCategory();

    @GET("category/{category_id}")
    Call<ArrayList<ItemBookSimple>> getListBookInCategory(@Path("category_id") int category_id);

    @GET("cart/{user_id}")
    Call<Cart> getCart(@Path("user_id") int user_id);

    @GET("book/{book_id}")
    Call<Book> getBook(@Path("book_id") int book_id);

    @GET("bill/{user_id}")
    Call<ArrayList<Bill>> getBill(@Path("user_id") int user_id);

    @GET("rate/{book_id}")
    Call<Rate> getRate(@Path("book_id") int book_id);

    @GET("author/{book_id}")
    Call<Book> getAuthor(@Path("book_id") int book_id);

//    @FormUrlEncoded
//    @POST("cart/add")
//    Call<User> updateUser(@Field("first_name") String first, @Field("last_name") String last);
}
