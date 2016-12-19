package bookstore.android.com.bookstore.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import bookstore.android.com.bookstore.R;
import bookstore.android.com.bookstore.adapters.CustomScrollviewReview;
import bookstore.android.com.bookstore.adapters.ListBookHorizontalScrollView;
import bookstore.android.com.bookstore.fragment.RatingFragment;
import bookstore.android.com.bookstore.models.Author;
import bookstore.android.com.bookstore.models.Book;
import bookstore.android.com.bookstore.models.Rate;
import bookstore.android.com.bookstore.models.Review;
import bookstore.android.com.bookstore.views.custom.RatingView;

/**
 * Created by vxhuy176 on 15/12/2016.
 */

public class RateActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView mScoreRatingTextView, mCountRatingTextView;
    private RatingView mAverageRating;
    private ImageView mRating5star, mRating4star, mRating3star, mRating2star, mRating1star;
    private RatingFragment mRatingFragment;
    private CustomScrollviewReview mReviews;
    private ListBookHorizontalScrollView mListBookSame;
    private ArrayList<Review> mListReviews = new ArrayList<>();
    private ArrayList<Book> mListBook = new ArrayList<>();
    private FloatingActionButton mAddReview;

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
        mAddReview = (FloatingActionButton)findViewById(R.id.bt_add_review);
        mRatingFragment = (RatingFragment) this.getSupportFragmentManager()
                .findFragmentById(R.id.fragment_rating);
        mReviews = (CustomScrollviewReview)findViewById(R.id.scroll_review_rating);
        setDataReview();
        mReviews.setData(mListReviews);
        mListBookSame = (ListBookHorizontalScrollView)findViewById(R.id.scrollhorizontal_same_type);
        mListBookSame.setDataListBook(mListBook);
        mAddReview.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_add_review:
                AlertDialog dialog = new AlertDialog.Builder(this).setTitle("Comment")
                        .setView(R.layout.dialog_add_review)
                        .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(getApplicationContext(),"OK",Toast.LENGTH_SHORT).show();
                            }
                        }).setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(getApplicationContext(),"Huy",Toast.LENGTH_SHORT).show();
                            }
                        }).create();
                dialog.show();
                break;
            default:break;
        }
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
