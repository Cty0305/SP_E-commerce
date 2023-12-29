package layer.domain.goods;

public class goodsItem {
    public Goods goods = new Goods();

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getGoodsItem_ID() {
        return goodsItem_ID;
    }

    public void setGoodsItem_ID(String goodsItem_ID) {
        this.goodsItem_ID = goodsItem_ID;
    }

    int quantity;
    int size;

    String goodsItem_ID;





}
