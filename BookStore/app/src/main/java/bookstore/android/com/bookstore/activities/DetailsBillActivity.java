package bookstore.android.com.bookstore.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.ArrayList;

import bookstore.android.com.bookstore.R;
import bookstore.android.com.bookstore.adapters.CustomScrollViewBill;
import bookstore.android.com.bookstore.models.ItemBookOfBill;
import bookstore.android.com.bookstore.utils.DataController;
import bookstore.android.com.bookstore.views.custom.BillItem;

/**
 * Created by vxhuy176 on 18/12/2016.
 */

public class DetailsBillActivity extends AppCompatActivity {
    private CustomScrollViewBill mCustomScrollViewBill;
    private TextView mTotalCost,mCountItem;
    private ArrayList<ItemBookOfBill> mListItemBookOfBills = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_bill);
        mCustomScrollViewBill = (CustomScrollViewBill)findViewById(R.id.scroll_list_book_details_bill);
        mTotalCost = (TextView)findViewById(R.id.text_total_price_details_views);
        mCountItem = (TextView)findViewById(R.id.textview_count_item);
        setData();

    }
    public void setData(){

        int billId = getIntent().getExtras().getInt(BillItem.BILL_ID);
        mListItemBookOfBills = DataController.listBill.get(billId).getListBooks();
        mCustomScrollViewBill.setDataDetailsBill(mListItemBookOfBills);
        float cost = 0;
        for(int i = 0;i<mListItemBookOfBills.size();i++){
            cost+=mListItemBookOfBills.get(i).getPrice()*mListItemBookOfBills.get(i).getQuantity();
        }
        if (!mListItemBookOfBills.isEmpty()){
            mTotalCost.setText(cost+" $");
            mCountItem.setText(mListItemBookOfBills.size()+"");
        }
    }
}
