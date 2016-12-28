package bookstore.android.com.bookstore.features.getbought;

import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.util.HashMap;

import bookstore.android.com.bookstore.utils.DataController;

/**
 * Created by tuannt on 28/12/2016.
 */

public class GetBoughtPresenter {

    private GetBoughtView mGetBoughtView;

    public GetBoughtPresenter(GetBoughtView mGetBoughtView) {
        this.mGetBoughtView = mGetBoughtView;
    }

    public void getBought() {
        Log.i("bookabc", DataController.user.getUserId()+"");
        Log.i("bookabc", DataController.user.getUserToken()+"");
        AndroidNetworking.post("http://android-samsung.herokuapp.com/api/user/book")
                .addBodyParameter("user_id", "" + DataController.user.getUserId())
                .addBodyParameter("user_token", DataController.user.getUserToken())
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.i("bookabc", response.toString());
                        Gson gson = new Gson();
                        Book[] items = gson.fromJson(response.toString(), Book[].class);
                        mGetBoughtView.getBoughtSuccess(items);
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.i("bookabc", anError.toString());
                        mGetBoughtView.getBoughtError();
                    }
                });
    }
}
