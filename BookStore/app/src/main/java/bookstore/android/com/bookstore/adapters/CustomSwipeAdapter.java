package bookstore.android.com.bookstore.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import bookstore.android.com.bookstore.R;

/**
 * Created by vxhuy176 on 09/09/2016.
 */
public class CustomSwipeAdapter extends PagerAdapter {
    private int[] mImageBook = {R.drawable.image_book,R.drawable.image_book,R.drawable.image_book,R.drawable.image_book,R.drawable.image_book};
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public CustomSwipeAdapter(Context context) {
        this.mContext = context;

    }

    @Override
    public int getCount() {
        return mImageBook.length;
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
        imageView.setImageResource(mImageBook[position]);
        container.addView(item_view);
        return item_view;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout)object);
    }
}
