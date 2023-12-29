package layer.dao.order;

import layer.domain.Order;

import java.util.List;

public interface OrderDAO {

    void create(Order order);
    void remove(Order order);
    void modify(Order order);
    Order findByPk(String pk);

    List<Order> findByAccount(String account);;

}
