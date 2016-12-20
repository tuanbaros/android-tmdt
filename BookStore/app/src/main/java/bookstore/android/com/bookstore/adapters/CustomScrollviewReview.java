package bookstore.android.com.bookstore.adapters;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

import bookstore.android.com.bookstore.R;
import bookstore.android.com.bookstore.models.Rate;
import bookstore.android.com.bookstore.models.Review;
import bookstore.android.com.bookstore.views.custom.RatingView;
import bookstore.android.com.bookstore.views.custom.ReviewItem;

/**
 * Created by vxhuy176 on 11/12/2016.
 */

public class CustomScrollviewReview extends ScrollView {
    private LinearLayout mLinearLayout;
    public CustomScrollviewReview(Context context) {
        super(context);
        init(context);
    }

    public CustomScrollviewReview(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomScrollviewReview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    public void init(Context context) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.scrollview_review, this, true);
    }

    public void setData(ArrayList<Review> listReviews){
        mLinearLayout = (LinearLayout)findViewById(R.id.linearlayout_list_review);
        for(int i = 0;i<listReviews.size();i++){
            ReviewItem reviewItem = new ReviewItem(this.getContext());
            MyHolder myHolder = new MyHolder(reviewItem.view);
            myHolder.mNameUser.setText(listReviews.get(i).getUsername());
            myHolder.mTime.setText(listReviews.get(i).getTime());
            myHolder.mContent.setText(listReviews.get(i).getContent());
            myHolder.mRate.setRate(listReviews.get(i).getRating());
            mLinearLayout.addView(reviewItem);
        }

    }
    private class MyHolder{
        private ImageView mAvatar;
        private TextView mNameUser,mContent,mTime;
        private RatingView mRate;

        public MyHolder(View view){
            mAvatar = (ImageView)view.findViewById(R.id.image_avatar);
            mNameUser = (TextView)view.findViewById(R.id.text_user_name_review);
            mContent = (TextView)view.findViewById(R.id.text_content_review);
            mTime = (TextView)view.findViewById(R.id.text_time_review);
            mRate = (RatingView)view.findViewById(R.id.rate_star_review);
        }

    }
}
