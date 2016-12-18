package bookstore.android.com.bookstore.models;

import java.util.ArrayList;

/**
 * Created by vxhuy176 on 05/12/2016.
 */

public class Author {
    private int id;
    private String name;
    private int rateBookAverage;
    private int TotalSold;
    private String recommend;
    private String contact;
    private String avatar;
    private ArrayList<BookOfAuthor> listBooks = new ArrayList<>();

    public Author(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getRateBookAverage() {
        return rateBookAverage;
    }

    public void setRateBookAverage(int rateBookAverage) {
        this.rateBookAverage = rateBookAverage;
    }

    public int getTotalSold() {
        return TotalSold;
    }

    public void setTotalSold(int totalSold) {
        TotalSold = totalSold;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }


    public ArrayList<BookOfAuthor> getListBooks() {
        return listBooks;
    }

    public void setListBooks(ArrayList<BookOfAuthor> listBooks) {
        this.listBooks = listBooks;
    }

    private class BookOfAuthor{
        private int idBook;
        private String urlImage;
        private String title;
        private float rateAverage;
        private float price;

        public int getIdBook() {
            return idBook;
        }

        public void setIdBook(int idBook) {
            this.idBook = idBook;
        }

        public String getUrlImage() {
            return urlImage;
        }

        public void setUrlImage(String urlImage) {
            this.urlImage = urlImage;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public float getRateAverage() {
            return rateAverage;
        }

        public void setRateAverage(float rateAverage) {
            this.rateAverage = rateAverage;
        }

        public float getPrice() {
            return price;
        }

        public void setPrice(float price) {
            this.price = price;
        }
    }
}
