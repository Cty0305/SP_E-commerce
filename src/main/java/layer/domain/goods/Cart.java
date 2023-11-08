package layer.domain.goods;

import java.util.List;

public class Cart {
    public String getCart_ID() {
        return cart_ID;
    }

    public void setCart_ID(String cart_ID) {
        this.cart_ID = cart_ID;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }


    private String cart_ID;
    private String account;

    public String getGoods_ID() {
        return goods_ID;
    }

    public void setGoods_ID(String goods_ID) {
        this.goods_ID = goods_ID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    private String goods_ID;
    private int quantity;



}
