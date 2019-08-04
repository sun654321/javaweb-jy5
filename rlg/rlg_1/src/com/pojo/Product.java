package com.pojo;

import java.sql.Blob;

public class Product {
private  int id;
private  int  categoryid;
private  String name;
private   String  subtitle;
private    String mainimage;
private  int status;
private  float  price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(int categoryid) {
        this.categoryid = categoryid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getMainimage() {
        return mainimage;
    }

    public void setMainimage(String mainimage) {
        this.mainimage = mainimage;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", categoryid=" + categoryid +
                ", name='" + name + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", mainimage=" + mainimage +
                ", status=" + status +
                ", price=" + price +
                '}';
    }
}
