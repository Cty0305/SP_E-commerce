package layer.dao.order;

import db.core.JDBCTemplate;
import layer.domain.Order;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class orderDAOImp implements OrderDAO {
    JDBCTemplate jdbcTemplate = new JDBCTemplate();
    @Override
    public void create(Order order) {
        jdbcTemplate.query(conn -> {
            String sql  = "insert into order (order_ID, order_date,total,status) values (?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,order.getOrder_ID());
            preparedStatement.setLong(2,order.getCreatedTime().getTime());
            preparedStatement.setDouble(3,order.getTotalPrice());
            preparedStatement.setInt(4,order.getStatus());
            return preparedStatement;
        });
    }

    @Override
    public void remove(Order order) {
        String sql = "Delete order where order_ID = ?";
        jdbcTemplate.query(conn -> {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,order.getOrder_ID());
            return preparedStatement;
        });
    }

    @Override
    public void modify(Order order) {
        String sql = "UPDATE order set order_ID =  ?, order_date = ?, total = ?, status = ?";
        jdbcTemplate.query(conn -> {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,order.getOrder_ID());
            preparedStatement.setTimestamp(2,order.getCreatedTime());
            preparedStatement.setFloat(3,order.getTotalPrice());
            preparedStatement.setInt(4,order.getStatus());
            return preparedStatement;
        });
    }

    @Override
    public Order findByPk(String pk) {
        List<Order> list = new ArrayList<>();
        jdbcTemplate.query(conn -> {
            String sql = "Select * from order where order_ID = ?";
            PreparedStatement preparedStatement  = conn.prepareStatement(sql);
            preparedStatement.setString(1,pk);
            return preparedStatement;
        },rs -> {
            Order order = new Order();
            order.setOrder_ID(rs.getString("order_ID"));
            order.setTotalPrice(rs.getFloat("total"));
            order.setStatus(rs.getInt("status"));
            order.setCreatedTime(rs.getTimestamp("order_date"));
            list.add(order);
        });

        return list.get(0);
    }
}
