package layer.domain.goods;

import java.util.List;

public class Cart {
    public String getCart_ID() {
        return cart_ID;
    }

    public String getGoods_item_ID() {
        return goods_item_ID;
    }

    public void setGoods_item_ID(String goods_item_ID) {
        this.goods_item_ID = goods_item_ID;
    }

    String goods_item_ID;
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


    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    private Goods goods;

    public layer.domain.goods.goodsItem getGoodsItem() {
        return goodsItem;
    }

    public void setGoodsItem(layer.domain.goods.goodsItem goodsItem) {
        this.goodsItem = goodsItem;
    }

    private goodsItem goodsItem;




    private int quantity;

    public String transSizeToText(int size){
        String m = "M";
        String l = "L";
        String s = "S";
        if(size==1){
            return s;
        }else if(size==2){
            return m;
        }else if(size==3){
            return l;
        }else{
            return "";
        }
    }

}
