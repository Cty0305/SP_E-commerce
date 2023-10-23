package layer.domain.goods;

import java.sql.Timestamp;

public class Goods {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name ;


    private float price;
    private int goods_ID;

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }
    public String brand;
    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }


    private Timestamp createdTime;
    private Timestamp updatedTime;

    public int  getGoods_ID() {
        return goods_ID;
    }

    public void setGoods_ID(int goods_ID) {
        this.goods_ID = goods_ID;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String description;
}
