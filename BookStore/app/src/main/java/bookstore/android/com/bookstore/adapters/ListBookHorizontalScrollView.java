package bookstore.android.com.bookstore.adapters;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import bookstore.android.com.bookstore.R;
import bookstore.android.com.bookstore.models.Category;
import bookstore.android.com.bookstore.models.ItemBookSimple;
import bookstore.android.com.bookstore.views.custom.BookItem;
import bookstore.android.com.bookstore.views.custom.CategoryItem;

public class ListBookHorizontalScrollView extends HorizontalScrollView {

    private LinearLayout mLinearLayoutBook;
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

    public void setDataListBook(ArrayList<ItemBookSimple> bookList){
//        Log.e("sss","sssssss"+ bookList);
        mLinearLayoutBook = (LinearLayout)findViewById(R.id.linearlayout_list_book);
        mLinearLayoutBook.removeAllViews();
        int tmp = bookList.size()<10?bookList.size():10;
        for(int i = 0;i<tmp;i++){
            BookItem bookItem = new BookItem(this.getContext());
            MyHolder myHolder = new MyHolder(bookItem.mView);
            Log.e("sss", "bookList.get(i).getId(): "+ bookList.get(i).getId() );
            if(bookList.get(i).getId()!=0){
                bookItem.position = bookList.get(i).getId();
            }else{
                bookItem.position =i;
            }
            if(bookList.get(i).getRateAverage()!=0){
//                myHolder.mRatingBook.bookList.get(i).getRateAverage();
            }
            if (!bookList.get(i).getUrlImage().equals("")){
                Picasso.with(bookItem.mView.getContext()).load(bookList.get(i).getUrlImage())
                        .placeholder(R.drawable.bg_loading).error(R.drawable.bg_error)
                        .into(myHolder.mBtBook);
            }
            myHolder.mBookName.setText(bookList.get(i).getTitle());
            myHolder.mBookName.setSelected(true);
            float tmp2 = bookList.get(i).getRateAverage()*10;
            while (tmp2>50){
                tmp2-=50;
            }
            int tmp_int = (int)tmp2;
            myHolder.mRatingBook.setRating((float) (((float)tmp_int)/10.0));
            myHolder.mBookPrice.setText(bookList.get(i).getPrice()+"$");
            mLinearLayoutBook.addView(bookItem);
        }

    }


    public void setDataCategory(ArrayList<Category> categoryList){
        mLinearLayoutBook = (LinearLayout)findViewById(R.id.linearlayout_list_book);
        for(int i = 0; i<categoryList.size();i++){
            CategoryItem categoryItem = new CategoryItem(this.getContext());
            MyHolderCategory myHolderCategory = new MyHolderCategory(categoryItem.mView);
            if (!categoryList.get(i).getImage().equals("")){
                Picasso.with(getContext()).load(categoryList.get(i).getImage())
                        .placeholder(R.drawable.loading).error(R.drawable.error).into(myHolderCategory.mImageCategory);
            }
            categoryItem.idView = categoryList.get(i).getId();
            myHolderCategory.mNameCategory.setText(categoryList.get(i).getName());
            mLinearLayoutBook.addView(categoryItem);
        }
    }

    class MyHolder{
        private TextView mBookName,mBookPrice,mBookOldPrice;
        private ImageButton mBtBook;
        private RatingBar mRatingBook;
        public MyHolder(View view){
            super();
            mBookName = (TextView)view.findViewById(R.id.textview_name_book);
//            mBookOldPrice = (TextView)view.findViewById(R.id.textview_old_price);
            mBookPrice = (TextView)view.findViewById(R.id.textview_price);
            mBtBook = (ImageButton)view.findViewById(R.id.image_book);
            mRatingBook = (RatingBar)view.findViewById(R.id.rating_average_item_book);
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


