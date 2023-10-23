package layer.web;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import layer.domain.Customer;
import Exception.encryptException;
import java.io.IOException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import Helpers.encryptionUtils;
import layer.domain.goods.Goods;
import layer.survice.ServiceException;
import layer.survice.customersService;
import layer.survice.customersServiceImp.customersServiceImp;
import layer.survice.goodsServiceImp;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import static Helpers.encryptionUtils.encryptString;

@WebServlet(name="Controller",urlPatterns = {"/controller"})
public class Controller extends HttpServlet {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate currentDate = LocalDate.now();
    customersServiceImp customersServiceImp = new customersServiceImp();

    goodsServiceImp goodsServiceImp = new goodsServiceImp();
    int pageSize;
    int currentPage;
    int totalPageNumber;
    @Override
    protected void doPost(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp) throws javax.servlet.ServletException, IOException {
        String action = req.getParameter("action");
        if(action.equalsIgnoreCase("register")){
            List<String> err = new ArrayList<>();
            String name = req.getParameter("name");
            String account = req.getParameter("account");
            String password = req.getParameter("password");
            String password2 = req.getParameter("password2");
            String birthday = req.getParameter("birthday");
            String phone = req.getParameter("phone");
            String address = req.getParameter("address");
            String email = req.getParameter("email");
            String gender = req.getParameter("gender");
            System.out.println("ths is string:" + birthday);
            if(name == null || name.trim().equals("")){
                err.add("未填寫姓名");
            }
            if(account == null || account.trim().equals("")){
                err.add("未填寫帳號");
            }
            if(password == null || password.trim().equals("")){
                err.add("未填寫密碼");
            }
            if(!password.equals(password2)){
                err.add("密碼不一致");
            }
            if(!birthday.equals("")){
                if(LocalDate.parse(birthday,formatter).isAfter(currentDate)){
                    err.add("不可為未來日期");
                }
            }
            if(phone.matches("09\\d{8}")){
                err.add("手機號碼格式錯誤");
            }
            if(gender == null){
                err.add("請選擇性別");
            }


            if(err.size()>0){
                req.setAttribute("err",err);
                req.getRequestDispatcher("customer_reg.jsp").forward(req,resp);
            }else{
                Customer customer = new Customer();
                customer.setName(name);
                customer.setAddress(address);
                customer.setPhone(phone);
                customer.setGender(gender);
                customer.setEmail(email);
                customer.setAccount(account);
                if(!birthday.equals("")){
                    customer.setBirthday(LocalDate.parse(req.getParameter("birthday"),formatter));
                }else{
                    customer.setBirthday(null);
                }
                customer.setSalt(Arrays.toString(generateSalt()));
                String passwordWithSalt = password+ customer.getSalt();
                try{
                    customer.setPassword(encryptString(passwordWithSalt));
                } catch (encryptException e) {
                    System.out.println("加密出錯");
                    e.printStackTrace();
                }

                try {
                    customersServiceImp.register(customer);
                    resp.sendRedirect("login.jsp");
                }catch (ServiceException e){
                    System.out.println("帳號存在");
                    err.add("帳號已存在");
                    req.setAttribute("err",err);
                    req.getRequestDispatcher("customer_reg.jsp").forward(req,resp);
                }


            }




        }
        else if ("login".equals(action)) {
            Customer loginCustomer = new Customer();
            List<String> err = new ArrayList<>();
            loginCustomer.setPassword(req.getParameter("password"));
            loginCustomer.setAccount(req.getParameter("account"));

            try{
                if(customersServiceImp.Login(loginCustomer)){
                    HttpSession session = req.getSession();
                    session.setAttribute("loggedInCustomerAccount",loginCustomer.getAccount());
                    req.getRequestDispatcher("main.jsp").forward(req,resp);
                }else {
                    err.add("密碼不正確");
                    System.out.println("密碼不正確");
                    req.setAttribute("error",err);
                    req.getRequestDispatcher("login.jsp").forward(req,resp);
                }
            }catch (ServiceException e){
                err.add("無對應帳號");
                System.out.println("無對應帳號");
                req.setAttribute("error",err);
                req.getRequestDispatcher("login.jsp").forward(req,resp);
            }


        }
        else if("list".equals(action)){
            //-------------商品列表---------------
            List<Goods> goodsList = goodsServiceImp.findAll();
            if(goodsList.size() % pageSize == 0){
                totalPageNumber = goodsList.size() / pageSize;
            }else{
                totalPageNumber = goodsList.size() / pageSize +1;
            }

            req.setAttribute("totalPageNumber",totalPageNumber);
            req.setAttribute("currentPage",currentPage);
            req.setAttribute("goodsList",goodsList.subList(0,currentPage*pageSize));
            req.getRequestDispatcher("goods_list.jsp").forward(req,resp);




        }
    }


    //產生salt
    private byte[] generateSalt(){
        byte[] bytes = new byte[16];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(bytes);
        return bytes;
    }


}
