package layer.domain;

import layer.domain.goods.Goods;

public class OrderLineItems {


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getSub_total() {
        return sub_total;
    }

    public void setSub_total(float sub_total) {
        this.sub_total = sub_total;
    }



    private int quantity;
    private float sub_total;

    public String getOrderLineItem_ID() {
        return orderLineItem_ID;
    }

    public void setOrderLineItem_ID(String orderLineItem_ID) {
        this.orderLineItem_ID = orderLineItem_ID;
    }

    private String orderLineItem_ID;

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    private Goods goods;
    private Order order;

}
