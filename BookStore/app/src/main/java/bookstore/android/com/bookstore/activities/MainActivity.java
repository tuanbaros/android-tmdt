package bookstore.android.com.bookstore.activities;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import bookstore.android.com.bookstore.R;
import bookstore.android.com.bookstore.adapters.CustomSwipeAdapter;
import bookstore.android.com.bookstore.models.Author;
import bookstore.android.com.bookstore.models.Book;
import bookstore.android.com.bookstore.models.Category;
import bookstore.android.com.bookstore.adapters.ListBookHorizontalScrollView;
import bookstore.android.com.bookstore.models.ItemBookSimple;
import bookstore.android.com.bookstore.network.ApiBookStore;
import bookstore.android.com.bookstore.network.RestClient;
import bookstore.android.com.bookstore.utils.DataController;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private ViewPager mViewPager;
    private CustomSwipeAdapter mCustomSwipeAdapter;
    private ListBookHorizontalScrollView mBestSeller,mCategory,mKindleEBooks,mBestBookOfMonth,mSales,mNewReleases;
    private ArrayList<ItemBookSimple> mListSalesBook = new ArrayList<>();
    private ArrayList<ItemBookSimple> mListBestSelling = new ArrayList<>();
    private ArrayList<ItemBookSimple> mListNewRelease = new ArrayList<>();
    private ArrayList<Book> bookList = new ArrayList<>();

    private MainActivity mMainActivity;
    private ArrayList<Category> mCategoryList = new ArrayList<>();
    private ProgressDialog mProgressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        mViewPager = (ViewPager) findViewById(R.id.viewpager_book);


        mBestSeller = (ListBookHorizontalScrollView)findViewById(R.id.list_book_best_seller);
        mCategory = (ListBookHorizontalScrollView)findViewById(R.id.list_book_category);
        mKindleEBooks = (ListBookHorizontalScrollView)findViewById(R.id.list_book_kindle_ebooks);
        mBestBookOfMonth = (ListBookHorizontalScrollView)findViewById(R.id.list_book_best_of_month);
        mSales = (ListBookHorizontalScrollView)findViewById(R.id.list_book_sales);
        mNewReleases = (ListBookHorizontalScrollView)findViewById(R.id.list_book_new_releases);

        createData();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.ic_cart) {
            Intent intent = new Intent(this, CartActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id){
            case R.id.nav_bill:
                startActivity(new Intent(this,BillActivity.class));
                break;
            case R.id.nav_cart:

                startActivity(new Intent(this,CartActivity.class));
                break;
            case R.id.nav_rate:

                break;
            case R.id.nav_not_rate:

                break;
            case R.id.nav_setting:
                break;
            case R.id.nav_signout:
                break;
           default:break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void createData() {

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();
        Call<ArrayList<ItemBookSimple>> callListTopSelling = DataController.apiBookStore.getListTopSelling();
        Call<ArrayList<ItemBookSimple>> callListBookSales = DataController.apiBookStore.getListTopSaleOff();
        Call<ArrayList<ItemBookSimple>> callListNewReleases = DataController.apiBookStore.getListNewReleases();
        Call<ArrayList<Category>> callListCategory = DataController.apiBookStore.getListCategory();
        callListTopSelling.enqueue(new Callback<ArrayList<ItemBookSimple>>() {
            @Override
            public void onResponse(Response<ArrayList<ItemBookSimple>> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    mListBestSelling = response.body();
                    mBestSeller.setDataListBook(mListBestSelling);
                    mKindleEBooks.setDataListBook(mListBestSelling);
                    mBestBookOfMonth.setDataListBook(mListBestSelling);
                    mCustomSwipeAdapter = new CustomSwipeAdapter(getApplicationContext(), mListBestSelling);
                    mViewPager.setAdapter(mCustomSwipeAdapter);
                }
                mProgressDialog.dismiss();
            }

            @Override
            public void onFailure(Throwable t) {
                ReloadActivity();
            }
        });

        callListCategory.enqueue(new Callback<ArrayList<Category>>() {
            @Override
            public void onResponse(Response<ArrayList<Category>> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    mCategoryList = response.body();
//                    Log.e("sss","mCategoryList"+mCategoryList);
                    mCategory.setDataCategory(mCategoryList);
                    mProgressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                ReloadActivity();
            }
        });

        callListBookSales.enqueue(new Callback<ArrayList<ItemBookSimple>>() {
            @Override
            public void onResponse(Response<ArrayList<ItemBookSimple>> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    mListSalesBook = response.body();
//                    Log.e("sss",""+mListSalesBook);
                    mSales.setDataListBook(mListSalesBook);
                    mProgressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                ReloadActivity();
            }
        });

        callListNewReleases.enqueue(new Callback<ArrayList<ItemBookSimple>>() {
            @Override
            public void onResponse(Response<ArrayList<ItemBookSimple>> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    mListNewRelease = response.body();
//                    Log.e("sss",""+mListNewRelease);
                    mNewReleases.setDataListBook(mListNewRelease);
                    mProgressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Throwable t) {
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
