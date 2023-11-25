package layer.survice.customersServiceImp;

import layer.dao.customers.customersDAO;
import layer.dao.customers.imp.customersDAOImp;
import layer.domain.Customer;
import layer.domain.Order;
import layer.domain.goods.Goods;
import layer.survice.ServiceException;
import layer.survice.customersService;

import java.util.List;
import java.util.Map;

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
        if(dbCustomer==null){
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



    @Override
    public boolean verificationEmail(Customer loginCustomer){
        Customer dbCustomer = customerDAO.findByPK(loginCustomer.getAccount());
        if(dbCustomer.getEmailStatus()==0){
            return false;
        }else {
            return true;
        }

    }

    @Override
    public void verificationSuccess(String account) {
        Customer customer = customerDAO.findByPK(account);
        System.out.println(customer.getAccount());
        customer.setEmailStatus(1);
        System.out.println(customer.getEmailStatus());
        customerDAO.modify(customer);
    }


    @Override
    public Customer findByPK(String account){
        Customer customer = customerDAO.findByPK(account);
        return customer;
    }


//    public boolean isIdUnique(Customer customer){
//        Customer dbCustomer = customerDAO.findByPK(String.valueOf(customer.getAccount()));
//        if(dbCustomer==null){
//            return true;
//        }
//        return false;
//    }
}
