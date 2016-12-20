package bookstore.android.com.bookstore.activities;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import bookstore.android.com.bookstore.R;
import bookstore.android.com.bookstore.adapters.CustomScrollviewReview;
import bookstore.android.com.bookstore.adapters.ListBookHorizontalScrollView;
import bookstore.android.com.bookstore.fragment.RatingFragment;
import bookstore.android.com.bookstore.models.Author;
import bookstore.android.com.bookstore.models.Book;
import bookstore.android.com.bookstore.models.ItemBookSimple;
import bookstore.android.com.bookstore.models.Rate;
import bookstore.android.com.bookstore.models.Review;
import bookstore.android.com.bookstore.network.ApiBookStore;
import bookstore.android.com.bookstore.network.RestClient;
import bookstore.android.com.bookstore.utils.DataController;
import bookstore.android.com.bookstore.views.custom.RatingView;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by vxhuy176 on 15/12/2016.
 */

public class RateActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView mScoreRatingTextView, mCountRatingTextView;
    private RatingBar mAverageRating;
    private ImageView mRating5star, mRating4star, mRating3star, mRating2star, mRating1star;
    private RatingFragment mRatingFragment;
    private CustomScrollviewReview mReviews;
    private ListBookHorizontalScrollView mListBookSame;
    private ArrayList<Review> mListReviews = new ArrayList<>();
    private ArrayList<ItemBookSimple> mListBook = new ArrayList<>();
    private FloatingActionButton mAddReview;
    private ProgressDialog mProgressDialog;
    private Rate mRate;
    private int mCategoryId;
    private LinearLayout mLinearRating;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);
        mScoreRatingTextView = (TextView)findViewById(R.id.text_score_rating);
        mCountRatingTextView = (TextView)findViewById(R.id.text_count_rating);
        mAverageRating = (RatingBar) findViewById(R.id.ratingview_average_rating);
        mRating1star = (ImageView)findViewById(R.id.image_rating1star);
        mRating2star = (ImageView)findViewById(R.id.image_rating2star);
        mRating3star = (ImageView)findViewById(R.id.image_rating3star);
        mRating4star = (ImageView)findViewById(R.id.image_rating4star);
        mRating5star = (ImageView)findViewById(R.id.image_rating5star);
        mLinearRating = (LinearLayout)findViewById(R.id.linear_rating);
        mAddReview = (FloatingActionButton)findViewById(R.id.bt_add_review);
        mRatingFragment = (RatingFragment) this.getSupportFragmentManager()
                .findFragmentById(R.id.fragment_rating);
        mReviews = (CustomScrollviewReview)findViewById(R.id.scroll_review_rating);
        mListBookSame = (ListBookHorizontalScrollView)findViewById(R.id.scrollhorizontal_same_type);
        setDataReview();
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
                           //viet ham post data len server
                            }
                        }).setPositiveButton("cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).create();
                dialog.show();
                break;
            default:break;
        }
    }

    public void setDataReview(){
        ApiBookStore apiBookStore = RestClient.getClient().create(ApiBookStore.class);
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();
        Intent callintent = getIntent();
        Bundle packageFromCaller=
                callintent.getBundleExtra("Mypackage");
        mCategoryId = packageFromCaller.getInt("CategoryId");

        mScoreRatingTextView.setText(packageFromCaller.getFloat(SellActivity.RATEAVERAGE_BOOK)+"");
        mAverageRating.setRating(packageFromCaller.getFloat(SellActivity.RATEAVERAGE_BOOK));
        mCountRatingTextView.setText(packageFromCaller.getInt(SellActivity.COUNT_RATE_BOOK)+"");
        Call<Rate> callRate = apiBookStore.getRate(packageFromCaller.getInt(SellActivity.BOOK_ID));
        callRate.enqueue(new Callback<Rate>() {
            @Override
            public void onResponse(Response<Rate> response, Retrofit retrofit) {
                if (response.isSuccess()){
                    mRate = response.body();
                    setCountRating();
                    mListReviews = mRate.getListReviews();
                    mReviews.setData(mListReviews);
                    Call<ArrayList<ItemBookSimple>> call = DataController.apiBookStore.getListBookInCategory(mCategoryId);
                    call.enqueue(new Callback<ArrayList<ItemBookSimple>>() {
                        @Override
                        public void onResponse(Response<ArrayList<ItemBookSimple>> response, Retrofit retrofit) {
                            if (response.isSuccess()){
                                mListBook = response.body();
                                mListBookSame.setDataListBook(mListBook);
                                mProgressDialog.dismiss();
                            }
                        }

                        @Override
                        public void onFailure(Throwable t) {
                            mProgressDialog.dismiss();
                            ReloadActivity();
                        }
                    });
                    mProgressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                mProgressDialog.dismiss();
                ReloadActivity();
            }
        });


    }

    public void ReloadActivity(){
        AlertDialog dialog = new AlertDialog.Builder(this).setTitle("Notification")
                .setMessage("Check your Internet, please!" +
                        "Would you want to reload?")
                .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                        startActivity(getIntent());
                    }
                }).setPositiveButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    public void setCountRating(){
        int totalRate = mRate.getRate1star()+mRate.getRate2star()+mRate.getRate3star()
                +mRate.getRate4star()+mRate.getRate5star();
        if (totalRate<=0){
            mLinearRating.setVisibility(View.GONE);
        }else{
            mLinearRating.setVisibility(View.VISIBLE);
            android.view.ViewGroup.LayoutParams layoutParams1 = mRating1star.getLayoutParams();
            layoutParams1.width = mRate.getRate1star()*650/totalRate;
            mRating1star.setLayoutParams(layoutParams1);
            android.view.ViewGroup.LayoutParams layoutParams2 = mRating2star.getLayoutParams();
            layoutParams2.width = mRate.getRate2star()*650/totalRate;
            mRating2star.setLayoutParams(layoutParams2);
            android.view.ViewGroup.LayoutParams layoutParams3 = mRating3star.getLayoutParams();
            layoutParams3.width = mRate.getRate3star()*650/totalRate;
            mRating3star.setLayoutParams(layoutParams3);
            android.view.ViewGroup.LayoutParams layoutParams4 = mRating4star.getLayoutParams();
            layoutParams4.width = mRate.getRate4star()*650/totalRate;
            mRating4star.setLayoutParams(layoutParams4);
            android.view.ViewGroup.LayoutParams layoutParams5 = mRating5star.getLayoutParams();
            layoutParams5.width = mRate.getRate5star()*650/totalRate;
            mRating5star.setLayoutParams(layoutParams5);

        }
    }
}
