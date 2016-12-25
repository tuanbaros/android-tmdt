package bookstore.android.com.bookstore.views.custom;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import bookstore.android.com.bookstore.R;
import bookstore.android.com.bookstore.activities.DetailsBillActivity;

/**
 * Created by vxhuy176 on 18/12/2016.
 */

public class BillItem extends LinearLayout {
    public View view;
    public int id;
    public static final String BILL_ID = "billId";
    public BillItem(Context context) {
        super(context);
        init(context);
    }

    public BillItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BillItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    public void init(Context context) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.item_bill, this, true);
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DetailsBillActivity.class);
                intent.putExtra(BILL_ID,id);
                view.getContext().startActivity(intent);
            }
        });
    }

}
