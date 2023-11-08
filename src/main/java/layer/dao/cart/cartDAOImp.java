package layer.dao.cart;

import db.core.JDBCTemplate;
import layer.domain.goods.Cart;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class cartDAOImp implements cartDAO{
    JDBCTemplate jdbcTemplate = new JDBCTemplate();


    @Override
    public void create(Cart cart) {
        String sql = "insert into cart (cart_ID,goods_ID,quantity,account) values (?,?,?,?)";
        jdbcTemplate.query(conn -> {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,cart.getCart_ID());
            preparedStatement.setString(2,cart.getGoods_ID());
            preparedStatement.setInt(3,cart.getQuantity());
            preparedStatement.setString(4,cart.getAccount());

            return preparedStatement;
        });
    }

    @Override
    public void modify(Cart cart) {
        String sql = "Update cart set goods_ID=?, quantity=? where cart_ID = ?";
        jdbcTemplate.query(conn -> {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,cart.getGoods_ID());
            preparedStatement.setInt(2,cart.getQuantity());
            preparedStatement.setString(3, cart.getCart_ID());
            return preparedStatement;
        });
    }

    @Override
    public void delete(Cart cart) {
        String sql = "delete from cart where cart_ID = ? ";
        jdbcTemplate.query(conn -> {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, cart.getCart_ID());
            return preparedStatement;
        });
    }

    @Override
    public List<Cart> findByAccount(String account) {
        List<Cart> cartList  =  new ArrayList<>();
        String sql = "select * from cart where account = ?";
        jdbcTemplate.query(conn -> {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,account);
            return preparedStatement;
        },rs -> {
            Cart cart = new Cart();
            cart.setCart_ID(rs.getString("cart_ID"));
            cart.setAccount(rs.getString("account"));
            cart.setQuantity(rs.getInt("quantity"));
            cart.setGoods_ID(rs.getString("goods_ID"));

            cartList.add(cart);
        });

        return cartList;
    }
}
