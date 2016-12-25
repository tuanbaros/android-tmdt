package bookstore.android.com.bookstore.fragment;


import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import bookstore.android.com.bookstore.R;
import bookstore.android.com.bookstore.activities.RateActivity;
import bookstore.android.com.bookstore.views.custom.RatingView;

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
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rating, container, false);
        mAvatar = (ImageView)view.findViewById(R.id.image_user_avatar);
        mNameUser = (TextView)view.findViewById(R.id.text_user_name);
        mRatingView = (RatingBar)view.findViewById(R.id.ratingview_user_rating);
        mChangeRate = (Button)view.findViewById(R.id.bt_change_rating);
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
                AlertDialog dialog = new AlertDialog.Builder(getContext()).setTitle("Notification")
                        .setMessage("Are you really rating for the book?")
                        .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                mRatingView.setRating(mRate);
                                mRatingView.setIsIndicator(true);
                                mChangeRate.setVisibility(View.VISIBLE);
                            }
                        }).setPositiveButton("cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).create();
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
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

}
