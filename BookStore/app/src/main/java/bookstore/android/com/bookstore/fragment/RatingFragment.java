package bookstore.android.com.bookstore.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import bookstore.android.com.bookstore.R;
import bookstore.android.com.bookstore.activities.RateActivity;
import bookstore.android.com.bookstore.views.custom.RatingView;

/**
 * Created by vxhuy176 on 15/12/2016.
 */

public class RatingFragment extends Fragment {
    private ImageView mAvatar;
    private RatingView mRatingView;
    private TextView mNameUser;
    private RateActivity mRateActivity;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rating, container, false);
        mAvatar = (ImageView)view.findViewById(R.id.image_user_avatar);
        mNameUser = (TextView)view.findViewById(R.id.text_user_name);
        mRatingView = (RatingView)view.findViewById(R.id.ratingview_user_rating);
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
