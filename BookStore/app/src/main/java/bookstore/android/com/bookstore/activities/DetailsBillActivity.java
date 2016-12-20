package bookstore.android.com.bookstore.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.ArrayList;

import bookstore.android.com.bookstore.R;
import bookstore.android.com.bookstore.adapters.CustomScrollViewBill;
import bookstore.android.com.bookstore.models.ItemBookOfBill;

/**
 * Created by vxhuy176 on 18/12/2016.
 */

public class DetailsBillActivity extends AppCompatActivity {
    private CustomScrollViewBill mCustomScrollViewBill;
    private TextView mTotalCost;
    private ArrayList<ItemBookOfBill> mListItemBookOfBills = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_bill);
        mCustomScrollViewBill = (CustomScrollViewBill)findViewById(R.id.scroll_list_book_details_bill);
        setData();
        mTotalCost = (TextView)findViewById(R.id.text_bill_total_cost);
        mCustomScrollViewBill.setDataDetailsBill(mListItemBookOfBills);

    }
    public void setData(){
        mListItemBookOfBills.add(new ItemBookOfBill(1,"Cuu Duong Than Cong",100,2,"Huy","aaaa"));
        mListItemBookOfBills.add(new ItemBookOfBill(1,"Cuu Duong Than Cong",100,2,"Huy","aaaa"));
        mListItemBookOfBills.add(new ItemBookOfBill(1,"Cuu Duong Than Cong",100,2,"Huy","aaaa"));
        mListItemBookOfBills.add(new ItemBookOfBill(1,"Cuu Duong Than Cong",100,2,"Huy","aaaa"));
        mListItemBookOfBills.add(new ItemBookOfBill(1,"Cuu Duong Than Cong",100,2,"Huy","aaaa"));
    }
}
