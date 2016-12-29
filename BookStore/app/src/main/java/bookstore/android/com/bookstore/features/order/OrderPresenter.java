package bookstore.android.com.bookstore.features.order;

import android.content.Context;
import android.database.Cursor;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import bookstore.android.com.bookstore.models.Cart;
import bookstore.android.com.bookstore.network.RestClient;
import bookstore.android.com.bookstore.utils.DataController;

/**
 * Created by tuannt on 27/12/2016.
 */

public class OrderPresenter {

    private OrderView mOrderView;

    private Item[] items;

    public OrderPresenter(OrderView mOrderView) {
        this.mOrderView = mOrderView;
    }

    public void getItem(Context context) {
        ArrayList<Item> itemArrayList = new ArrayList<>();
        Cart cart = new Cart(context);
        cart.open();
        Cursor cursor = cart.getAllCartsFollowCartId(DataController.user.getUserId());
        if (cursor.moveToFirst())
        {
            do {
                Item item = new Item();
                item.setBook_id(cursor.getInt(2));
                item.setQuantity(cursor.getInt(4));
                item.setName(cursor.getString(5));
                itemArrayList.add(item);
            } while (cursor.moveToNext());
        }
        cart.close();
        this.items = itemArrayList.toArray(new Item[itemArrayList.size()]);
        mOrderView.getItemDone(this.items);
    }

    public void order(String name, String phone, String address) {
        Receiver receiver = new Receiver();
        receiver.setUser_id(DataController.user.getUserId());
        receiver.setUser_token(DataController.user.getUserToken());
        receiver.setName(name);
        receiver.setPhone(phone);
        receiver.setAddress(address);
        Gson gson = new Gson();
        String user = gson.toJson(receiver);
        String cart = gson.toJson(this.items);

        AndroidNetworking.post(RestClient.url + "user/bill/store")
                .addBodyParameter("user", user)
                .addBodyParameter("carts", cart)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.getString("status").equals("success")) {
                                mOrderView.orderSuccess("success");
                            } else
                                mOrderView.orderError(response.getString("error"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                            mOrderView.orderError("error");
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        mOrderView.orderError("error");
                    }
                });
    }

    public void deleteCart(Context context) {
        Cart cart = new Cart(context);
        cart.open();
        Cursor cursor = cart.getAllCartsFollowCartId(DataController.user.getUserId());
        if (cursor.moveToFirst())
        {
            do {
                cart.deleteCart(cursor.getInt(0));
            } while (cursor.moveToNext());
        }
        cart.close();
    }

}
