package bookstore.android.com.bookstore.features.order;

/**
 * Created by tuannt on 27/12/2016.
 */

public interface OrderView {
    void getItemDone(Item[] items);

    void orderSuccess(String success);

    void orderError(String error);
}
