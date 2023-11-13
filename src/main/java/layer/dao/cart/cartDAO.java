package layer.dao.cart;

import db.core.JDBCTemplate;
import layer.domain.goods.Cart;

import java.util.List;

public interface cartDAO {

    void create(Cart cart);
    void delete(String cart_ID);
    void modify(Cart cart);
    List<Cart> findByAccount(String account);



}
