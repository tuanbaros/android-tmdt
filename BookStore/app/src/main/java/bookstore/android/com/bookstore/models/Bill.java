package bookstore.android.com.bookstore.models;

/**
 * Created by vxhuy176 on 05/12/2016.
 */

public class Bill {
    private int id;
    private User user;
    private Cart cart;
    private String time;
    private CartBook cartBook; //biến dùng nếu người dùng mua mà không cho vào trong cart.
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public CartBook getCartBook() {
        return cartBook;
    }

    public void setCartBook(CartBook cartBook) {
        this.cartBook = cartBook;
    }
}
