package bookstore.android.com.bookstore.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ProgressBar;

import java.util.ArrayList;

import bookstore.android.com.bookstore.R;
import bookstore.android.com.bookstore.adapters.BookGridviewAdapter;
import bookstore.android.com.bookstore.models.ItemBookSimple;
import bookstore.android.com.bookstore.utils.DataController;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by toan on 21/12/2016.
 */
public class SearchActivity extends AppCompatActivity implements View.OnClickListener {
    ActionBar actionBar;
    ProgressBar progressBar;
    GridView gridView;
    ArrayList<ItemBookSimple>list;
    BookGridviewAdapter adapter;
    ArrayList<ItemBookSimple> mListItems =new ArrayList<>();;
    Button mMoreSearch;
    int countItem = 0;
    String newQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchresult);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(0xFFFFFF);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        list=new ArrayList<>();
        countItem = 0;
        mMoreSearch = (Button)findViewById(R.id.bt_more_search);
        progressBar = (ProgressBar) findViewById(R.id.searchProgress);
        gridView = (GridView) findViewById(R.id.gridviewSearch);
        mMoreSearch.setOnClickListener(this);
        setSpecialSearch();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds mListItems to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem actionSearch = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) actionSearch.getActionView();
        searchView.setIconifiedByDefault(false);
        searchView.setQueryHint("Enter the key");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                countItem = 0;
                DataController.isSpecialSearch = false;
                list.clear();
                mListItems.clear();
                newQuery=query.trim();
                progressBar.setVisibility(View.VISIBLE);
                searchBook(newQuery);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.bt_more_search){
            if (DataController.isSpecialSearch){
                setSpecialSearch();
            }else{
                searchBook(newQuery);
            }
        }
    }
    private void searchBook(String newQuery){


        Call<ArrayList<ItemBookSimple>> books= DataController.apiBookStore.getListBooks(newQuery,countItem);
        books.enqueue(new Callback<ArrayList<ItemBookSimple>>() {
            @Override
            public void onResponse(Response<ArrayList<ItemBookSimple>> response, Retrofit retrofit) {
                if(response.isSuccess()){

                    mListItems.addAll(response.body());
                    countItem+=10;
                    adapter=new BookGridviewAdapter(getApplicationContext(),R.layout.item_book,mListItems);
                    gridView.setAdapter(adapter);
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

    }

    public void setSpecialSearch(){
        progressBar.setVisibility(View.VISIBLE);
        if(DataController.isSpecialSearch){
            Log.e("sss", "setSpecialSearch: "+ DataController.type_search);

            switch (DataController.type_search){
                case R.id.bt_more_best_seller:
                    Call<ArrayList<ItemBookSimple>> call = DataController.apiBookStore.getListTopSelling(countItem);
                    call.enqueue(new Callback<ArrayList<ItemBookSimple>>() {
                        @Override
                        public void onResponse(Response<ArrayList<ItemBookSimple>> response, Retrofit retrofit) {
                            if (response.isSuccess()){
                                list.addAll(response.body());
                                countItem+=10;
                                adapter=new BookGridviewAdapter(getApplicationContext(),R.layout.item_book,list);
                                gridView.setAdapter(adapter);
                                progressBar.setVisibility(View.GONE);
                            }
                        }

                        @Override
                        public void onFailure(Throwable t) {
                            t.printStackTrace();
                        }
                    });
                    break;
                case R.id.bt_more_new_releases:
                    Call<ArrayList<ItemBookSimple>> callnewreleases = DataController.apiBookStore.getListNewReleases(countItem);
                    callnewreleases.enqueue(new Callback<ArrayList<ItemBookSimple>>() {
                        @Override
                        public void onResponse(Response<ArrayList<ItemBookSimple>> response, Retrofit retrofit) {
                            if (response.isSuccess()){
                                list.addAll(response.body());
                                countItem+=10;
                                adapter=new BookGridviewAdapter(getApplicationContext(),R.layout.item_book,list);
                                gridView.setAdapter(adapter);
                                progressBar.setVisibility(View.GONE);
                            }
                        }

                        @Override
                        public void onFailure(Throwable t) {
                            t.printStackTrace();
                        }
                    });
                    break;
                case R.id.bt_more_sales:
                    Call<ArrayList<ItemBookSimple>> callsales = DataController.apiBookStore.getListTopSaleOff(countItem);
                    callsales.enqueue(new Callback<ArrayList<ItemBookSimple>>() {
                        @Override
                        public void onResponse(Response<ArrayList<ItemBookSimple>> response, Retrofit retrofit) {
                            if (response.isSuccess()){
                                list.addAll(response.body());
                                countItem+=10;
                                adapter=new BookGridviewAdapter(getApplicationContext(),R.layout.item_book,list);
                                gridView.setAdapter(adapter);
                                progressBar.setVisibility(View.GONE);
                            }
                        }

                        @Override
                        public void onFailure(Throwable t) {
                            t.printStackTrace();
                        }
                    });
                    break;
                default: setSearchCategory();
                    break;
            }


        }
        progressBar.setVisibility(View.GONE);
    }

    private void setSearchCategory() {
        Call<ArrayList<ItemBookSimple>> call = DataController.apiBookStore.getListBookInCategory(DataController.type_search,countItem);
        call.enqueue(new Callback<ArrayList<ItemBookSimple>>() {
            @Override
            public void onResponse(Response<ArrayList<ItemBookSimple>> response, Retrofit retrofit) {
                if (response.isSuccess()){
                    list.addAll(response.body());
                    countItem+=10;
                    adapter=new BookGridviewAdapter(getApplicationContext(),R.layout.item_book,list);
                    gridView.setAdapter(adapter);
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        });
    }

}

