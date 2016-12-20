package bookstore.android.com.bookstore.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import bookstore.android.com.bookstore.R;
import bookstore.android.com.bookstore.adapters.CustomScrollViewBill;
import bookstore.android.com.bookstore.models.Bill;
import bookstore.android.com.bookstore.models.ItemBookOfBill;
import bookstore.android.com.bookstore.network.ApiBookStore;
import bookstore.android.com.bookstore.network.RestClient;

/**
 * Created by vxhuy176 on 16/12/2016.
 */

public class BillActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private ArrayList<Bill> mListBill = new ArrayList<>();
    private Spinner mFilterBill;
    private CustomScrollViewBill mCustomScrollViewBill;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        setDataBill();
        mFilterBill = (Spinner)findViewById(R.id.spiner_type_bill);
        mCustomScrollViewBill = (CustomScrollViewBill)findViewById(R.id.scrollview_list_bill);
        mCustomScrollViewBill.setData(mListBill);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(),
                R.array.spinner_type_bill, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mFilterBill.setAdapter(adapter);
        mFilterBill.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.ic_home:
                onBackPressed();
                break;
            default:break;
        }
        return super.onOptionsItemSelected(item);
    }
    private void setDataBill(){




//        ArrayList<ItemBookOfBill> listItemBookOfBills = new ArrayList<>();
//        listItemBookOfBills.add(new  ItemBookOfBill(1,"Cuu duong than cong",(float)10.5,2,"Huy","xxxx"));
//        listItemBookOfBills.add(new  ItemBookOfBill(1,"Cuu duong than cong",(float)10.5,2,"Huy","xxxx"));
//        listItemBookOfBills.add(new  ItemBookOfBill(1,"Cuu duong than cong",(float)10.5,2,"Huy","xxxx"));
//        mListBill.add(new Bill(1,"Processing","18/12/2016",listItemBookOfBills));
//        mListBill.add(new Bill(1,"Processing","18/12/2016",listItemBookOfBills));
//        mListBill.add(new Bill(1,"Processing","18/12/2016",listItemBookOfBills));
//        mListBill.add(new Bill(1,"Processing","18/12/2016",listItemBookOfBills));
//        mListBill.add(new Bill(1,"Processing","18/12/2016",listItemBookOfBills));
    }

}
