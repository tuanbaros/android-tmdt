package bookstore.android.com.bookstore.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import bookstore.android.com.bookstore.R;
import bookstore.android.com.bookstore.databinding.ActivityOrderBinding;
import bookstore.android.com.bookstore.features.order.Item;
import bookstore.android.com.bookstore.features.order.ItemAdapter;
import bookstore.android.com.bookstore.features.order.OrderPresenter;
import bookstore.android.com.bookstore.features.order.OrderView;

public class OrderActivity extends AppCompatActivity implements OrderView, View.OnClickListener {

    private ActivityOrderBinding mOrderBinding;

    private OrderPresenter mOrderPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mOrderBinding = DataBindingUtil.setContentView(this, R.layout.activity_order);
        mOrderPresenter = new OrderPresenter(this);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Info");
        }
        setViewDisabled();
        mOrderBinding.orderButton.setText("Order " + "(" + getIntent().getStringExtra("total") + ")");
        mOrderPresenter.getItem(this);
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
    public void getItemDone(Item[] items) {
        mOrderBinding.bookListView.setAdapter(new ItemAdapter(this, items));
        setViewEnabled();
        mOrderBinding.orderButton.setOnClickListener(this);
    }

    @Override
    public void orderSuccess(String success) {
        mOrderPresenter.deleteCart(this);
        showAlertDialog("Success", "We will call you as soon as to confirm");
    }

    @Override
    public void orderError(String error) {
        showAlertDialog("Error", error);
    }

    @Override
    public void onClick(View view) {
        String name = mOrderBinding.receiverEditText.getText().toString();
        String phone = mOrderBinding.phoneEditText.getText().toString();
        String address = mOrderBinding.addressEditText.getText().toString();
        if (name.matches("") || phone.matches("") || address.matches("")) {
            Toast.makeText(this, "You did not enter all information", Toast.LENGTH_SHORT).show();
        } else {
            mOrderBinding.orderProgressBar.setVisibility(View.VISIBLE);
            setViewDisabled();
            mOrderPresenter.order(name, phone, address);
        }
    }

    private void setViewEnabled() {
        mOrderBinding.orderButton.setEnabled(true);
        mOrderBinding.phoneEditText.setEnabled(true);
        mOrderBinding.addressEditText.setEnabled(true);
        mOrderBinding.receiverEditText.setEnabled(true);
        mOrderBinding.orderProgressBar.setVisibility(View.GONE);
    }

    private void setViewDisabled() {
        mOrderBinding.orderButton.setEnabled(false);
        mOrderBinding.phoneEditText.setEnabled(false);
        mOrderBinding.addressEditText.setEnabled(false);
        mOrderBinding.receiverEditText.setEnabled(false);
        mOrderBinding.orderProgressBar.setVisibility(View.VISIBLE);
    }

    private void showAlertDialog(final String title, String content) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // set title
        alertDialogBuilder.setTitle(title);

        // set dialog message
        alertDialogBuilder
                .setMessage(content)
                .setCancelable(false)
                .setPositiveButton("OK",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        if (title.equals("Success")) {
                            Intent intent = new Intent(getBaseContext(), BillActivity.class);
                            startActivity(intent);
                            OrderActivity.this.finish();
                        } else {
                            setViewEnabled();
                        }
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }
}
