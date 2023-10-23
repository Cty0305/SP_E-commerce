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
            String sql = "Insert into orderLineItem (id,goods_ID,order_ID,quantity,sub_total) VALUES (?,?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1,orderLineItems.getId());
            preparedStatement.setString(3,orderLineItems.getOrder().getOrder_ID());
            preparedStatement.setInt(2,orderLineItems.getGoods().getGoods_ID());
            preparedStatement.setInt(4,orderLineItems.getQuantity());
            preparedStatement.setFloat(5,orderLineItems.getSub_total());
            return preparedStatement;
        });
    }

    @Override
    public void remove(OrderLineItems orderLineItems) {
        String sql = "Delete from orderLineItem where id = ?";
        jdbcTemplate.query(conn -> {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1,orderLineItems.getId());
            return preparedStatement;
        });
    }

    @Override
    public void modify(OrderLineItems orderLineItems) {
        String sql = "UPDATE orderLineItem SET id = ?, goods_ID = ?, order_ID = ?, quantity = ?,  sub_total = ?";
        jdbcTemplate.query(conn -> {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1,orderLineItems.getId());
            preparedStatement.setInt(2,orderLineItems.getGoods().getGoods_ID());
            preparedStatement.setString(3,orderLineItems.getOrder().getOrder_ID());
            preparedStatement.setInt(4,orderLineItems.getQuantity());
            preparedStatement.setFloat(5,orderLineItems.getSub_total());
            return preparedStatement;
        });
    }

    @Override
    public OrderLineItems findByPk(String pk) {
        List<OrderLineItems> list = new ArrayList<>();
        String sql = "Select * from orderLineItems where id = ?";
        jdbcTemplate.query(conn -> {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,pk);
            return preparedStatement;
        },rs -> {
            OrderLineItems orderLineItems = new OrderLineItems();
            orderLineItems.setId(rs.getInt("id"));
            orderLineItems.setQuantity(rs.getInt("quantity"));
            orderLineItems.setSub_total(rs.getFloat("sub_total"));

            Goods goods = new Goods();
            goods.setGoods_ID(rs.getInt("goods_ID"));
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
}
