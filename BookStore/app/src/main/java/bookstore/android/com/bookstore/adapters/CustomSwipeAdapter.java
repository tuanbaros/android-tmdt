package bookstore.android.com.bookstore.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import bookstore.android.com.bookstore.R;
import bookstore.android.com.bookstore.activities.SellActivity;
import bookstore.android.com.bookstore.models.ItemBookSimple;


public class CustomSwipeAdapter extends PagerAdapter {
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private int mPosition;
    private AppCompatActivity mActivity;

    private ArrayList<ItemBookSimple> mListBestBook = new ArrayList<>();

    public CustomSwipeAdapter(AppCompatActivity activity,Context context,ArrayList<ItemBookSimple> list) {
        this.mContext = context;
        this.mListBestBook = list;
        this.mActivity = activity;
    }

    @Override
    public int getCount() {
        return mListBestBook.size()<6?mListBestBook.size():6;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == (LinearLayout)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        mLayoutInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item_view = mLayoutInflater.inflate(R.layout.swipe_book,container,false);
        final MyHolder myHolder = new MyHolder(item_view);
        myHolder.position = position;
        myHolder.mBookName.setText(mListBestBook.get(position).getTitle());
        myHolder.mAuthor.setText(mListBestBook.get(position).getAuthor());
        myHolder.mPrice.setText(mListBestBook.get(position).getPrice()+" $");
        float tmp = mListBestBook.get(position).getRateAverage()*10;
        while (tmp>50){
            tmp-=50;
        }
        int tmp_int = (int)tmp;
        myHolder.mRating.setRating((float) (((float)tmp_int)/10.0));
        Picasso.with(item_view.getContext()).load(mListBestBook.get(position).getUrlImage())
                .placeholder(R.drawable.loading).error(R.drawable.error).into(myHolder.imageView);
        container.addView(item_view);
        myHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mActivity, SellActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id_book",mListBestBook.get(myHolder.position).getId());
                i.putExtra("Mypackage",bundle);
                mActivity.startActivity(i);
            }
        });
        return item_view;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout)object);
    }

    private class MyHolder{
        int position;
        TextView mPrice,mOldPrice,mBookName,mAuthor;
        RatingBar mRating;
        ImageView imageView;
        public MyHolder(View item_view){
            imageView = (ImageView)item_view.findViewById(R.id.image_swipe_book);
            mPrice = (TextView)item_view.findViewById(R.id.textview_price);
            mAuthor = (TextView)item_view.findViewById(R.id.textview_author_book);
            mBookName = (TextView)item_view.findViewById(R.id.textview_name_book);
            mOldPrice = (TextView)item_view.findViewById(R.id.textview_old_price);
            mRating = (RatingBar)item_view.findViewById(R.id.ratingBar);

        }
    }
}
