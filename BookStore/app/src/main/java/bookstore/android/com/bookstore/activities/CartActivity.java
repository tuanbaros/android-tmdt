package bookstore.android.com.bookstore.activities;

import android.app.ProgressDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import bookstore.android.com.bookstore.R;
import bookstore.android.com.bookstore.adapters.BookCartAdapter;
import bookstore.android.com.bookstore.models.Author;
import bookstore.android.com.bookstore.models.Book;
import bookstore.android.com.bookstore.adapters.ListBookHorizontalScrollView;
import bookstore.android.com.bookstore.models.Cart;
import bookstore.android.com.bookstore.models.CartBook;
import bookstore.android.com.bookstore.models.ItemBookSimple;
import bookstore.android.com.bookstore.utils.DataController;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by vxhuy176 on 09/12/2016.
 */

public class CartActivity extends AppCompatActivity {
    private RecyclerView mRecycleBookCart;
    private ListBookHorizontalScrollView mListBookBuyMore;
    private TextView mTextCountItem;
    private TextView mTextTotalCost;
    private Button mBtBuyCart;
    private ArrayList<ItemBookSimple> mListBookMore = new ArrayList<>();
    private BookCartAdapter mBookCartAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private Book mBook;
    private ArrayList<Book> mListCartBook = new ArrayList<>();
    private ProgressDialog mProgressDialog;
    private ArrayList<Integer> quantityBook = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        mTextCountItem = (TextView)findViewById(R.id.textview_count_item);
        mTextTotalCost = (TextView)findViewById(R.id.textview_total_price);
        mRecycleBookCart = (RecyclerView)findViewById(R.id.recycleview_books);
        mListBookBuyMore = (ListBookHorizontalScrollView) findViewById(R.id.horizontal_buy_more);
        mBtBuyCart = (Button)findViewById(R.id.bt_buy_cart);
        mLinearLayoutManager = new LinearLayoutManager(this);
        setData();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cart, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.ic_home:
                onBackPressed();
                return true;
            default:
                return true;
        }

    }
    private void setData(){
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();
        // get book in cart
        int id_fb = 1;
        Cart cart = new Cart(getBaseContext());
        cart.open();
        Cursor cursor = cart.getAllCartsFollowCartId(id_fb);
        if (cursor.moveToFirst())
        {
            do {
                DisplayBook(cursor);
            } while (cursor.moveToNext());
        }
        cart.close();
        mProgressDialog.dismiss();

    }

    private void DisplayBook(Cursor cursor) {
        Call<Book> bookCall = DataController.apiBookStore.getBook(cursor.getInt(2));
        bookCall.enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Response<Book> response, Retrofit retrofit) {
                if (response.isSuccess()){
                    mBook = response.body();
                    mListCartBook.add(mBook);

                    // get quantity book
                    int id_fb = 1;
                    Cart cart = new Cart(getBaseContext());
                    cart.open();
                    Cursor cursor = cart.getAllCartsFollowCartId(id_fb);
                    if (cursor.moveToFirst())
                    {
                        do {
                            if (cursor.getInt(2)==mBook.getId()){
                                quantityBook.add(cursor.getInt(3));
                            }
                        } while (cursor.moveToNext());
                    }
                    cart.close();

                    // get total items in cart
                    mTextCountItem.setText(mListCartBook.size()+"");

                    // set total cost of all item in user's cart
                    float cost = 0;
                    for(int i = 0;i<mListCartBook.size();i++){
                        cost+=mListCartBook.get(i).getPrice()*quantityBook.get(i);
                    }
                    mTextTotalCost.setText(cost+"");

                    mBookCartAdapter = new BookCartAdapter(getApplicationContext(),mListCartBook);
                    mRecycleBookCart.setHasFixedSize(true);
                    mRecycleBookCart.setLayoutManager(mLinearLayoutManager);
                    mRecycleBookCart.setAdapter(mBookCartAdapter);
                }
            }

            @Override
            public void onFailure(Throwable t) {
            }
        });
    }
}
