package bookstore.android.com.bookstore.features.getbought;


/**
 * Created by tuannt on 28/12/2016.
 */

public interface GetBoughtView {

    void getBoughtSuccess(Book[] books);

    void getBoughtError();

}
