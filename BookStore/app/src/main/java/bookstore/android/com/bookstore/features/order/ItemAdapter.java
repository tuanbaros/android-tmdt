package bookstore.android.com.bookstore.features.order;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import bookstore.android.com.bookstore.R;
import bookstore.android.com.bookstore.databinding.ItemBookOrderListViewBinding;

/**
 * Created by tuannt on 27/12/2016.
 */

public class ItemAdapter extends BaseAdapter {

    private Item[] items;

    private LayoutInflater inflater;

    public ItemAdapter(Context context, Item[] items) {
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
            convertView = inflater.inflate(R.layout.item_book_order_list_view, null);
            holder.mBinding =
                    DataBindingUtil.bind(convertView);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.mBinding.indexTextView.setText("" + (position + 1));
        holder.mBinding.nameTextView.setText(items[position].getName());
        holder.mBinding.quantityTextView.setText(" (" + items[position].getQuantity() + ")");
        return convertView;
    }

    private static class ViewHolder {
        private ItemBookOrderListViewBinding mBinding;
    }
}
