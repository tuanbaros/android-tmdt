package bookstore.android.com.bookstore.features.getbought;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.squareup.picasso.Picasso;

import bookstore.android.com.bookstore.R;
import bookstore.android.com.bookstore.databinding.BoughtItemListViewBinding;

/**
 * Created by tuannt on 28/12/2016.
 */

public class BoughtAdapter extends BaseAdapter {

    private Book[] items;

    private LayoutInflater inflater;

    public BoughtAdapter(Context context, Book[] items) {
        this.items = items;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public Object getItem(int i) {
        return items[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.bought_item_list_view, null);
            holder.mBinding =
                    DataBindingUtil.bind(convertView);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        Picasso.with(parent.getContext()).load(items[position].getUrlImage()).into(holder.mBinding.avatarImageView);
        holder.mBinding.nameTextView.setText(items[position].getTitle());
        holder.mBinding.nameAuthorTextView.setText(items[position].getAuthor());
        holder.mBinding.priceTextView.setText(items[position].getPrice() + " $");
        return convertView;
    }

    private static class ViewHolder {
        private BoughtItemListViewBinding mBinding;
    }
}