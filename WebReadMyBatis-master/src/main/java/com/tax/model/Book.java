package com.tax.model;

/**ͼ��ʵ�� Bean POJO*/
public class Book {
    /**���*/
    private int id;
    /**����*/
    private String title;
    /**����*/
    private String typename;
    /**�۸�*/
    private Double price;
    /**״̬*/
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
