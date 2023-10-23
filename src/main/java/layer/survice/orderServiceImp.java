package layer.survice;

import layer.dao.goods.GoodsDAO;
import layer.dao.goods.goodsDAOImp;
import layer.dao.order.OrderDAO;
import layer.dao.order.orderDAOImp;
import layer.dao.orderLineItems.OrderLineItemsDAO;
import layer.dao.orderLineItems.orderLineItemImp;
import layer.domain.Order;
import layer.domain.OrderLineItems;
import layer.domain.goods.Goods;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;


public class orderServiceImp implements orderService{
    GoodsDAO goodsDAO = new goodsDAOImp();
    OrderDAO orderDAO = new orderDAOImp();
    OrderLineItemsDAO orderLineItemsDAO = new orderLineItemImp();


    @Override
    public String submitService(List<Map<String,Object>> cart) {
        Order order = new Order();
        String order_ID = String.valueOf(LocalTime.now()) + Math.random() * 100;
        order.setOrder_ID(order_ID);
        order.setStatus(1);
        order.setTotalPrice(0.0f);
        order.setCreatedTime(new Timestamp(System.currentTimeMillis()));

        orderDAO.create(order);
        float subtotal = 0.0f;
        float total = 0.0f;
        for(Map item:cart){
            String goods_ID = (String)item.get("goods_ID");
            Integer quantity = (Integer)item.get("quantity");
            Goods goods = goodsDAO.findByPk(goods_ID);

            subtotal+= goods.getPrice() * quantity;

            OrderLineItems orderLineItems = new OrderLineItems();
            orderLineItems.setQuantity(quantity);
            orderLineItems.setGoods(goods);
            orderLineItems.setOrder(order);
            orderLineItems.setSub_total(subtotal);


            orderLineItemsDAO.create(orderLineItems);
            total+=subtotal;

        }
        order.setTotalPrice(total);
        orderDAO.modify(order);
        return order_ID;
    }
}
