package Library_Management_System;

public class Book {
    static int count = 1;
    private int id;
    private String title;
    private String author;
    private String activeyear;
    private String publisher;
    private boolean status;

    public Book(String title, String author, String activeyear, String publisher, boolean status) {
        this.id = count++;
        this.title = title;
        this.author = author;
        this.activeyear = activeyear;
        this.publisher = publisher;
        this.status = status;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getActiveyear() {
        return activeyear;
    }

    public void setActiveyear(String activeyear) {
        this.activeyear = activeyear;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status ? true : false;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", activeyear=" + activeyear +
                ", publisher='" + publisher + '\'' +
                ", status=" + status +
                '}';
    }
}
