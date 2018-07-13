package com.tax.model;

/**图书实体 Bean POJO*/
public class Book {
    /**编号*/
    private int id;
    /**书名*/
    private String title;
    /**类型*/
    private String typename;
    /**价格*/
    private Double price;
    /**状态*/
    private String state;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", typename='" + typename + '\'' +
                ", price=" + price +
                ", state='" + state + '\'' +
                '}';
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

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
