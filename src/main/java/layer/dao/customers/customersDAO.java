package layer.dao.customers;

import layer.domain.Customer;

import java.util.List;

public interface customersDAO {
    void create(Customer customer);
    Customer findByPK(String pk);
    List<Customer> findAll(List<Customer> list);
    void remove(Customer customer);
    void modify(Customer customer);

}
