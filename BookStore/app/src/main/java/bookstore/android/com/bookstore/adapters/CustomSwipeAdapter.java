package bookstore.android.com.bookstore.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import bookstore.android.com.bookstore.R;
import bookstore.android.com.bookstore.models.ItemBookSimple;
import bookstore.android.com.bookstore.views.custom.RatingView;

/**
 * Created by vxhuy176 on 09/09/2016.
 */
public class CustomSwipeAdapter extends PagerAdapter {
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    private ArrayList<ItemBookSimple> mListBestBook = new ArrayList<>();

    public CustomSwipeAdapter(Context context,ArrayList<ItemBookSimple> list) {
        this.mContext = context;
        this.mListBestBook = list;
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
        ImageView imageView = (ImageView)item_view.findViewById(R.id.image_swipe_book);
        TextView mPrice,mOldPrice,mBookName,mAuthor;
        RatingView mRating;
        mPrice = (TextView)item_view.findViewById(R.id.textview_price);
        mAuthor = (TextView)item_view.findViewById(R.id.textview_author_book);
        mBookName = (TextView)item_view.findViewById(R.id.textview_name_book);
        mOldPrice = (TextView)item_view.findViewById(R.id.textview_old_price);
        mRating = (RatingView)item_view.findViewById(R.id.rating_slide);

        mBookName.setText(mListBestBook.get(position).getTitle());
        mAuthor.setText(mListBestBook.get(position).getAuthor());
        mPrice.setText(mListBestBook.get(position).getPrice()+" $");
        Picasso.with(item_view.getContext()).load(mListBestBook.get(position).getUrlImage()).into(imageView);
        container.addView(item_view);
        return item_view;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout)object);
    }


}
