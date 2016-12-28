package bookstore.android.com.bookstore.fragment;


import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import bookstore.android.com.bookstore.R;
import bookstore.android.com.bookstore.activities.RateActivity;
import bookstore.android.com.bookstore.models.Book;
import bookstore.android.com.bookstore.models.Rate;
import bookstore.android.com.bookstore.utils.DataController;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by vxhuy176 on 15/12/2016.
 */

public class RatingFragment extends Fragment{
    private ImageView mAvatar;
    private RatingBar mRatingView;
    private TextView mNameUser;
    private RateActivity mRateActivity;
    private float mRate;
    private Button mChangeRate;
    private int mBookId;
    private int mUserRating = 0;
    private boolean mInorgeRatingBar = false;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rating, container, false);
        mAvatar = (ImageView)view.findViewById(R.id.image_user_avatar);
        mNameUser = (TextView)view.findViewById(R.id.text_user_name);
        mRatingView = (RatingBar)view.findViewById(R.id.ratingview_user_rating);
        mChangeRate = (Button)view.findViewById(R.id.bt_change_rating);
        if(DataController.user.getAvatar()!=null){
            Picasso.with(getContext()).load(DataController.user.getAvatar()).into(mAvatar);
        }
        if(DataController.user.getName()!=null){
            mNameUser.setText(DataController.user.getName());
        }


        mChangeRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRatingView.setIsIndicator(false);
                mChangeRate.setVisibility(View.GONE);
            }
        });
        mRatingView.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                mRate = v;
                if(!mInorgeRatingBar){
                    AlertDialog dialog = new AlertDialog.Builder(getContext()).setTitle("Notification")
                            .setMessage("Are you really rating for the book?")
                            .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Call<Rate.Status> callRate = DataController.apiBookStore.postRate(
                                            DataController.user.getUserId(),
                                            mBookId,
                                            DataController.user.getUserToken(),
                                            (int)mRate
                                    );
                                    callRate.enqueue(new Callback<Rate.Status>() {
                                        @Override
                                        public void onResponse(Response<Rate.Status> response, Retrofit retrofit) {
                                            if (response.isSuccess()){
                                                Rate.Status status = response.body();
                                                if (status.getStatus().equals("success")){
                                                    mRatingView.setRating(mRate);
                                                    mRatingView.setIsIndicator(true);
                                                    mChangeRate.setVisibility(View.VISIBLE);
                                                    mRateActivity.updateDataRate();
                                                    mRateActivity.setDataReview();
                                                    mRateActivity.setDataSameBook();
                                                    loadDataBook();
                                                }
                                            }
                                        }

                                        @Override
                                        public void onFailure(Throwable t) {

                                        }
                                    });

                                }
                            }).setPositiveButton("cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            }).create();
                    dialog.setCanceledOnTouchOutside(false);
                    dialog.show();
                }
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof RateActivity) {
            this.mRateActivity = (RateActivity) context;
        }
    }

    public void setmBookId(int mBookId,RateActivity rateActivity) {
        this.mRateActivity = rateActivity;
        this.mBookId = mBookId;
        Log.e("test","mBookID = "+mBookId);//cham the :)))
        if(mBookId>0){
            loadDataBook();

        }
    }
    public void loadDataBook(){
        if (DataController.user!=null){
            DataController.apiBookStore.getBook(mBookId,DataController.user.getUserId()).enqueue(new Callback<Book>() {
                @Override
                public void onResponse(Response<Book> response, Retrofit retrofit) {
                    if(response.isSuccess()){
                        mRateActivity.book = response.body();
                        mUserRating = mRateActivity.book.getUserRate();
                        Log.e("sss","rate = "+mUserRating);
                        if(mUserRating>=1&&mUserRating<=5){
                            mInorgeRatingBar = true;
                            mRatingView.setIsIndicator(false);
                            mRatingView.setRating(mUserRating);
                            mRatingView.setIsIndicator(true);
                            mInorgeRatingBar = false;
                            mChangeRate.setVisibility(View.VISIBLE);
                            if(mRateActivity.book!=null&&mRateActivity.book.getUserRate()>0){
                                mRateActivity.getmAddReview().setVisibility(View.VISIBLE);
                            }else{
                                mRateActivity.getmAddReview().setVisibility(View.GONE);
                            }
                            //chac luc nay server ck
                        }
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    t.printStackTrace();
                }
            });
        }
    }




}
