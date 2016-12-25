package bookstore.android.com.bookstore.views.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import bookstore.android.com.bookstore.R;

/**
 * Created by vxhuy176 on 18/12/2016.
 */

public class DetailsBillItem extends LinearLayout {
    public View view;
    public DetailsBillItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public DetailsBillItem(Context context) {
        super(context);
        init(context);
    }

    public DetailsBillItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }
    public void init(Context context) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.item_details_bill, this, true);
    }
}
