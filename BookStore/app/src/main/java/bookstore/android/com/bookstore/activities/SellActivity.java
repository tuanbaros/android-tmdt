package bookstore.android.com.bookstore.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import bookstore.android.com.bookstore.R;
import bookstore.android.com.bookstore.adapters.CustomScrollviewReview;
import bookstore.android.com.bookstore.adapters.ListBookHorizontalScrollView;
import bookstore.android.com.bookstore.models.Author;
import bookstore.android.com.bookstore.models.Book;
import bookstore.android.com.bookstore.models.ItemBookSimple;
import bookstore.android.com.bookstore.models.Review;
import bookstore.android.com.bookstore.network.ApiBookStore;
import bookstore.android.com.bookstore.network.RestClient;
import bookstore.android.com.bookstore.views.custom.RatingView;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by vxhuy176 on 11/12/2016.
 */

@SuppressWarnings("ResourceAsColor")
public class SellActivity extends AppCompatActivity implements View.OnClickListener {
    private CollapsingToolbarLayout collapsingToolbarLayout = null;
    private CustomScrollviewReview mCustomScrollviewReview;
    private ArrayList<Review> mListReviews = new ArrayList<>();
    private ArrayList<ItemBookSimple> mListSameBook = new ArrayList<>();
    private TextView mTextAuthor, mTextBookName, mTextOldPrice, mTextPrice, mTextNumRating;
    private RatingView mRating;
    private Button mSeeAllDescription, mSeeAllReView;
    private ListBookHorizontalScrollView mSameBook;
    private ImageView mImageBar;
    private Book mBook;
    private ProgressDialog mProgressDialog;
    public static final String BOOK_ID = "bookId";
    public static final String RATEAVERAGE_BOOK = "rateAverage";
    public static final String COUNT_RATE_BOOK = "countRate";
    public static ApiBookStore apiBookStore = RestClient.getClient().create(ApiBookStore.class);
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);

        mRating = (RatingView) findViewById(R.id.rating_book_sell);
        mTextAuthor = (TextView) findViewById(R.id.text_author_book_sell);
        mTextBookName = (TextView) findViewById(R.id.text_name_book_sell);
        mTextOldPrice = (TextView) findViewById(R.id.text_old_price_book_sell);
        mTextPrice = (TextView) findViewById(R.id.text_price_book_sell);
        mTextNumRating = (TextView) findViewById(R.id.text_num_rating_sell1);
        mCustomScrollviewReview = (CustomScrollviewReview) findViewById(R.id.scrollview_part_of_reviews);
        mSeeAllDescription = (Button) findViewById(R.id.bt_seeall_description);
        mSameBook = (ListBookHorizontalScrollView) findViewById(R.id.list_sell_same_books);
        mSeeAllReView = (Button) findViewById(R.id.bt_seeall_review);
        mImageBar = (ImageView) findViewById(R.id.profile_id);
        collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);
        setDataReview();
        mSeeAllDescription.setOnClickListener(this);
        mRating.setOnClickListener(this);
        mSeeAllReView.setOnClickListener(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_sell);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        mCustomScrollviewReview.setData(mListReviews);


    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ic_home:
                onBackPressed();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setDataReview() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.show();
        Intent callerIntent = getIntent();
        Bundle packageFromCaller =
                callerIntent.getBundleExtra("Mypackage");
        int id_book = packageFromCaller.getInt("id_book");


        Call<Book> callBook = apiBookStore.getBook(id_book);
        callBook.enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Response<Book> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    mBook = response.body();
                    mTextAuthor.setText(mBook.getAuthor().getName());
                    mTextBookName.setText(mBook.getTitle());
                    mTextPrice.setText(mBook.getPrice() + "$");
                    mTextOldPrice.setText(mBook.getOldPrice() + "$");
                    Log.e("aaa",""+mBook.getQuantityRating());
                    mTextNumRating.setText("("+mBook.getQuantityRating()+")");
                    Picasso.with(getApplicationContext()).load(mBook.getImages()).into(mImageBar);
                    collapsingToolbarLayout.setTitle(mBook.getTitle());
                    if (mBook != null) {
                        Call<ArrayList<ItemBookSimple>> callListSameBook = apiBookStore.getListBookInCategory(mBook.getCategoryId());
                        callListSameBook.enqueue(new Callback<ArrayList<ItemBookSimple>>() {
                            @Override
                            public void onResponse(Response<ArrayList<ItemBookSimple>> response, Retrofit retrofit) {
                                if (response.isSuccess()) {
                                    mListSameBook = response.body();
                                    Log.e("sss",mListSameBook+"");
                                    mSameBook.setDataListBook(mListSameBook);
                                }
                            }

                            @Override
                            public void onFailure(Throwable t) {
                                Log.e("sss",t+"");
                            }
                        });
                    }
                }
                mProgressDialog.dismiss();
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getApplicationContext(), "mBook??", Toast.LENGTH_SHORT).show();
                Log.e("sss", "onFailure: " + t);
                mProgressDialog.dismiss();
            }
        });




    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_seeall_description:
                break;
            case R.id.bt_seeall_review:
                Intent intent = new Intent(this, RateActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt(BOOK_ID, mBook.getId());
                bundle.putInt(COUNT_RATE_BOOK, mBook.getQuantityRating());
                bundle.putFloat(RATEAVERAGE_BOOK, mBook.getRateAverage());
                intent.putExtra("Mypackage", bundle);
                startActivity(intent);
                break;
            case R.id.rating_book_sell:
                Intent intent1 = new Intent(this, RateActivity.class);
                startActivity(intent1);
                break;
            default:
                break;
        }
    }

}
