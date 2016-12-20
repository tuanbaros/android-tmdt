package bookstore.android.com.bookstore.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by vxhuy176 on 05/12/2016.
 */

public class Category {
    @SerializedName("id")
    private int id;
    @SerializedName("urlImage")
    private String image;
    @SerializedName("name")
    private String name;

    public Category(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;

    }

}
