package bookstore.android.com.bookstore.views.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import bookstore.android.com.bookstore.R;

/**
 * Created by vxhuy176 on 07/12/2016.
 */

public class BookItem extends LinearLayout{
    public View mView;
    public ImageButton mImageButton;
    private TextView mTextView;
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

    }




}
