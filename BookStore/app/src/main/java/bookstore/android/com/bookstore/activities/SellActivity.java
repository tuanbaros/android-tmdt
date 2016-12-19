package bookstore.android.com.bookstore.activities;

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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import bookstore.android.com.bookstore.R;
import bookstore.android.com.bookstore.adapters.CustomScrollviewReview;
import bookstore.android.com.bookstore.adapters.ListBookHorizontalScrollView;
import bookstore.android.com.bookstore.models.Author;
import bookstore.android.com.bookstore.models.Book;
import bookstore.android.com.bookstore.models.Review;
import bookstore.android.com.bookstore.views.custom.RatingView;

/**
 * Created by vxhuy176 on 11/12/2016.
 */

@SuppressWarnings("ResourceAsColor")
public class SellActivity extends AppCompatActivity implements View.OnClickListener {
    private CollapsingToolbarLayout collapsingToolbarLayout = null;
    private CustomScrollviewReview mCustomScrollviewReview;
    private ArrayList<Review> mListReviews = new ArrayList<>();
    private List<Book> bookList = new ArrayList<>();
    private TextView mTextAuthor,mTextBookName,mTextOldPrice,mTextPrice,mTextNumRating;
    private RatingView mRating;
    private Button mSeeAllDescription, mSeeAllReView;
    private ListBookHorizontalScrollView mListSameBook;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);

        mRating =(RatingView)findViewById(R.id.rating_book_sell);
        mTextAuthor = (TextView)findViewById(R.id.text_author_book_sell);
        mTextBookName = (TextView)findViewById(R.id.text_name_book_sell);
        mTextOldPrice = (TextView)findViewById(R.id.text_old_price_book_sell);
        mTextPrice = (TextView)findViewById(R.id.text_price_book_sell);
        mTextNumRating = (TextView)findViewById(R.id.text_num_rating_sell);
        mCustomScrollviewReview = (CustomScrollviewReview)findViewById(R.id.scrollview_part_of_reviews);
        mSeeAllDescription = (Button)findViewById(R.id.bt_seeall_description);
        mListSameBook = (ListBookHorizontalScrollView)findViewById(R.id.list_sell_same_books);
        mSeeAllReView = (Button)findViewById(R.id.bt_seeall_review);
        setDataReview();
        mListSameBook.setDataListBook(bookList);
        mSeeAllDescription.setOnClickListener(this);
        mRating.setOnClickListener(this);
        mSeeAllReView.setOnClickListener(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_sell);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        Intent callerIntent=getIntent();
        Bundle packageFromCaller=
                callerIntent.getBundleExtra("Mypackage");
        int id_book=packageFromCaller.getInt("id_book");

        mRating.setRate(4);
        mTextAuthor.setText(bookList.get(id_book).getAuthor().getName());
        mTextBookName.setText((bookList.get(id_book).getTitle()));
        mTextPrice.setText(bookList.get(id_book).getPrice()+"");
        mTextOldPrice.setText(bookList.get(id_book).getOldPrice()+"");
        mCustomScrollviewReview.setData(mListReviews);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(bookList.get(id_book).getTitle());
        dynamicToolbarColor();

        toolbarTextAppernce();
    }
    private void dynamicToolbarColor() {

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.image_book);
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                collapsingToolbarLayout.setContentScrimColor(palette.getMutedColor(R.color.colorPrice));
                collapsingToolbarLayout.setStatusBarScrimColor(palette.getMutedColor(R.color.colorPrimary));
            }
        });
    }


    private void toolbarTextAppernce() {
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.collapsedappbar);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.expandedappbar);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.ic_home:
                onBackPressed();
                break;
            default:break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setDataReview(){

        bookList.add(new Book("Cửu âm Bạch cốt trảo 6",new Author("Quách Tương"),100000,200000));
        bookList.add(new Book("Cửu âm Bạch cốt trảo 6",new Author("Quách Tương"),100000,200000));
        bookList.add(new Book("Cửu âm Bạch cốt trảo 6",new Author("Quách Tương"),100000,200000));
        bookList.add(new Book("Cửu âm Bạch cốt trảo 6",new Author("Quách Tương"),100000,200000));
        bookList.add(new Book("Cửu âm Bạch cốt trảo 6",new Author("Quách Tương"),100000,200000));
        bookList.add(new Book("Cửu âm Bạch cốt trảo 6",new Author("Quách Tương"),100000,200000));
        bookList.add(new Book("Cửu âm Bạch cốt trảo 6",new Author("Quách Tương"),100000,200000));

        mListReviews.add(new Review("quá hay.hay không tưởng nổi1 :))","vxhuy176",5,"11/12/2016 22:11"));
        mListReviews.add(new Review("quá hay.hay không tưởng nổi 2:))","vxhuy176",3,"11/12/2016 22:11"));
        mListReviews.add(new Review("quá hay.hay không tưởng nổi :3))","vxhuy176",4,"11/12/2016 22:11"));
//        mListReviews.add(new Review("quá hay.hay không tưởng nổi :)4)","vxhuy176",5,"11/12/2016 22:11"));
//        mListReviews.add(new Review("quá hay.hay không tưởng nổi :))5","vxhuy176",5,"11/12/2016 22:11"));

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_seeall_description:
                break;
            case R.id.bt_seeall_review:
                Intent intent = new Intent(this,RateActivity.class);
                startActivity(intent);
                break;
            case R.id.rating_book_sell:
                Intent intent1 = new Intent(this,RateActivity.class);
                startActivity(intent1);
                break;
            default:break;
        }
    }

}
