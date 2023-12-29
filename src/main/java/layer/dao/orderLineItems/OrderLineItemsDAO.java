package layer.dao.orderLineItems;

import layer.domain.Order;
import layer.domain.OrderLineItems;

import java.util.List;

public interface OrderLineItemsDAO {
    void create(OrderLineItems orderLineItems);
    void remove(OrderLineItems orderLineItems);
    void modify(OrderLineItems orderLineItems);
    OrderLineItems findByPk(String pk);
    List<OrderLineItems> findByOrder(Order order);
}
