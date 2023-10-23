package layer.dao.order;

import layer.domain.Order;

public interface OrderDAO {

    void create(Order order);
    void remove(Order order);
    void modify(Order order);
    Order findByPk(String pk);

}
