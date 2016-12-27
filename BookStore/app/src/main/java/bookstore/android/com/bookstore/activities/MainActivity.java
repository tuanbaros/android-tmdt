package bookstore.android.com.bookstore.activities;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.login.widget.ProfilePictureView;

import org.json.JSONObject;

import java.util.ArrayList;

import bookstore.android.com.bookstore.R;
import bookstore.android.com.bookstore.adapters.CustomSwipeAdapter;
import bookstore.android.com.bookstore.features.auth.Token;
import bookstore.android.com.bookstore.models.Book;
import bookstore.android.com.bookstore.models.Category;
import bookstore.android.com.bookstore.adapters.ListBookHorizontalScrollView;
import bookstore.android.com.bookstore.models.ItemBookSimple;
import bookstore.android.com.bookstore.models.User;
import bookstore.android.com.bookstore.utils.DataController;
import bookstore.android.com.bookstore.utils.Variables;
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

    private JSONObject response, profile_pic_data, profile_pic_url;

    private MainActivity mMainActivity;
    private ArrayList<Category> mCategoryList = new ArrayList<>();
    private ProgressDialog mProgressDialog;
    private NavigationView navigation_view;
    private TextView user_name,userId;
    private ProfilePictureView user_picture;
    private FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent i=getIntent();

        setNavigationHeader();
        setUserProfile(DataController.user);

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
        fab=(FloatingActionButton)findViewById(R.id.fabSearch);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itSearch=new Intent(MainActivity.this,SearchActivity.class);
                startActivity(itSearch);
            }
        });

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void setUserProfile(User user) {
        try {
            user_name.setText(user.getName());
            userId.setText(user.getFbId());
            user_picture.setProfileId(String.valueOf(user.getFbId()));
            Toast.makeText(MainActivity.this,user.getFbId(),Toast.LENGTH_LONG).show();
            Toast.makeText(MainActivity.this,user.getName(),Toast.LENGTH_LONG).show();
//            profile_pic_data = new JSONObject(response.get("picture").toString());
//            profile_pic_url = new JSONObject(profile_pic_data.getString("data"));
//
//            Picasso.with(this).load(profile_pic_url.getString("url"))
//                    .into(user_picture);

        } catch (Exception e){
            e.printStackTrace();
            Toast.makeText(MainActivity.this,"error",Toast.LENGTH_LONG);
        }
    }

    private void setNavigationHeader() {
        navigation_view = (NavigationView) findViewById(R.id.nav_view);

        View header = LayoutInflater.from(this).inflate(R.layout.nav_header_main, null);
        navigation_view.addHeaderView(header);

        user_name = (TextView) header.findViewById(R.id.username);
        userId=(TextView) header.findViewById(R.id.userid);
        user_picture = (ProfilePictureView) header.findViewById(R.id.profile_image);
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
        // Inflate the menu; this adds mListItems to the action bar if it is present.
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
        if(id==R.id.nav_login){
            Intent i=new Intent(getApplicationContext(),LoginActivity.class);
            startActivity(i);
        }
//
//        if (id == R.id.nav_camera) {
//            // Handle the camera action
//        } else if (id == R.id.nav_gallery) {
//
//        } else if (id == R.id.nav_slideshow) {
//
//        } else if (id == R.id.nav_manage) {
//
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }

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
                    for (Category category:
                         mCategoryList) {
                        Variables.categoryHashMap.put(category.getId(), category.getName());
                    }
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (DataController.user != null)
            Token.store(this, DataController.user.getUserToken(), DataController.user.getUserId());
    }
}
