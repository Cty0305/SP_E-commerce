package layer.domain.goods;

import java.sql.Timestamp;
import java.util.Comparator;

public class Goods {
    public static Comparator<Goods> compareByName = Comparator.comparing(Goods::getName);
    public static Comparator<Goods> compareByPrice = Comparator.comparing(Goods::getPrice);
    public static Comparator<Goods> compareByCategory = Comparator.comparing(Goods::getCategory);
    public static Comparator<Goods> compareByCreatedTime = Comparator.comparing(Goods::getCreatedTime);
    public static Comparator<Goods> reversedCompareByName = compareByName.reversed();
    public static Comparator<Goods> reversedCompareByPrice = compareByPrice.reversed();
    public static Comparator<Goods> reversedCompareByCategory = compareByCategory.reversed();
    public static Comparator<Goods> reversedCompareCreatedTime = compareByCreatedTime.reversed();



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name ;



    public Timestamp getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Timestamp updatedTime) {
        this.updatedTime = updatedTime;
    }


    private float price;
    private String goods_ID;

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }
    public String category;
    public void setCategory(String category) {
        this.category = category;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }


    private Timestamp createdTime;
    private Timestamp updatedTime;


    public String getGoods_ID() {
        return goods_ID;
    }

    public void setGoods_ID(String goods_ID) {
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
