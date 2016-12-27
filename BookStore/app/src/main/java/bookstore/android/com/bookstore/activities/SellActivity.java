package bookstore.android.com.bookstore.activities;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import bookstore.android.com.bookstore.R;
import bookstore.android.com.bookstore.adapters.CustomScrollviewReview;
import bookstore.android.com.bookstore.adapters.ListBookHorizontalScrollView;
import bookstore.android.com.bookstore.models.Author;
import bookstore.android.com.bookstore.models.Book;
import bookstore.android.com.bookstore.models.Cart;
import bookstore.android.com.bookstore.models.CartBook;
import bookstore.android.com.bookstore.models.ItemBookSimple;
import bookstore.android.com.bookstore.models.Rate;
import bookstore.android.com.bookstore.models.Review;
import bookstore.android.com.bookstore.utils.DataController;
import bookstore.android.com.bookstore.utils.Variables;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by vxhuy176 on 11/12/2016.
 */

@SuppressWarnings("ResourceAsColor")
public class SellActivity extends AppCompatActivity implements View.OnClickListener {
    private CollapsingToolbarLayout collapsingToolbarLayout = null;
    private CustomScrollviewReview mCustomScrollviewReview;
    private ArrayList<Review> mListReviews = new ArrayList<>();
    private ArrayList<ItemBookSimple> mListSameBook = new ArrayList<>();
    private ArrayList<ItemBookSimple>itemsBookOfAuthor=new ArrayList<>();
    private TextView mTextAuthor, mTextBookName, mTextOldPrice, mTextPrice, mTextNumRating,mCountRatingSell, mRatingAverageSell, mCategoryTextView;
    private RatingBar mRating,mRatingReviews;
    private Button mSeeAllDescription, mSeeAllReView, mAddCart, mBuyBook;
    private ListBookHorizontalScrollView mSameBook, bookListsOfAuthor;
    private ImageView mImageBar;
    private Book mBook;
    private Dialog dAuthor;
    private TextView tvStatus,tvCategory,tvLang,tvContent;
    private ProgressDialog mProgressDialog;
    public static final String USER_RATING_BOOK = "userRating";
    public static final String BOOK_ID = "bookId";
    public static final String RATEAVERAGE_BOOK = "rateAverage";
    public static final String COUNT_RATE_BOOK = "countRate";

    private ArrayList<CartBook> mListCartBook = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);

        mRating = (RatingBar) findViewById(R.id.rating_book_sell);
        mTextAuthor = (TextView) findViewById(R.id.text_author_book_sell);
//        mTextAuthor.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//            }
//        });
        mTextBookName = (TextView) findViewById(R.id.text_name_book_sell);
        mTextOldPrice = (TextView) findViewById(R.id.text_old_price_book_sell);
        mTextPrice = (TextView) findViewById(R.id.text_price_book_sell);
        mTextNumRating = (TextView) findViewById(R.id.text_num_rating_sell1);
        mCustomScrollviewReview = (CustomScrollviewReview) findViewById(R.id.scrollview_part_of_reviews);
        mSeeAllDescription = (Button) findViewById(R.id.bt_seeall_description);
        mSameBook = (ListBookHorizontalScrollView) findViewById(R.id.list_sell_same_books);
        mSeeAllReView = (Button) findViewById(R.id.bt_seeall_review);
        mImageBar = (ImageView) findViewById(R.id.profile_id);
        mCountRatingSell = (TextView)findViewById(R.id.text_count_Rating_sell);
        mRatingAverageSell =(TextView)findViewById(R.id.text_ratingAverage_sell);
        collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);
        mRatingReviews = (RatingBar)findViewById(R.id.rating_reviews);
        mCategoryTextView = (TextView) findViewById(R.id.category_text_view);
        mAddCart = (Button)findViewById(R.id.bt_add_cart);
        mBuyBook = (Button)findViewById(R.id.bt_buy_book);
        tvLang=(TextView)findViewById(R.id.language_text_view);
        tvContent=(TextView)findViewById(R.id.content_text_view);
        tvStatus=(TextView)findViewById(R.id.status_text_view);
        tvCategory=(TextView)findViewById(R.id.category_text_view);



        mAddCart.setOnClickListener(this);
        mBuyBook.setOnClickListener(this);
        mSeeAllDescription.setOnClickListener(this);
        mRating.setOnClickListener(this);
        mSeeAllReView.setOnClickListener(this);
        mTextAuthor.setOnClickListener(this);
        setDataSell();


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_sell);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds mListItems to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ic_home:
                onBackPressed();
                break;
            case android.R.id.home:
                this.finish();
                break;
            case R.id.ic_cart:
                Intent intent = new Intent(this, CartActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.text_author_book_sell:{
                dAuthor = new Dialog(this,
                        android.R.style.Theme_Material_Light_Dialog_Alert);
                dAuthor.setContentView(R.layout.dialog_author);
                dAuthor.setTitle("Detail Author");
                dAuthor.show();
                if(mBook!=null){
                    Call<Author> call = DataController.apiBookStore.getAuthor(mBook.getId());
                    call.enqueue(new Callback<Author>() {
                        @Override
                        public void onResponse(Response<Author> response, Retrofit retrofit) {
                            if (response.isSuccess()){
                                Author author = response.body();

                                TextView authorName = (TextView) dAuthor.findViewById(R.id.tvAuthorName);
                                TextView authorSold = (TextView) dAuthor.findViewById(R.id.authorSold);
                                TextView authorRate = (TextView) dAuthor.findViewById(R.id.authorRate);
                                TextView authorInfo=(TextView)dAuthor.findViewById(R.id.authorBook);
                                ImageView authorAvatar = (ImageView) dAuthor.findViewById(R.id.authorAvatar);
                                Button authorOK = (Button) dAuthor.findViewById(R.id.authorBt);
                                bookListsOfAuthor =
                                        (ListBookHorizontalScrollView)dAuthor.findViewById(R.id.list_author_book);
                                itemsBookOfAuthor=author.getListBooks();
                                if(itemsBookOfAuthor.size()>0) {
                                    bookListsOfAuthor.setDataListBook(itemsBookOfAuthor);
                                }
                                authorName.setText(author.getName());
                                authorSold.setText(String.valueOf(author.getTotalSold()));
                                authorRate.setText(String.valueOf(author.getRateBookAverage()));
                                authorInfo.setText(author.getRecommend());
                                Picasso.with(getApplicationContext()).load(author.getAvatar())
                                        .placeholder(R.drawable.loading).error(R.drawable.error).into(authorAvatar);
                                authorOK.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        dAuthor.dismiss();
                                    }
                                });
                            }
                        }

                        @Override
                        public void onFailure(Throwable t) {
                            Toast.makeText(getApplication(),"Error",Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                break;


        }
            case R.id.bt_seeall_description: {
                final Dialog dialog = new Dialog(this, android.R.style.Theme_Material_Light_Dialog_Alert);
                dialog.setContentView(R.layout.dialog_description);
                dialog.show();

                TextView status = (TextView) dialog.findViewById(R.id.status_text_view);
                TextView category = (TextView) dialog.findViewById(R.id.category_text_view);
                TextView author = (TextView) dialog.findViewById(R.id.author_text_view);
                TextView date = (TextView) dialog.findViewById(R.id.date_text_view);
                TextView lang = (TextView) dialog.findViewById(R.id.language_text_view);
                TextView des = (TextView) dialog.findViewById(R.id.content_text_view);
                TextView title = (TextView) dialog.findViewById(R.id.title_text_view);


                author.setText(mBook.getAuthor().getName());
                title.setText(mBook.getTitle());
                category.setText(mCategoryTextView.getText().toString());
                date.setText(mBook.getTime());
                status.setText(mBook.getStatus());
                lang.setText(mBook.getLanguage());
                des.setText(mBook.getDescription());

                Button btOk = (Button) dialog.findViewById(R.id.btDescription);
                btOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                break;
            }
            case R.id.bt_seeall_review:
                Intent intent = new Intent(this, RateActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt(BOOK_ID, mBook.getId());
                bundle.putInt(COUNT_RATE_BOOK, mBook.getQuantityRating());
                bundle.putInt(USER_RATING_BOOK, mBook.getUserRate());
                bundle.putFloat(RATEAVERAGE_BOOK, mBook.getRateAverage());
                bundle.putInt("CategoryId", mBook.getCategoryId());
                intent.putExtra("Mypackage", bundle);
                intent.putExtra("book_name", mBook.getTitle());
                startActivity(intent);
                break;
            case R.id.rating_book_sell:
                Intent intent1 = new Intent(this, RateActivity.class);
                startActivity(intent1);
                break;
            case R.id.bt_add_cart:
                addBookInCart("add");
                break;
            case R.id.bt_buy_book:
                addBookInCart("buy");
                break;
            default:
                break;
        }
    }

    // add current book in user's cart
    public void addBookInCart(final String add_or_buy){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.dialog_add_cart)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // add book for cart
                        Cart cart = new Cart(getBaseContext());
                        cart.open();
                        int id_user = DataController.user.getUserId();
                        if (cart.insertCart(id_user,mBook.getId(),mBook.getImages(),1, mBook.getTitle()) > 0)
                            Toast.makeText(getBaseContext(), "Add book successful!",Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(getBaseContext(), "Book already added!",Toast.LENGTH_SHORT).show();
                        cart.close();
                        if (add_or_buy.equals("buy")){
                            Intent intent2 = new Intent(getBaseContext(), CartActivity.class);
                            startActivity(intent2);
                        }

                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                        dialog.cancel();
                    }
                });
        // Create the AlertDialog object and return it
        builder.create();
        builder.show();
    }

    public void setDataSell() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();
        Intent callerIntent = getIntent();
        Bundle packageFromCaller =
                callerIntent.getBundleExtra("Mypackage");
        int id_book = packageFromCaller.getInt("id_book");


        Call<Book> callBook = DataController.apiBookStore.getBook(id_book);
        callBook.enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Response<Book> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    mBook = response.body();
                    mTextAuthor.setText(mBook.getAuthor().getName());
//                    mTextAuthor.setOnClickListener(showDialogAuthor);
                    mTextBookName.setText(mBook.getTitle());

                    tvLang.setText(mBook.getLanguage().toString());
                    tvStatus.setText(mBook.getStatus().toString());
                    tvContent.setText(mBook.getDescription().toString());
                    tvCategory.setText(String.valueOf(mBook.getCategoryId()));

                    mTextPrice.setText(mBook.getPrice() + "$");
                    if(mBook.getOldPrice()!=mBook.getPrice()&&mBook.getOldPrice()!=0){
                        mTextOldPrice.setText(mBook.getOldPrice() + "$");
                    }
                    mTextNumRating.setText("("+mBook.getQuantityRating()+")");
                    mRatingAverageSell.setText(mBook.getRateAverage()+"");
                    mCountRatingSell.setText(mBook.getQuantityRating()+"");
                    mCategoryTextView.setText((String) Variables.categoryHashMap.get(mBook.getCategoryId()));
                    float tmp2 = mBook.getRateAverage()*10;
                    while (tmp2>50){
                        tmp2-=50;
                    }
                    int tmp_int = (int)tmp2;
                    mRating.setRating((float) (((float)tmp_int)/10.0));
                    mRatingReviews.setRating((float) (((float)tmp_int)/10.0));
                    Picasso.with(getApplicationContext()).load(mBook.getImages()).placeholder(R.drawable.bg_loading)
                            .error(R.drawable.bg_error).into(mImageBar);
                    collapsingToolbarLayout.setTitle(mBook.getTitle());
                    if (mBook != null) {
                        Log.e("sss","mbook_id = "+mBook.getId());
                        setDataReview();
                        setDataSameBook();
                    }
                }
                mProgressDialog.dismiss();
            }

            @Override
            public void onFailure(Throwable t) {
                mProgressDialog.dismiss();
                ReloadActivity();
            }
        });
    }

    public void setDataReview() {

        Call<Rate> callRate = DataController.apiBookStore.getRate(mBook.getId());
        callRate.enqueue(new Callback<Rate>() {
            @Override
            public void onResponse(Response<Rate> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    mListReviews = response.body().getListReviews();
                    if (mListReviews.size()>3){
                        ArrayList<Review> tmp = new ArrayList<>();
                        for(int i =0;i<3;i++){
                            tmp.add(mListReviews.get(i));
                        }
                        mCustomScrollviewReview.setData(tmp);
                    }else{
                        mCustomScrollviewReview.setData(mListReviews);
                    }

                }
            }

            @Override
            public void onFailure(Throwable t) {
                mProgressDialog.dismiss();
                ReloadActivity();
            }
        });
        mProgressDialog.dismiss();

    }
    public void setDataSameBook(){
        Call<ArrayList<ItemBookSimple>> callListSameBook = DataController.apiBookStore.getListBookInCategory(mBook.getCategoryId());
        callListSameBook.enqueue(new Callback<ArrayList<ItemBookSimple>>() {
            @Override
            public void onResponse(Response<ArrayList<ItemBookSimple>> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    mListSameBook = response.body();
                    mSameBook.setDataListBook(mListSameBook);

                    mProgressDialog.dismiss();
                }
            }
            @Override
            public void onFailure(Throwable t) {
                mProgressDialog.dismiss();
                ReloadActivity();
            }
        });
    }

    public void ReloadActivity(){
        AlertDialog dialog = new AlertDialog.Builder(this).setTitle("Notification")
                .setMessage("Check your Internet, please!" +
                        "Would you want to reload?")
                .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                        startActivity(getIntent());
                    }
                }).setPositiveButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

}
