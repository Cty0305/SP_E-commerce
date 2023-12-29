package layer.dao.orderLineItems;

import db.core.JDBCTemplate;
import layer.domain.Order;
import layer.domain.OrderLineItems;
import layer.domain.goods.Goods;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class orderLineItemImp implements OrderLineItemsDAO {
    JDBCTemplate jdbcTemplate = new JDBCTemplate();
    @Override
    public void create(OrderLineItems orderLineItems) {
        jdbcTemplate.query(conn -> {
            String sql = "Insert into orderLineItem (orderLineItem_ID,goods_ID,order_ID,quantity) VALUES (?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,orderLineItems.getOrderLineItem_ID());
            preparedStatement.setString(2,orderLineItems.getGoods().getGoods_ID());
            preparedStatement.setString(3,orderLineItems.getOrder().getOrder_ID());
            preparedStatement.setInt(4,orderLineItems.getQuantity());
            return preparedStatement;
        });
    }

    @Override
    public void remove(OrderLineItems orderLineItems) {
        String sql = "Delete from orderLineItem where id = ?";
        jdbcTemplate.query(conn -> {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,orderLineItems.getOrderLineItem_ID());
            return preparedStatement;
        });
    }

    @Override
    public void modify(OrderLineItems orderLineItems) {
        String sql = "UPDATE orderLineItem SET goods_ID = ?, order_ID = ?, quantity = ? where orderLineItem_ID = ?";
        jdbcTemplate.query(conn -> {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,orderLineItems.getGoods().getGoods_ID());
            preparedStatement.setString(2,orderLineItems.getOrder().getOrder_ID());
            preparedStatement.setInt(3,orderLineItems.getQuantity());
            preparedStatement.setString(4,orderLineItems.getOrderLineItem_ID());
            return preparedStatement;
        });
    }

    @Override
    public OrderLineItems findByPk(String pk) {
        List<OrderLineItems> list = new ArrayList<>();
        String sql = "Select * from orderLineItems where orderLineItem_ID = ?";
        jdbcTemplate.query(conn -> {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,pk);
            return preparedStatement;
        },rs -> {
            OrderLineItems orderLineItems = new OrderLineItems();
            orderLineItems.setOrderLineItem_ID(rs.getString("orderLineItem_ID"));
            orderLineItems.setQuantity(rs.getInt("quantity"));

            Goods goods = new Goods();
            goods.setGoods_ID(rs.getString("goods_ID"));
            orderLineItems.setGoods(goods);


            Order order = new Order();
            order.setOrder_ID(rs.getString("order_ID"));
            orderLineItems.setOrder(order);

            list.add(orderLineItems);
        });
        if (list.size()==0){
            return null;
        }

        return list.get(0);
    }

    @Override
    public List<OrderLineItems> findByOrder(Order order) {
        List<OrderLineItems> list = new ArrayList<>();
        String order_ID = order.getOrder_ID();
        jdbcTemplate.query(conn -> {

            String sql = "Select * from orderLineItem where order_ID = ? ";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,order_ID);
            return preparedStatement;
        },rs -> {
            OrderLineItems orderLineItems = new OrderLineItems();
            orderLineItems.setOrderLineItem_ID(rs.getString("orderLineItem_ID"));
            orderLineItems.setQuantity(rs.getInt("quantity"));

            Goods goods = new Goods();
            goods.setGoods_ID(rs.getString("goods_ID"));
            orderLineItems.setGoods(goods);

            Order order1 = new Order();
            order1.setOrder_ID(rs.getString("order_ID"));
            orderLineItems.setOrder(order1);

            list.add(orderLineItems);
        });

        return list;
    }
}
