package bookstore.android.com.bookstore.views.custom;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import bookstore.android.com.bookstore.R;
import bookstore.android.com.bookstore.activities.MainActivity;
import bookstore.android.com.bookstore.models.Book;
import bookstore.android.com.bookstore.models.Category;

public class ListBookHorizontalScrollView extends HorizontalScrollView {
    public LinearLayout mLinearLayoutBook;
    private LinearLayout mFavoriteLinearLayout;
    public ListBookHorizontalScrollView(Context context) {
        super(context);
        init(context);
    }

    public ListBookHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ListBookHorizontalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    public void init(Context context) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.list_horizontal_book, this, true);
    }
    public void setDataListBook(List<Book> bookList, final MainActivity mainActivity){
        mLinearLayoutBook = (LinearLayout)findViewById(R.id.linearlayout_list_book);
        for(int i = 0;i<5;i++){
            BookItem bookItem = new BookItem(this.getContext());
            MyHolder myHolder = new MyHolder(bookItem.mView);
            myHolder.mBookName.setText(bookList.get(i).getTitle());
            myHolder.mBookName.setSelected(true);
            Log.e("TEST", "setDataListBook: "+myHolder.mBookName.getText() );
            myHolder.mBookOldPrice.setText(bookList.get(i).getOldPrice()+"");
            myHolder.mBookPrice.setText(bookList.get(i).getPrice()+"");
            mLinearLayoutBook.addView(bookItem);

        }

    }
    public void setDataCategory(List<Category> categoryList){
        mLinearLayoutBook = (LinearLayout)findViewById(R.id.linearlayout_list_book);
        for(int i = 0; i<5;i++){
            CategoryItem categoryItem = new CategoryItem(this.getContext());
            MyHolderCategory myHolderCategory = new MyHolderCategory(categoryItem.mView);
            Log.e("sss", "setDataCategory: e");
            myHolderCategory.mNameCategory.setText(categoryList.get(i).getName());
            mLinearLayoutBook.addView(categoryItem);
        }
    }

    class MyHolder{
        private TextView mBookName,mBookPrice,mBookOldPrice;
        private ImageButton mBtBook;
        public MyHolder(View view){
            super();
            mBookName = (TextView)view.findViewById(R.id.textview_name_book);
            mBookOldPrice = (TextView)view.findViewById(R.id.textview_old_price);
            mBookPrice = (TextView)view.findViewById(R.id.textview_price);
            mBtBook = (ImageButton)view.findViewById(R.id.image_book);
        }
    }
    class MyHolderCategory{
        private TextView mNameCategory;
        private ImageButton mImageCategory;

        public MyHolderCategory(View view){
            mNameCategory = (TextView)view.findViewById(R.id.textview_name_category);
            mImageCategory = (ImageButton)view.findViewById(R.id.imagebutton_category);
        }

    }

}


