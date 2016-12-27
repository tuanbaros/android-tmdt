package bookstore.android.com.bookstore.views.custom;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import bookstore.android.com.bookstore.R;
import bookstore.android.com.bookstore.activities.SearchActivity;
import bookstore.android.com.bookstore.utils.DataController;

/**
 * Created by vxhuy176 on 07/12/2016.
 */

public class CategoryItem extends LinearLayout {
    public View mView;
    public int idView = 1;
    public ImageButton imageButton;
    public CategoryItem(Context context) {
        super(context);
        init(context);
    }

    public CategoryItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CategoryItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    private void init(Context context){
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        mView = layoutInflater.inflate(R.layout.item_category, this, true);
        imageButton = (ImageButton)findViewById(R.id.imagebutton_category);
        imageButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                DataController.isSpecialSearch = true;
                DataController.type_search = idView;
                getContext().startActivity(new Intent(getContext(),SearchActivity.class));
            }
        });
    }
}
