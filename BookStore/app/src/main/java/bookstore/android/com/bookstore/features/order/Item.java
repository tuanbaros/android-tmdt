package bookstore.android.com.bookstore.features.order;

/**
 * Created by tuannt on 27/12/2016.
 */

public class Item {
    private int book_id;
    private int quantity;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
