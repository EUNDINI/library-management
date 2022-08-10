package ddwucom.mobile.finalreport;

import java.io.Serializable;

public class MyData implements Serializable {
    private long _id;
    private int image;
    private String book_title;
    private String author;
    private String publisher;
    private int price;
    private int number_of_pages; // 쪽수

    public MyData(String book_title, String author, String publisher) {
        this.book_title = book_title;
        this.author = author;
        this.publisher = publisher;
        this.price  = 10000;
    }

    public MyData(long _id, int image, String book_title, String author, String publisher, int price, int number_of_pages) {
        this._id = _id;
        this.image = image;
        this.book_title = book_title;
        this.author = author;
        this.publisher = publisher;
        this.price = price;
        this.number_of_pages = number_of_pages;
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getBook_title() {
        return book_title;
    }

    public void setBook_title(String book_title) {
        this.book_title = book_title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getNumber_of_pages() {
        return number_of_pages;
    }

    public void setNumber_of_pages(int number_of_pages) {
        this.number_of_pages = number_of_pages;
    }

    // 나중에 수정
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_id);
        sb.append(".");
        sb.append(book_title);
        sb.append(".");
        sb.append(author);
        sb.append(".");
        sb.append(publisher);
        sb.append(".");
        sb.append(price);
        sb.append(".");
        sb.append(number_of_pages);
        return sb.toString();
    }
}
