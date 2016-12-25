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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import bookstore.android.com.bookstore.R;

/**
 * Created by toan on 21/12/2016.
 */
public class SearchActivity extends AppCompatActivity {
    public static final String TAG = "SearchActivity";
    ActionBar actionBar;
    ProgressBar progressBar;
    GridView gridView;
    TextView resultSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchresult);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(0xFFFFFF);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        progressBar = (ProgressBar) findViewById(R.id.searchProgress);
        gridView = (GridView) findViewById(R.id.gridviewSearch);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem actionSearch = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) actionSearch.getActionView();
        searchView.setIconifiedByDefault(false);
        searchView.setQueryHint("Enter the key");

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
}

