package bookstore.android.com.bookstore.adapters;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import bookstore.android.com.bookstore.R;
import bookstore.android.com.bookstore.models.Bill;
import bookstore.android.com.bookstore.models.ItemBookOfBill;
import bookstore.android.com.bookstore.views.custom.BillItem;
import bookstore.android.com.bookstore.views.custom.DetailsBillItem;

/**
 * Created by vxhuy176 on 18/12/2016.
 */

public class CustomScrollViewBill extends ScrollView {
    private LinearLayout mLinearLayout;
    public CustomScrollViewBill(Context context) {
        super(context);
        init(context);
    }

    public CustomScrollViewBill(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomScrollViewBill(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    public void init(Context context) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.scrollview_bill, this, true);
    }
    public void setData(ArrayList<Bill> listBill) {
        mLinearLayout  = (LinearLayout)findViewById(R.id.linearlayout_list_bill);
        for(int i = 0;i<listBill.size();i++){
            BillItem billItem = new BillItem(this.getContext());
            MyHolder myHolder = new MyHolder(billItem.view);
            billItem.id = i;
            myHolder.mBillStatus.setText(listBill.get(i).getStatus());
            myHolder.mBillTimeBuy.setText(listBill.get(i).getTime());
            float tmp = 0;
            for(int j = 0; j<listBill.get(i).getListBooks().size();j++){
                tmp+= listBill.get(i).getListBooks().get(j).getQuantity()*listBill.get(i).getListBooks().get(j).getPrice();
            }
           if (listBill.get(i).getStatus().toLowerCase().equals("processing")){
               Picasso.with(getContext())
                       .load(R.drawable.proccessing)
                       .placeholder(R.drawable.loading)
                       .error(R.drawable.error)
                       .into(myHolder.mBillStatusImage);
           }else{
               if (listBill.get(i).getStatus().toLowerCase().equals("shipping")){
                   Picasso.with(getContext())
                           .load(R.drawable.shipping)
                           .placeholder(R.drawable.loading)
                           .error(R.drawable.error)
                           .into(myHolder.mBillStatusImage);
               }else{
                   if (listBill.get(i).getStatus().toLowerCase().equals("cancel")){
                       Picasso.with(getContext())
                               .load(R.drawable.cancel)
                               .placeholder(R.drawable.loading)
                               .error(R.drawable.error)
                               .into(myHolder.mBillStatusImage);
                   }else{
                       if (listBill.get(i).getStatus().toLowerCase().equals("completed")){
                           Picasso.with(getContext())
                                   .load(R.drawable.complete)
                                   .placeholder(R.drawable.loading)
                                   .error(R.drawable.error)
                                   .into(myHolder.mBillStatusImage);
                       }
                   }
               }
           }
            myHolder.mBillTotalCost.setText(tmp+"");
            mLinearLayout.addView(billItem);
        }
    }

    public void setDataDetailsBill(ArrayList<ItemBookOfBill> dataDetailsBill) {
        mLinearLayout  = (LinearLayout)findViewById(R.id.linearlayout_list_bill);
        for(int i = 0;i<dataDetailsBill.size();i++){
            DetailsBillItem detailsBillItem = new DetailsBillItem(this.getContext());
            MyHolderDetailBill myHolder = new MyHolderDetailBill(detailsBillItem.view);
            myHolder.mAuthor.setText(dataDetailsBill.get(i).getAuthorName());
            myHolder.mPrice.setText(dataDetailsBill.get(i).getPrice()+"");
            myHolder.mQuantity.setText(dataDetailsBill.get(i).getQuantity()+"");
            Picasso.with(getContext())
                    .load(dataDetailsBill.get(i).getUrlImage())
                    .placeholder(R.drawable.bg_loading)
                    .error(R.drawable.bg_error)
                    .into(myHolder.mBookImage);
            myHolder.mTitle.setText(dataDetailsBill.get(i).getTitle());
            mLinearLayout.addView(detailsBillItem);
        }
    }

    private class MyHolder{
        private ImageView mBillStatusImage;
        private TextView mBillStatus,mBillTimeBuy,mBillTotalCost;

        public MyHolder(View view){
            mBillStatusImage = (ImageView)view.findViewById(R.id.image_bill_status);
            mBillStatus = (TextView)view.findViewById(R.id.text_bill_status);
            mBillTimeBuy = (TextView)view.findViewById(R.id.text_bill_time);
            mBillTotalCost = (TextView)view.findViewById(R.id.text_bill_total_cost);
        }

    }

    private class MyHolderDetailBill{
        private ImageView mBookImage;
        private TextView mAuthor,mTitle,mPrice,mQuantity;

        public MyHolderDetailBill(View view){
            mBookImage = (ImageView)view.findViewById(R.id.imagebt_book_details_bill);
            mAuthor = (TextView)view.findViewById(R.id.text_author_book_details_bill);
            mTitle = (TextView)view.findViewById(R.id.text_book_name_details_bill);
            mPrice = (TextView)view.findViewById(R.id.text_price_book_details_bill);
            mQuantity = (TextView)view.findViewById(R.id.text_quantity_book_details_bill);
        }

    }
}
