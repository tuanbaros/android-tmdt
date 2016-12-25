package bookstore.android.com.bookstore.views.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import bookstore.android.com.bookstore.R;

public class RatingView extends LinearLayout {

    LayoutInflater mLayoutInflater;

    ImageView mImageStar1,mImageStar2,mImageStar3,mImageStar4,mImageStar5;
    View mView;

    public RatingView(Context context) {
        super(context);
        initView(context);
    }

    public RatingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public RatingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context){
        mLayoutInflater = LayoutInflater.from(context);
        mView = mLayoutInflater.inflate(R.layout.item_star, this);
        mImageStar1 = (ImageView) mView.findViewById(R.id.image_star1);
        mImageStar2 = (ImageView) mView.findViewById(R.id.image_star2);
        mImageStar3 = (ImageView) mView.findViewById(R.id.image_star3);
        mImageStar4 = (ImageView) mView.findViewById(R.id.image_star4);
        mImageStar5 = (ImageView) mView.findViewById(R.id.image_star5);
    }

    public void setRate(int numberRating){
        switch (numberRating){
            case 5:
                mImageStar1.setImageResource(R.drawable.featured_on);
                mImageStar2.setImageResource(R.drawable.featured_on);
                mImageStar3.setImageResource(R.drawable.featured_on);
                mImageStar4.setImageResource(R.drawable.featured_on);
                mImageStar5.setImageResource(R.drawable.featured_on);
                break;

            case 4:
                mImageStar1.setImageResource(R.drawable.featured_on);
                mImageStar2.setImageResource(R.drawable.featured_on);
                mImageStar3.setImageResource(R.drawable.featured_on);
                mImageStar4.setImageResource(R.drawable.featured_on);
                mImageStar5.setImageResource(R.drawable.featured);
                break;

            case 3:
                mImageStar1.setImageResource(R.drawable.featured_on);
                mImageStar2.setImageResource(R.drawable.featured_on);
                mImageStar3.setImageResource(R.drawable.featured_on);
                mImageStar4.setImageResource(R.drawable.featured);
                mImageStar5.setImageResource(R.drawable.featured);
                break;

            case 2:
                mImageStar1.setImageResource(R.drawable.featured_on);
                mImageStar2.setImageResource(R.drawable.featured_on);
                mImageStar3.setImageResource(R.drawable.featured);
                mImageStar4.setImageResource(R.drawable.featured);
                mImageStar5.setImageResource(R.drawable.featured);
                break;

            case 1:
                mImageStar1.setImageResource(R.drawable.featured_on);
                mImageStar2.setImageResource(R.drawable.featured);
                mImageStar3.setImageResource(R.drawable.featured);
                mImageStar4.setImageResource(R.drawable.featured);
                mImageStar5.setImageResource(R.drawable.featured);
                break;
            default:
                mImageStar1.setImageResource(R.drawable.featured);
                mImageStar2.setImageResource(R.drawable.featured);
                mImageStar3.setImageResource(R.drawable.featured);
                mImageStar4.setImageResource(R.drawable.featured);
                mImageStar5.setImageResource(R.drawable.featured);
                break;

        }
    }



}
