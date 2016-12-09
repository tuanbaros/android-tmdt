package bookstore.android.com.bookstore.models;

/**
 * Created by vxhuy176 on 05/12/2016.
 */

public class Rate {
    private int id;
    private int point;
    private int bookId; //để biết user đã rate cho sách nào.

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

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

}
