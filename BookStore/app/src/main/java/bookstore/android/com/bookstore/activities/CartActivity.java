package bookstore.android.com.bookstore.activities;

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
import bookstore.android.com.bookstore.models.CartBook;

/**
 * Created by vxhuy176 on 09/12/2016.
 */

public class CartActivity extends AppCompatActivity {
    private RecyclerView mRecycleBookCart;
    private ListBookHorizontalScrollView mListBookBuyMore;
    private TextView mTextCountItem;
    private TextView mTextTotalCost;
    private Button mBtBuyCart;
    private ArrayList<Book> mListBook = new ArrayList<>();
    private BookCartAdapter mBookCartAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private CartActivity mCartActivity;
    private ArrayList<CartBook> mListCartBook = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        mTextCountItem = (TextView)findViewById(R.id.textview_count_item);
        mTextTotalCost = (TextView)findViewById(R.id.textview_total_price);
        mRecycleBookCart = (RecyclerView)findViewById(R.id.recycleview_books);
        mListBookBuyMore = (ListBookHorizontalScrollView) findViewById(R.id.horizontal_buy_more);
        mBtBuyCart = (Button)findViewById(R.id.bt_buy_cart);
        setData();
        mBookCartAdapter = new BookCartAdapter(getApplicationContext(),mListCartBook);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecycleBookCart.setHasFixedSize(true);
        mRecycleBookCart.setLayoutManager(mLinearLayoutManager);
        mRecycleBookCart.setAdapter(mBookCartAdapter);
        mListBookBuyMore.setDataListBook(mListBook);


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
        mListCartBook.add(new CartBook(1,"Cửu âm chân kinh 1","Kim Dung",100000,10000,10));
        mListCartBook.add(new CartBook(1,"Cửu âm chân kinh 1","Kim Dung",100000,10000,10));
        mListCartBook.add(new CartBook(1,"Cửu âm chân kinh 1","Kim Dung",100000,10000,10));
        mListCartBook.add(new CartBook(1,"Cửu âm chân kinh 1","Kim Dung",100000,10000,10));
        mListCartBook.add(new CartBook(1,"Cửu âm chân kinh 1","Kim Dung",100000,10000,10));
        mListCartBook.add(new CartBook(1,"Cửu âm chân kinh 1","Kim Dung",100000,10000,10));
        mListBook.add(new Book("Cửu âm Bạch cốt trảo",new Author("Quách Tương"),100000,200000));
        mListBook.add(new Book("Cửu âm Bạch cốt trảo",new Author("Quách Tương"),100000,200000));
        mListBook.add(new Book("Cửu âm Bạch cốt trảo",new Author("Quách Tương"),100000,200000));
        mListBook.add(new Book("Cửu âm Bạch cốt trảo",new Author("Quách Tương"),100000,200000));
        mListBook.add(new Book("Cửu âm Bạch cốt trảo",new Author("Quách Tương"),100000,200000));
    }
}
