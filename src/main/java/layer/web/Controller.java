package layer.web;


import layer.domain.Customer;
import Exception.encryptException;
import java.io.IOException;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import Helpers.encryptionUtils;
import layer.domain.goods.Goods;
import layer.survice.ServiceException;
import layer.survice.customersService;
import layer.survice.customersServiceImp.customersServiceImp;
import layer.survice.goodsServiceImp;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static Helpers.encryptionUtils.encryptString;


public class Controller extends HttpServlet {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate currentDate = LocalDate.now();
    customersServiceImp customersServiceImp = new customersServiceImp();

    goodsServiceImp goodsServiceImp = new goodsServiceImp();
    int pageSize = 10;//每頁筆數
    int currentPage = 1;//當前頁數
    int totalPageNumber = 0;//總頁數
    private Map<String, List<Goods>> cache = new HashMap<>();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        pageSize = Integer.parseInt(config.getInitParameter("pageSize"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if("list".equals(action)){

            List<Goods> goodsList = new ArrayList<>();
            if(cache.containsKey("goodsList")){
                goodsList = cache.get("goodsList");
            }else{
                goodsList = goodsServiceImp.findAll();
                cache.put("goodsList",goodsList);
            }

            //-------------商品列表---------------
            currentPage = 1;
            if(goodsList.size() % pageSize == 0){
                totalPageNumber = goodsList.size() / pageSize;
            }else{
                totalPageNumber = goodsList.size() / pageSize +1;
            }

            req.setAttribute("totalPageNumber",totalPageNumber);
            req.setAttribute("currentPage",currentPage);

            int start = (currentPage-1) * pageSize;
            int end = currentPage * pageSize;
            if(currentPage==totalPageNumber){
                end = goodsList.size();
            }

            req.setAttribute("goodsList", goodsServiceImp.queryByStartEnd(start,end));
            req.getRequestDispatcher("goods_list.jsp").forward(req,resp);




        }
        //------------商品列表分页--------------
        else if ("paging".equals(action)) {



            List<Goods> goodsList = new ArrayList<>();
            if(cache.containsKey("goodsList")){
                goodsList = cache.get("goodsList");
            }else {
                goodsList = goodsServiceImp.findAll();
                cache.put("goodsList",goodsList);
            }


            if(goodsList.size() % pageSize == 0){
                totalPageNumber = goodsList.size() / pageSize;
            }else{
                totalPageNumber = goodsList.size() / pageSize +1;
            }
            String page = req.getParameter("page");
            if(page.equals("prev")){
                currentPage--;
                if(currentPage<1){
                    currentPage=1;
                }

            } else if (page.equals("next")) {
                currentPage++;
                if(currentPage>totalPageNumber){
                    currentPage=totalPageNumber;
                }
            } else {
                currentPage = Integer.valueOf(page);//要處理大於或小於totalPageNum
            }

            int start = (currentPage-1) * pageSize;
            int end = currentPage * pageSize;
            if(currentPage == totalPageNumber){
                end = goodsList.size();
            }

            req.setAttribute("totalPageNumber",totalPageNumber);
            req.setAttribute("currentPage",currentPage);
            req.setAttribute("goodsList",goodsList.subList(start,end));
            req.getRequestDispatcher("goods_list.jsp").forward(req,resp);
        }
    }

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
        //建立商品
        else if(action.equals("createGoods")){
            Goods goods = new Goods();
            String price = req.getParameter("price");
            String brand = req.getParameter("brand");
            String name = req.getParameter("name");
            List<String> err = new ArrayList<>();


            if(price==null||price.isEmpty()){
                err.add("價格不得為空");
            }
            if(Float.parseFloat(price)<=0){
                err.add("價格不得為負數");
            }
            if(brand==null||brand.isEmpty()){
                err.add("品牌不可為空");
            }
            if(name==null||name.isEmpty()){
                err.add("名稱不可為空");
            }
            if(err.size()==0){
                List<Goods> goodsList = goodsServiceImp.findAll();
                goods.setGoods_ID(goodsList.size());
                goods.setPrice(Float.parseFloat(price));
                goods.setDescription(req.getParameter("description"));
                goods.setBrand(brand);
                goods.setCreatedTime(new Timestamp(System.currentTimeMillis()));
                goods.setName(name);
                goodsServiceImp.createGoods(goods);


                goodsList.add(goods);
                req.setAttribute("goodsList",goodsList);
                //轉到商品列表頁面
                req.getRequestDispatcher("goodsList_backstage.jsp").forward(req,resp);
            }else{

                req.setAttribute("err",err);
                req.getRequestDispatcher("goodsList_backstage.jsp").forward(req,resp);

            }








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
