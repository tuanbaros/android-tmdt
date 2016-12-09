package bookstore.android.com.bookstore.models;

/**
 * Created by vxhuy176 on 06/12/2016.
 */
public class View {
    private int id;
    private int bookId; //để xem người dùng đã xem sách nào

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
}
