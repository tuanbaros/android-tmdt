package bookstore.android.com.bookstore.views.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import bookstore.android.com.bookstore.R;

/**
 * Created by vxhuy176 on 11/12/2016.
 */

public class ReviewItem extends LinearLayout {
    public View view;
    public ReviewItem(Context context) {
        super(context);
        init(context);
    }

    public ReviewItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ReviewItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    public void init(Context context) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.item_review, this, true);
    }
}
