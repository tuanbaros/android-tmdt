package bookstore.android.com.bookstore.models;

/**
 * Created by vxhuy176 on 05/12/2016.
 */

public class Review {
    private int id;
    private String content;
    private String urlImageAvatar;
    private String username;
    private int rating;
    private String time;

    public Review(String content, String username, int rating, String time) {
        this.content = content;
        this.username = username;
        this.rating = rating;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUrlImageAvatar() {
        return urlImageAvatar;
    }

    public void setUrlImageAvatar(String urlImageAvatar) {
        this.urlImageAvatar = urlImageAvatar;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
