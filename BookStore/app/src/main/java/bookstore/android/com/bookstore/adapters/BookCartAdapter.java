package bookstore.android.com.bookstore.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import bookstore.android.com.bookstore.R;
import bookstore.android.com.bookstore.activities.SellActivity;
import bookstore.android.com.bookstore.models.CartBook;


/**
 * Created by vxhuy176 on 10/12/2016.
 */

public class BookCartAdapter extends RecyclerView.Adapter<BookCartAdapter.MyHolder>  {
    private ArrayList<CartBook> mListCartBook = new ArrayList<>();
    private Context mContext;


    public BookCartAdapter(Context context,ArrayList<CartBook> listCartBook){
        this.mListCartBook = listCartBook;
        this.mContext = context;
    }
    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book_cart,parent,false);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {

        CartBook item = mListCartBook.get(position);
//        Picasso.with(mContext).load(item.getBook().getListImages().get(0).getUrl()).into(holder.mImageButton);
        holder.mBooksOldPrice.setText(item.getBook().getOldPrice()+"");
        holder.mBooksPrice.setText(item.getBook().getPrice()+"");
        holder.mBooksName.setText(item.getBook().getTitle());
        holder.mBooksAuthor.setText(item.getBook().getAuthor().getName());

    }

    @Override
    public int getItemCount() {
        return mListCartBook.size();
    }




    public class MyHolder extends RecyclerView.ViewHolder implements AdapterView.OnItemSelectedListener {
        private TextView mBooksName, mBooksAuthor, mBooksPrice, mBooksOldPrice;
        private Spinner mQuantityBook;
        private ImageButton mImageButton;
        private Button mDeleteBookCart;
        public MyHolder(View itemView) {
            super(itemView);
            mBooksAuthor = (TextView)itemView.findViewById(R.id.text_author_book);
            mBooksName = (TextView)itemView.findViewById(R.id.text_book_name);
            mBooksPrice = (TextView)itemView.findViewById(R.id.text_price_book);
            mBooksOldPrice = (TextView)itemView.findViewById(R.id.text_old_price_book);
            mQuantityBook = (Spinner)itemView.findViewById(R.id.spiner_quantity_book);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(itemView.getContext(),
                    R.array.spinner_array, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            mQuantityBook.setAdapter(adapter);
            mImageButton = (ImageButton)itemView.findViewById(R.id.imagebt_book_cart);
            mDeleteBookCart = (Button)itemView.findViewById(R.id.bt_delete_book_cart);
            mQuantityBook.setOnItemSelectedListener(this);
        }
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            String item = adapterView.getItemAtPosition(i).toString();
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }
}
