package bookstore.android.com.bookstore.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import bookstore.android.com.bookstore.R;
import bookstore.android.com.bookstore.adapters.CustomScrollviewReview;
import bookstore.android.com.bookstore.adapters.ListBookHorizontalScrollView;
import bookstore.android.com.bookstore.fragment.RatingFragment;
import bookstore.android.com.bookstore.models.Author;
import bookstore.android.com.bookstore.models.Book;
import bookstore.android.com.bookstore.models.Review;
import bookstore.android.com.bookstore.views.custom.RatingView;

/**
 * Created by vxhuy176 on 15/12/2016.
 */

public class RateActivity extends AppCompatActivity {
    private TextView mScoreRatingTextView, mCountRatingTextView;
    private RatingView mAverageRating;
    private ImageView mRating5star, mRating4star, mRating3star, mRating2star, mRating1star;
    private RatingFragment mRatingFragment;
    private CustomScrollviewReview mReviews;
    private ListBookHorizontalScrollView mListBookSame;
    private ArrayList<Review> mListReviews = new ArrayList<>();
    private ArrayList<Book> mListBook = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);
        mScoreRatingTextView = (TextView)findViewById(R.id.text_score_rating);
        mCountRatingTextView = (TextView)findViewById(R.id.text_count_rating);
        mAverageRating = (RatingView) findViewById(R.id.ratingview_average_rating);
        mRating1star = (ImageView)findViewById(R.id.image_rating1star);
        mRating2star = (ImageView)findViewById(R.id.image_rating2star);
        mRating3star = (ImageView)findViewById(R.id.image_rating3star);
        mRating4star = (ImageView)findViewById(R.id.image_rating4star);
        mRating5star = (ImageView)findViewById(R.id.image_rating5star);
        mRatingFragment = (RatingFragment) this.getSupportFragmentManager()
                .findFragmentById(R.id.fragment_rating);
        mReviews = (CustomScrollviewReview)findViewById(R.id.scroll_review_rating);
        setDataReview();
        mReviews.setData(mListReviews);
        mListBookSame = (ListBookHorizontalScrollView)findViewById(R.id.scrollhorizontal_same_type);
        mListBookSame.setDataListBook(mListBook);

    }
    public void setDataReview(){
        mListBook.add(new Book("Cửu âm Bạch cốt trảo 1 + Hang long thap bat skill",new Author("Quách Tương"),100000,200000));
        mListBook.add(new Book("Cửu âm Bạch cốt trảo 1 + Hang long thap bat skill",new Author("Quách Tương"),100000,200000));
        mListBook.add(new Book("Cửu âm Bạch cốt trảo 2 + Hang long thap bat skill",new Author("Quách Tương"),100000,200000));
        mListBook.add(new Book("Cửu âm Bạch cốt trảo 3 + Hang long thap bat skill",new Author("Quách Tương"),100000,200000));
        mListBook.add(new Book("Cửu âm Bạch cốt trảo 4 + Hang long thap bat skill",new Author("Quách Tương"),100000,200000));
        mListReviews.add(new Review("quá hay.hay không tưởng nổi1 :))","vxhuy176",5,"11/12/2016 22:11"));
        mListReviews.add(new Review("quá hay.hay không tưởng nổi 2:))","vxhuy176",3,"11/12/2016 22:11"));
        mListReviews.add(new Review("quá hay.hay không tưởng nổi :3))","vxhuy176",4,"11/12/2016 22:11"));
        mListReviews.add(new Review("quá hay.hay không tưởng nổi :)4)","vxhuy176",5,"11/12/2016 22:11"));
        mListReviews.add(new Review("quá hay.hay không tưởng nổi :))5","vxhuy176",5,"11/12/2016 22:11"));
        mListReviews.add(new Review("quá hay.hay không tưởng nổi1 :))","vxhuy176",5,"11/12/2016 22:11"));
        mListReviews.add(new Review("quá hay.hay không tưởng nổi 2:))","vxhuy176",3,"11/12/2016 22:11"));
        mListReviews.add(new Review("quá hay.hay không tưởng nổi :3))","vxhuy176",4,"11/12/2016 22:11"));
        mListReviews.add(new Review("quá hay.hay không tưởng nổi :)4)","vxhuy176",5,"11/12/2016 22:11"));
        mListReviews.add(new Review("quá hay.hay không tưởng nổi :))5","vxhuy176",5,"11/12/2016 22:11"));

    }
}
