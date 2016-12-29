package bookstore.android.com.bookstore.adapters;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import bookstore.android.com.bookstore.R;
import bookstore.android.com.bookstore.activities.CartActivity;
import bookstore.android.com.bookstore.models.Book;
import bookstore.android.com.bookstore.models.Cart;
import bookstore.android.com.bookstore.utils.DataController;


/**
 * Created by vxhuy176 on 10/12/2016.
 */

 public class BookCartAdapter extends RecyclerView.Adapter<BookCartAdapter.MyHolder>  {

    private ArrayList<Book> mListCartBook;

    private Context mContext;

    public BookCartAdapter(Context context, ArrayList<Book> listCartBook){
        this.mListCartBook = listCartBook;
        this.mContext = context;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book_cart, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        final int pos = holder.getAdapterPosition();
        final Book item = mListCartBook.get(pos);
        Picasso.with(mContext).load(item.getImages()).placeholder(R.drawable.bg_loading)
                .error(R.drawable.bg_error).into(holder.mImageButton);
        holder.mBooksOldPrice.setText(String.valueOf(item.getOldPrice()));
        holder.mBooksPrice.setText(String.valueOf(item.getPrice()));
        holder.mBooksName.setText(item.getTitle());
        holder.mBooksAuthor.setText(item.getAuthor().getName());

        // delete book
        holder.mDeleteBookCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // delete book in cart
                Cart cart = new Cart(mContext);
                cart.open();
                cart.deleteCart(getCartId(item));
                cart.close();
                mListCartBook.remove(pos);
                Toast.makeText(mContext, "Book is deleted!", Toast.LENGTH_SHORT).show();
                //
                /*Intent intent = new Intent(mContext, CartActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);*/
                //
                notifyChange();
                CartActivity.updateTotalCost(mListCartBook.size(), calculateTotalCost());
                if (mListCartBook.size() < 1) {
                    CartActivity.mBtBuyCart.setEnabled(false);
                }
            }
        });

        // get quantity for each book in cart
        Cart cart = new Cart(mContext);
        cart.open();
        Cursor cursor = cart.getCart(getCartId(item));
        cart.close();
        holder.mQuantityBook.setSelection(cursor.getInt(4) - 1, true);

        // set quantity for each book
        holder.mQuantityBook.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i("onclick", "ajaja");
                String quantity = adapterView.getItemAtPosition(i).toString();
                Cart cart = new Cart(mContext);
                cart.open();
                cart.updateQuantity(getCartId(item), Integer.parseInt(quantity));
                cart.close();
                //
                /*Intent intent = new Intent(mContext, CartActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);*/
                //
                notifyChange();
                CartActivity.updateTotalCost(mListCartBook.size(), calculateTotalCost());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private int getCartId(Book item){
        int id_user = DataController.user.getUserId();
        int cart_id = -1;
        Cart cart = new Cart(mContext);
        cart.open();
        Cursor cursor = cart.getAllCartsFollowCartId(id_user);
        if (cursor.moveToFirst())
        {
            do {
                if (cursor.getInt(2)==item.getId()){
                    cart_id = cursor.getInt(0);
                    break;
                }
            } while (cursor.moveToNext());
        }
        cart.close();
        return cart_id;
    }

    private float calculateTotalCost(){
        // get book in cart
        int id_user = DataController.user.getUserId();
        float cost = 0;
        Cart cart = new Cart(mContext);
        cart.open();
        Cursor cursor = cart.getAllCartsFollowCartId(id_user);
        if (cursor.moveToFirst())
        {
            do {
                for (int i=0; i<mListCartBook.size(); i++){
                    if (cursor.getInt(2) == mListCartBook.get(i).getId()) {
                        cost += cursor.getInt(4)*mListCartBook.get(i).getPrice();
                        break;
                    }
                }
            } while (cursor.moveToNext());
        }
        cart.close();
        return cost;
    }

    @Override
    public int getItemCount() {
        return mListCartBook.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        private TextView mBooksName, mBooksAuthor, mBooksPrice, mBooksOldPrice;
        private Spinner mQuantityBook;
        private ImageView mImageButton;
        private Button mDeleteBookCart;
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(mContext,
                R.array.spinner_array, android.R.layout.simple_spinner_item);
        MyHolder(View itemView) {
            super(itemView);
            mBooksAuthor = (TextView)itemView.findViewById(R.id.text_author_book);
            mBooksName = (TextView)itemView.findViewById(R.id.text_book_name);
            mBooksPrice = (TextView)itemView.findViewById(R.id.text_price_book);
            mBooksOldPrice = (TextView)itemView.findViewById(R.id.text_old_price_book);
            mImageButton = (ImageView)itemView.findViewById(R.id.imagebt_book_cart);
            mDeleteBookCart = (Button)itemView.findViewById(R.id.bt_delete_book_cart);
            mQuantityBook = (Spinner)itemView.findViewById(R.id.spiner_quantity_book);
            mQuantityBook.setBackgroundColor(Color.GRAY);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            mQuantityBook.setAdapter(adapter);
        }

    }

    private void notifyChange() {
        this.notifyDataSetChanged();
    }
}
