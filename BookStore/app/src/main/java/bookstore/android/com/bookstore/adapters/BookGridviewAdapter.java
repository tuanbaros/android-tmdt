package bookstore.android.com.bookstore.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import bookstore.android.com.bookstore.R;
import bookstore.android.com.bookstore.activities.SellActivity;
import bookstore.android.com.bookstore.models.ItemBookSimple;

/**
 * Created by toan on 26/12/2016.
 */
public class BookGridviewAdapter extends ArrayAdapter<ItemBookSimple> {

    public ImageButton mImageButton;
    private TextView mTextView;
    int layoutId;
    Context context;
    ArrayList<ItemBookSimple>items;

    public BookGridviewAdapter(Context context, int resource,ArrayList<ItemBookSimple> objects) {
        super(context, resource,objects);
        this.context=context;
        this.items=objects;
        this.layoutId=resource;

    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        ViewHolder viewHolder;
        if(convertView==null){
            viewHolder=new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.item_book,parent,false);
            viewHolder.gridViewItem=(CardView)convertView.findViewById(R.id.item_book);
            viewHolder.bookImage=(ImageButton) convertView.findViewById(R.id.image_book);
            viewHolder.bookPrice=(TextView)convertView.findViewById(R.id.textview_price);
            viewHolder.bookTitle=(TextView)convertView.findViewById(R.id.textview_name_book);
            viewHolder.bookRating=(RatingBar)convertView.findViewById(R.id.rating_average_item_book);
            convertView.setTag(viewHolder);

        }else {
            viewHolder=(ViewHolder)convertView.getTag();
        }
        final ItemBookSimple book=items.get(position);
        viewHolder.gridViewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(context,SellActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Bundle bundle=new Bundle();
                bundle.putInt("id_book",items.get(position).getId());
                i.putExtra("Mypackage",bundle);
                context.startActivity(i);
            }
        });

        float tmp = items.get(position).getRateAverage()*10;
        while (tmp>50){
            tmp-=50;
        }
        int tmp_int = (int)tmp;
        viewHolder.bookPrice.setText(String.valueOf(book.getPrice())+"$");
        viewHolder.bookTitle.setText(book.getTitle());
        viewHolder.bookRating.setRating((float) (((float)tmp_int)/10.0));
        Log.e("sss","rating = "+  viewHolder.bookRating.getRating() );
        Picasso.with(convertView.getContext()).load(items.get(position).getUrlImage())
                .placeholder(R.drawable.loading).error(R.drawable.error).into(viewHolder.bookImage);


        return convertView;
    }
    class ViewHolder{
        CardView gridViewItem;
        ImageButton bookImage;
        TextView bookTitle;
        TextView bookPrice;
        RatingBar bookRating;
    }
}
