package bookstore.android.com.bookstore.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

import bookstore.android.com.bookstore.R;
import bookstore.android.com.bookstore.adapters.CustomScrollViewBill;
import bookstore.android.com.bookstore.models.Bill;
import bookstore.android.com.bookstore.utils.DataController;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by vxhuy176 on 16/12/2016.
 */

public class BillActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    public int id;
    private Spinner mFilterBill;
    private CustomScrollViewBill mCustomScrollViewBill;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);

        mFilterBill = (Spinner)findViewById(R.id.spiner_type_bill);
        mCustomScrollViewBill = (CustomScrollViewBill)findViewById(R.id.scrollview_list_bill);
        setDataBill();
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
        Call<ArrayList<Bill>> callListBill = DataController.apiBookStore.getBill(DataController.user.getUserId());
        callListBill.enqueue(new Callback<ArrayList<Bill>>() {
            @Override
            public void onResponse(Response<ArrayList<Bill>> response, Retrofit retrofit) {
                if(response.isSuccess()){
                    DataController.listBill = response.body();
                    mCustomScrollViewBill.setData(DataController.listBill);
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

}
