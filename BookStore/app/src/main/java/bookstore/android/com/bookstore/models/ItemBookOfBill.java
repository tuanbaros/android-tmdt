package bookstore.android.com.bookstore.models;

public class ItemBookOfBill {
    private int id;
    private String title;
    private float price;
    private int quantity;
    private String authorName;
    private String urlImage;

    public ItemBookOfBill(int id, String title, float price, int quantity, String authorName, String urlImage) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.quantity = quantity;
        this.authorName = authorName;
        this.urlImage = urlImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }
}

