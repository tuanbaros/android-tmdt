package bookstore.android.com.bookstore.views.custom;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import bookstore.android.com.bookstore.R;
import bookstore.android.com.bookstore.activities.SellActivity;

/**
 * Created by vxhuy176 on 07/12/2016.
 */

public class BookItem extends LinearLayout{
    public View mView;
    public ImageButton mImageButton;
    private TextView mTextView;
    public int position;
    public BookItem(Context context) {
        super(context);
        init(context);
    }

    public BookItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BookItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        mView = layoutInflater.inflate(R.layout.item_book, this, true);
        mImageButton = (ImageButton)findViewById(R.id.image_book);
        mTextView = (TextView)findViewById(R.id.textview_name_book);
        mTextView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), ""+mTextView, Toast.LENGTH_SHORT).show();
            }
        });
        mImageButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), SellActivity.class);

                Bundle bundle = new Bundle();
                bundle.putInt("id_book",position);
                i.putExtra("Mypackage",bundle);
                getContext().startActivity(i);
            }
        });
    }




}
