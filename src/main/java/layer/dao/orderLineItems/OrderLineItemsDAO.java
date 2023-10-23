package layer.dao.orderLineItems;

import layer.domain.OrderLineItems;

public interface OrderLineItemsDAO {
    void create(OrderLineItems orderLineItems);
    void remove(OrderLineItems orderLineItems);
    void modify(OrderLineItems orderLineItems);
    OrderLineItems findByPk(String pk);
}
