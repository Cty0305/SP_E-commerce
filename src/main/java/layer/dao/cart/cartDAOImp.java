package layer.dao.cart;

import db.core.JDBCTemplate;
import layer.domain.goods.Cart;

import java.sql.PreparedStatement;
import java.util.List;

public class cartDAOImp implements cartDAO{
    JDBCTemplate jdbcTemplate = new JDBCTemplate();
    //還沒建立cartID

    @Override
    public void create(Cart cart) {
        String sql = "insert into cart (goods_ID,)";
        jdbcTemplate.query(conn -> {
            PreparedStatement preparedStatement = conn.prepareStatement(sql)
        });
    }

    @Override
    public void modify(Cart cart) {

    }

    @Override
    public void delete(Cart cart) {

    }

    @Override
    public List<Cart> findByAccount(String account) {
        return null;
    }
}
