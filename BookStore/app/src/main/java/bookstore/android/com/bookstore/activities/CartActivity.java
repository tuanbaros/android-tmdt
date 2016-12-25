package bookstore.android.com.bookstore.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
    private ArrayList<CartBook> mListCartBook = new ArrayList<>();
    private ProgressDialog mProgressDialog;
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
        Call<Cart> cartCall = DataController.apiBookStore.getCart(4);
        cartCall.enqueue(new Callback<Cart>() {
            @Override
            public void onResponse(Response<Cart> response, Retrofit retrofit) {
                if (response.isSuccess()){
                    mListCartBook = response.body().getListCartBooks();
                    mBookCartAdapter = new BookCartAdapter(getApplicationContext(),mListCartBook);
                    mRecycleBookCart.setHasFixedSize(true);
                    mRecycleBookCart.setLayoutManager(mLinearLayoutManager);
                    mRecycleBookCart.setAdapter(mBookCartAdapter);
                    mTextCountItem.setText(mListCartBook.size()+"");
                    float cost = 0;
                    for(int i = 0;i<mListCartBook.size();i++){
                        cost+=mListCartBook.get(i).getPrice()*mListCartBook.get(i).getQuantity();
                    }
                    mTextTotalCost.setText(cost+"");
                }
                mProgressDialog.dismiss();
            }

            @Override
            public void onFailure(Throwable t) {
                mProgressDialog.dismiss();
            }
        });
    }
}
