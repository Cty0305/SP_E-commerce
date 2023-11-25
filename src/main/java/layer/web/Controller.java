package layer.web;


import Helpers.EmailVerification;
import Helpers.emailVerificationMap;
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
import layer.domain.goods.Cart;
import layer.domain.goods.Goods;
import layer.survice.ServiceException;
import layer.survice.cartService;
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
    private Map<String, List<Cart>> cache_cart = new HashMap<>();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        pageSize = Integer.parseInt(config.getInitParameter("pageSize"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        //--------商品表------
        if("list".equals(action)){
            String account = (String) req.getSession().getAttribute("loggedInCustomerAccount");

            if(account==null){
                resp.sendRedirect("login.jsp");

            }else {
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
        }
        //------------商品列表分页--------------
        else if ("paging".equals(action)) {
            String account = (String) req.getSession().getAttribute("loggedInCustomerAccount");

            if(account==null){
                resp.sendRedirect("login.jsp");

            }else{
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
        //商品後台
        else if(action.equals("goodsList_backstage")){
            List<Goods> goodsList = new ArrayList<>();
            if(cache.containsKey("goodsList")){
                goodsList = cache.get("goodsList");
            }else{
                goodsList = goodsServiceImp.findAll();
                cache.put("goodsList",goodsList);
            }
            //設定顯示時是第一頁
            currentPage = 1;
            //設定頁面數量

            if(goodsList.size() % pageSize == 0){
                totalPageNumber = goodsList.size() / pageSize;
            }else{
                totalPageNumber = goodsList.size() / pageSize +1;
            }

            int start = (currentPage-1) * pageSize;
            int end = currentPage * pageSize;
            if(currentPage==totalPageNumber){
                end = goodsList.size();
            }

            req.setAttribute("goodsList",goodsList.subList(start,end));
            req.setAttribute("totalPageNumber",totalPageNumber);
            req.setAttribute("currentPage",currentPage);
            req.getRequestDispatcher("goods_backstage.jsp").forward(req,resp);
        }
        //------------後臺商品列表分页--------------
        else if ("backstage_paging".equals(action)) {

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
                currentPage = Integer.valueOf(page);
            }

            int start = (currentPage-1) * pageSize;
            int end = currentPage * pageSize;
            if(currentPage == totalPageNumber){
                end = goodsList.size();
            }

            req.setAttribute("totalPageNumber",totalPageNumber);
            req.setAttribute("currentPage",currentPage);
            req.setAttribute("goodsList",goodsList.subList(start,end));
            req.getRequestDispatcher("goods_backstage.jsp").forward(req,resp);
        }
        //---------購物車頁面-------
        else if("cart".equals(action)){

            String account = (String) req.getSession().getAttribute("loggedInCustomerAccount");

            if(account==null){
                resp.sendRedirect("login.jsp");

            }else{
                cartService cartService = new cartService();
                List<Cart> cartList = cartService.findByAccount(account);




                if(cartList.size() % pageSize == 0){
                    totalPageNumber = cartList.size() / pageSize;

                }else{
                    totalPageNumber = cartList.size() / pageSize +1;

                }



                currentPage = 1;
                int start = (currentPage-1) * pageSize;
                int end = currentPage * pageSize;
                if(currentPage == totalPageNumber){
                    end = cartList.size();
                }

                req.setAttribute("totalPageNumber",totalPageNumber);
                req.setAttribute("currentPage",currentPage);
                req.setAttribute("cartList",cartList.subList(start,end));
                req.getRequestDispatcher("cart.jsp").forward(req,resp);
            }

        }
        //-----購物車分頁------
        else if ("cart_paging".equals(action)) {
            String account = (String) req.getSession().getAttribute("loggedInCustomerAccount");

            if(account==null){
                resp.sendRedirect("login.jsp");

            }else{
                List<Cart> cartList = new ArrayList<>();
                if(cache_cart.containsKey("cartList")){
                    cartList = cache_cart.get("cartList");
                }else {
                    cartService cartService = new cartService();
                    cartList = cartService.findByAccount((String) req.getSession().getAttribute("loggedInCustomerAccount"));
                    cache_cart.put("cartList",cartList);
                }


                if(cartList.size() % pageSize == 0){
                    totalPageNumber = cartList.size() / pageSize;
                }else{
                    totalPageNumber = cartList.size() / pageSize +1;
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
                    currentPage = Integer.valueOf(page);
                }

                int start = (currentPage-1) * pageSize;
                int end = currentPage * pageSize;
                if(currentPage == totalPageNumber){
                    end = cartList.size();
                }

                req.setAttribute("totalPageNumber",totalPageNumber);
                req.setAttribute("currentPage",currentPage);
                req.setAttribute("cartList",cartList.subList(start,end));
                req.getRequestDispatcher("cart.jsp").forward(req,resp);
            }

        }


        //------------購物車刪除-------
        else if ("deleteCartItem".equals(action)) {
            cartService cartService = new cartService();
            cartService.deleteCartItem(req.getParameter("cart_ID"));
            req.getRequestDispatcher("controller?action=cart").forward(req,resp);

        }

        //-----檢查登入信件
        else if("verification".equals(action)){
            String token = req.getParameter("token");
            if(req.getSession().getAttribute("map")==null){
                System.out.println("Session失效");
                req.getRequestDispatcher("emailVerificationFailed.jsp").forward(req,resp);
            } else{
                Map<String,emailVerificationMap> map = (Map<String, emailVerificationMap>) req.getSession().getAttribute("map");
                if(!map.containsKey(token)){
                    System.out.println("token不存在");
                    req.getRequestDispatcher("emailVerificationFailed.jsp").forward(req,resp);
                }else if(System.currentTimeMillis() - map.get(token).getTimestamp()> 10 * 60 * 1000){
                    System.out.println("連結逾期");
                    req.getRequestDispatcher("emailVerificationFailed.jsp").forward(req,resp);

                }else{
                    customersServiceImp.verificationSuccess(map.get(token).getAccount());
                    System.out.println("認證成功");
                    resp.sendRedirect("login.jsp");

                }
            }

        }
        //-------重新發送信件-----
        else if("resendEmail".equals(action)){
            String account = req.getParameter("account");
            EmailVerification emailVerification = new EmailVerification();
            Customer customer = customersServiceImp.findByPK(account);
            String token = emailVerification.createEmail(customer);
            emailVerificationMap emailVerificationMap = new emailVerificationMap();
            emailVerificationMap.setTimestamp(System.currentTimeMillis());
            emailVerificationMap.setAccount(customer.getAccount());


            Map<String,emailVerificationMap> map = new HashMap<>();
            map.put(token,emailVerificationMap);
            req.getSession().setAttribute("map",map);
            resp.sendRedirect("login.jsp");
        }
    }

    @Override
    protected void doPost(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp) throws javax.servlet.ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        //------註冊----------------------
        if(action.equalsIgnoreCase("register")){
            List<String> err = new ArrayList<>();
            String firstName = req.getParameter("firstName");
            String lastName = req.getParameter("lastName");
            String account = req.getParameter("account");
            String password = req.getParameter("password");
            String password2 = req.getParameter("password2");

            if(firstName == null || firstName.trim().equals("")){
                err.add("please enter the first name");
            }
            if(lastName == null || lastName.trim().equals("")){
                err.add("please enter the last name");
            }
            if(account == null || account.trim().equals("")){
                err.add("please enter username");
            }
            if(password == null || password.trim().equals("")){
                err.add("please enter password");
            }
            if(!password.equals(password2)){
                err.add("passwords do not match ");
            }

            if(err.size()>0){
                req.setAttribute("err",err);
                req.getRequestDispatcher("customer_reg.jsp").forward(req,resp);
            }else{
                Customer customer = new Customer();
                customer.setFirstName(firstName);
                customer.setAccount(account);
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
//                    EmailVerification emailVerification = new EmailVerification();
//                    String token = emailVerification.createEmail(customer);
//
//                    emailVerificationMap emailVerificationMap = new emailVerificationMap();
//                    emailVerificationMap.setTimestamp(System.currentTimeMillis());
//                    emailVerificationMap.setAccount(customer.getAccount());
//
//
//                    Map<String,emailVerificationMap> map = new HashMap<>();
//                    map.put(token,emailVerificationMap);
//                    req.getSession().setAttribute("map",map);


                    resp.sendRedirect("login.jsp");
                }catch (ServiceException e){
                    System.out.println("帳號存在");
                    err.add("the account has already exist");
                    req.setAttribute("err",err);
                    req.getRequestDispatcher("customer_reg.jsp").forward(req,resp);
                }


            }




        }
        //-------------註冊----------------
        else if (action.equals("createAccount")) {

            //檢查token
            Map<String,String> tokenMap = (Map<String, String>) req.getSession().getAttribute("tokenMap");
            String email = (String) req.getAttribute("email");
            String token = tokenMap.get(email);
            String inputToken = req.getParameter("token");

            //判斷是否正確
            if(token.equals(inputToken)){
                List<String> err = new ArrayList<>();
                String firstName = req.getParameter("firstName");
                String lastName = req.getParameter("lastName");
                String account = req.getParameter("account");
                String password = req.getParameter("password");
                String password2 = req.getParameter("password2");

                if(firstName == null || firstName.trim().equals("")){
                    err.add("please enter the first name");
                }
                if(lastName == null || lastName.trim().equals("")){
                    err.add("please enter the last name");
                }
                if(account == null || account.trim().equals("")){
                    err.add("please enter username");
                }
                if(password == null || password.trim().equals("")){
                    err.add("please enter password");
                }
                if(!password.equals(password2)){
                    err.add("passwords do not match ");
                }
                if(err.size()>0){
                    req.setAttribute("err",err);
                    req.getRequestDispatcher("customer_reg.jsp").forward(req,resp);
                }else{
                    Customer customer = new Customer();
                    customer.setFirstName(firstName);
                    customer.setAccount(account);
                    customer.setEmail(email);
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
                        System.out.println("註冊成功");
                        resp.sendRedirect("login.jsp");
                    }catch (ServiceException e){
                        System.out.println("帳號存在");
                        err.add("the account has already exist");
                        req.setAttribute("err",err);
                        req.getRequestDispatcher("customer_reg.jsp").forward(req,resp);
                    }
                }
            }else{
                System.out.println("token錯誤");
                tokenMap.put(email,token);
                req.setAttribute("email",email);
                req.getRequestDispatcher("create_account.html").forward(req,resp);
            }


        }
        //------登入----
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
        //-------------註冊驗證信相---------
        else if(action.equals("signUpEmailVerification")){
            String email = req.getParameter("email");
            EmailVerification emailVerification = new EmailVerification();
            String token = emailVerification.createEmail(email);
            Map<String,String> tokenMap = new HashMap<>();
            tokenMap.put(email,token);
            req.setAttribute("email",email);
            req.getSession().setAttribute("tokenMap",tokenMap);
            req.getRequestDispatcher("create_account.html").forward(req,resp);

        }
        //-----------------建立商品-----------------------
        else if(action.equals("createGoods")){
            Goods goods = new Goods();
            String price = req.getParameter("price");
            String category = req.getParameter("category");
            String name = req.getParameter("name");
            int quantity = 0;
            List<String> err = new ArrayList<>();


            if(price==null||price.isEmpty()){
                err.add("價格不得為空");
            }
            if(Float.parseFloat(price)<=0){
                err.add("價格不得為負數");
            }
            if(category==null||category.isEmpty()){
                err.add("分類不可為空");
            }
            if(name==null||name.isEmpty()){
                err.add("名稱不可為空");
            }

            if(req.getParameter("quantity")==null){
                err.add("請輸入商品數量");
            }else{
                try{
                    quantity = Integer.parseInt(req.getParameter("quantity"));
                }catch (NumberFormatException e){
                    err.add("產品數量請輸入整數");
                    req.setAttribute("err",err);
                    req.getRequestDispatcher("goods_create.jsp").forward(req,resp);
                }
                if(quantity<1){
                    err.add("商品數量需要大於0");
                }
            }
            if(err.size()==0){
                List<Goods> goodsList = goodsServiceImp.findAll();
                goods.setGoods_ID(UUID.randomUUID().toString());
                goods.setPrice(Float.parseFloat(price));
                goods.setDescription(req.getParameter("description"));
                goods.setCategory(category);
                goods.setCreatedTime(new Timestamp(System.currentTimeMillis()));
                goods.setName(name);
                goods.setQuantity(quantity);

                goodsServiceImp.createGoods(goods);


                goodsList.add(goods);
                if(goodsList.size() % pageSize == 0){
                    totalPageNumber = goodsList.size() / pageSize;
                }else{
                    totalPageNumber = goodsList.size() / pageSize +1;
                }

                currentPage = 1;
                int start = (currentPage -1) * pageSize;
                int end;
                if(currentPage == totalPageNumber){
                    end = goodsList.size();
                }else{
                    end = currentPage * pageSize;
                }


                req.setAttribute("goodsList",goodsList.subList(start,end));
                req.setAttribute("currentPage",currentPage);
                req.setAttribute("totalPageNumber",totalPageNumber);
                //轉到商品列表頁面
                req.getRequestDispatcher("goods_backstage.jsp").forward(req,resp);
            }else{
                req.setAttribute("err",err);
                req.getRequestDispatcher("goods_backstage.jsp").forward(req,resp);

            }








        }//------添加購物車-------
        else if(action.equals("add")){
            Cart cart = new Cart();
            cart.setAccount((String) req.getSession().getAttribute("loggedInCustomerAccount"));
            cart.setGoods_ID(req.getParameter("goods_ID"));

            cartService cartService = new cartService();
            cartService.addToCart(cart);

            resp.sendRedirect("controller?action=list");



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
