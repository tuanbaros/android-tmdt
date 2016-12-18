package bookstore.android.com.bookstore.models;

import java.util.ArrayList;

/**
 * Created by vxhuy176 on 18/12/2016.
 */

public class TipBooks {
    private ArrayList<ItemBookSimple> listBooks = new ArrayList<>();
    private String tip;

    public ArrayList<ItemBookSimple> getListBooks() {
        return listBooks;
    }

    public void setListBooks(ArrayList<ItemBookSimple> listBooks) {
        this.listBooks = listBooks;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }


    public class ItemBookSimple {
        private int id;
        private String title;
        private float price;
        private String authorName;
        private String urlImage;

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
}
