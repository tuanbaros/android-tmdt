package bookstore.android.com.bookstore.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import bookstore.android.com.bookstore.R;
import bookstore.android.com.bookstore.databinding.ActivityBoughtBinding;
import bookstore.android.com.bookstore.features.getbought.BoughtAdapter;
import bookstore.android.com.bookstore.features.getbought.GetBoughtPresenter;
import bookstore.android.com.bookstore.features.getbought.GetBoughtView;
import bookstore.android.com.bookstore.models.Book;
import bookstore.android.com.bookstore.utils.DataController;

public class BoughtActivity extends AppCompatActivity implements GetBoughtView {

    private ActivityBoughtBinding mBoughtBinding;

    private GetBoughtPresenter mGetBoughtPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBoughtBinding = DataBindingUtil.setContentView(this, R.layout.activity_bought);
        mGetBoughtPresenter = new GetBoughtPresenter(this);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("My book");
        }

        mGetBoughtPresenter.getBought();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return true;
        }

    }

    @Override
    public void getBoughtSuccess(bookstore.android.com.bookstore.features.getbought.Book[] books) {
        mBoughtBinding.boughtProgressBar.setVisibility(View.GONE);
        mBoughtBinding.boughtListView.setAdapter(new BoughtAdapter(this, books));
    }

    @Override
    public void getBoughtError() {
        mBoughtBinding.boughtProgressBar.setVisibility(View.GONE);
        showAlertDialog("Hi, " + DataController.user.getName(), "You haven't any books!");
    }

    private void showAlertDialog(final String title, String content) {
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // set title
        alertDialogBuilder.setTitle(title);

        // set dialog message
        alertDialogBuilder
                .setMessage(content)
                .setCancelable(false)
                .setPositiveButton("OK",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {

                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }
}
