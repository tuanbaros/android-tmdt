package bookstore.android.com.bookstore.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
public class SearchActivity extends AppCompatActivity {
    ActionBar actionBar;
    ProgressBar progressBar;
    GridView gridView;
    ArrayList<ItemBookSimple>list;
    BookGridviewAdapter adapter;
    ArrayList<ItemBookSimple> mListItems;

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
        progressBar = (ProgressBar) findViewById(R.id.searchProgress);
        gridView = (GridView) findViewById(R.id.gridviewSearch);
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
                String newQuery=query.trim();
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
    private void searchBook(String newQuery){
        mListItems =new ArrayList<>();
        Call<ArrayList<ItemBookSimple>> books= DataController.apiBookStore.getListBooks(newQuery);
        books.enqueue(new Callback<ArrayList<ItemBookSimple>>() {
            @Override
            public void onResponse(Response<ArrayList<ItemBookSimple>> response, Retrofit retrofit) {
                if(response.isSuccess()){
                    mListItems =response.body();
                    adapter=new BookGridviewAdapter(getApplicationContext(),R.layout.item_book,new ArrayList<ItemBookSimple>(mListItems));
                    gridView.setAdapter(adapter);
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}

