package layer.survice;

import layer.domain.Customer;
import layer.domain.Order;
import layer.domain.goods.Goods;

import java.util.List;
import java.util.Map;

public interface customersService {

    void register(Customer customer) throws ServiceException;
    void addCart(Goods goods);
    List<Goods> searchAllGoods(List<String> goodsCondition);
    void checkOut(List<Order> orders);
    boolean Login(Customer customer) throws ServiceException;

    void forgetPassword();
    boolean verificationEmail(Customer loginCustomer);
    void verificationSuccess(String account);
    public Customer findByPK(String account);
}
