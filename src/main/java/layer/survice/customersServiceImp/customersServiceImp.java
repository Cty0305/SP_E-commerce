package layer.survice.customersServiceImp;

import layer.dao.customers.customersDAO;
import layer.dao.customers.imp.customersDAOImp;
import layer.domain.Customer;
import layer.domain.Order;
import layer.domain.goods.Goods;
import layer.survice.ServiceException;
import layer.survice.customersService;

import java.util.List;
import Helpers.*;
import Exception.*;

public class customersServiceImp implements customersService {
    customersDAO customerDAO = new customersDAOImp();


    @Override
    public void register(Customer customer) throws ServiceException{
        Customer dbCustomer= customerDAO.findByPK(customer.getAccount());
        if(dbCustomer!=null){
            throw new ServiceException("客户ID: " + customer.getAccount() + "已经存在！");
        }else{
            customerDAO.create(customer);
        }

    }

    @Override
    public void addCart(Goods goods) {

    }

    @Override
    public List<Goods> searchAllGoods(List<String> goodsCondition) {
        return null;
    }

    @Override
    public void checkOut(List<Order> orders) {

    }

    @Override
    public boolean Login(Customer loginCustomer) throws ServiceException {
        Customer dbCustomer = customerDAO.findByPK(loginCustomer.getAccount());
        if(dbCustomer.getAccount()==null){
            System.out.println("無DBC");
            throw new ServiceException("無對應帳號");
        }







        //密碼hash
        try {
            String passwordWithSalt = loginCustomer.getPassword()+dbCustomer.getSalt();
            String encryptPassword =  encryptionUtils.encryptString(passwordWithSalt);
            if(encryptPassword.equals(dbCustomer.getPassword())){
                return true;
            }
        } catch (encryptException e) {
            e.printStackTrace();
        }




        return false;
    }

    @Override
    public void forgetPassword() {

    }

//    public boolean isIdUnique(Customer customer){
//        Customer dbCustomer = customerDAO.findByPK(String.valueOf(customer.getAccount()));
//        if(dbCustomer==null){
//            return true;
//        }
//        return false;
//    }
}
