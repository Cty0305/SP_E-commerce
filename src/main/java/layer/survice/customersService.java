package layer.survice;

import layer.domain.Customer;
import layer.domain.Order;
import layer.domain.goods.Goods;

import java.util.List;

public interface customersService {

    void register(Customer customer) throws ServiceException;
    void addCart(Goods goods);
    List<Goods> searchAllGoods(List<String> goodsCondition);
    void checkOut(List<Order> orders);
    boolean Login(Customer customer) throws ServiceException;

    void forgetPassword();

}
