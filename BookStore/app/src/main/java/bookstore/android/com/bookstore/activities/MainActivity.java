package bookstore.android.com.bookstore.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
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

    public void createData(){

        ApiBookStore apiBookStore = RestClient.getClient().create(ApiBookStore.class);
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.show();
        Call<ArrayList<ItemBookSimple>> callListTopSelling = apiBookStore.getListTopSelling();
        Call<ArrayList<ItemBookSimple>> callListBookSales = apiBookStore.getListTopSaleOff();
        Call<ArrayList<ItemBookSimple>> callListNewReleases = apiBookStore.getListNewReleases();
        Call<ArrayList<Category>> callListCategory = apiBookStore.getListCategory();
        callListTopSelling.enqueue(new Callback<ArrayList<ItemBookSimple>>() {
            @Override
            public void onResponse(Response<ArrayList<ItemBookSimple>> response, Retrofit retrofit) {
                if(response.isSuccess()){
                    mListBestSelling = response.body();
                    mBestSeller.setDataListBook(mListBestSelling);
                    mKindleEBooks.setDataListBook(mListBestSelling);
                    mBestBookOfMonth.setDataListBook(mListBestSelling);
                    mCustomSwipeAdapter = new CustomSwipeAdapter(getApplicationContext(),mListBestSelling);
                    mViewPager.setAdapter(mCustomSwipeAdapter);
                }
                mProgressDialog.dismiss();
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getApplicationContext(),"mListBestSelling???",Toast.LENGTH_SHORT).show();
            }
        });

        callListCategory.enqueue(new Callback<ArrayList<Category>>() {
            @Override
            public void onResponse(Response<ArrayList<Category>> response, Retrofit retrofit) {
                if(response.isSuccess()){
                    mCategoryList = response.body();
                    mCategory.setDataCategory(mCategoryList);

                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getApplicationContext(),"mCategoryList???",Toast.LENGTH_SHORT).show();
            }
        });

        callListBookSales.enqueue(new Callback<ArrayList<ItemBookSimple>>() {
            @Override
            public void onResponse(Response<ArrayList<ItemBookSimple>> response, Retrofit retrofit) {
                if(response.isSuccess()){
                    mListSalesBook = response.body();
                    mSales.setDataListBook(mListSalesBook);

                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getApplicationContext(),"mListSalesBook???",Toast.LENGTH_SHORT).show();
            }
        });

        callListNewReleases.enqueue(new Callback<ArrayList<ItemBookSimple>>() {
            @Override
            public void onResponse(Response<ArrayList<ItemBookSimple>> response, Retrofit retrofit) {
                if(response.isSuccess()){
                    mListNewRelease = response.body();
                    mNewReleases.setDataListBook(mListNewRelease);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getApplicationContext(),"mListNewRelease???",Toast.LENGTH_SHORT).show();
            }
        });


        Book book = new Book("Cửu âm Bạch cốt trảo 1 + Hang long thap bat skill",new Author("Quách Tương"),100000,200000);
        bookList.add(book);
        Book book1 = new Book("Cửu âm Bạch cốt trảo 2",new Author("Quách Tương"),100000,200000);
        bookList.add(book1);
        Book book2 = new Book("Cửu âm Bạch cốt trảo 3",new Author("Quách Tương"),100000,200000);
        bookList.add(book2);
        Book book3 = new Book("Cửu âm Bạch cốt trảo 4",new Author("Quách Tương"),100000,200000);
        bookList.add(book3);
        Book book4 = new Book("Cửu âm Bạch cốt trảo 5",new Author("Quách Tương"),100000,200000);
        bookList.add(book4);
        Book book5 = new Book("Cửu âm Bạch cốt trảo 6",new Author("Quách Tương"),100000,200000);
        bookList.add(book5);
//        Category category = new Category("Skill1");
//        categoryList.add(category);
//        Category category1 = new Category("Skill2");
//        categoryList.add(category1);
//        Category category2 = new Category("Skill3");
//        categoryList.add(category2);
//        Category category3 = new Category("Skill4");
//        categoryList.add(category3);
//        Category category4 = new Category("Skill5");
//        categoryList.add(category4);
    }
}
