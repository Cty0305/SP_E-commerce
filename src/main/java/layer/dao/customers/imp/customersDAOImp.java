package layer.dao.customers.imp;

import db.core.JDBCTemplate;
import db.core.RollCallBackHandler;
import layer.dao.customers.customersDAO;
import layer.domain.Customer;
import Exception.encryptException;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class customersDAOImp implements customersDAO {
    JDBCTemplate jdbcTemplate = new JDBCTemplate();


    @Override
    public void create(Customer customer) {

        jdbcTemplate.query(conn -> {
            String sql = "Insert into Customers(name, phone, email, address, gender, password_hash, birthday,account,salt) VALUES (?,?,?,?,?,?,?,?,?) ";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,customer.getName());
            preparedStatement.setString(2,customer.getPhone());
            preparedStatement.setString(3,customer.getEmail());
            preparedStatement.setString(4,customer.getAddress() != null ? customer.getAddress() :"");
            preparedStatement.setString(5,customer.getGender());
            preparedStatement.setString(6,customer.getPassword());


            //prevent  date null
            if(customer.getBirthday()!=null){
                preparedStatement.setDate(7, Date.valueOf(customer.getBirthday()));
            }else{
                preparedStatement.setNull(7, Types.DATE);
            }
            preparedStatement.setString(8, customer.getAccount());
            preparedStatement.setString(9, customer.getSalt());
            return preparedStatement;
        });
    }

    @Override
    public Customer findByPK(String pk){
        List<Customer> list = new ArrayList<>();
        jdbcTemplate.query(conn -> {
            String sql = "Select * from customers where account  = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,pk);
            return preparedStatement;
        }, rs -> {
            Customer customer = getCustomerFromDB(rs);
            list.add(customer);
        });
        //如果沒找到
        if (list.size()==0){
            return null;
        }
        //回傳找到的第一個
        return list.get(0);
    }

    @Override
    public List<Customer> findAll(List<Customer> list) {
//        List<String> nameList= new ArrayList<>();
//        List<String> addressList= new ArrayList<>();
//        List<String> phoneList= new ArrayList<>();
//        List<String> genderList= new ArrayList<>();
//
//        for(Customer customer:list){
//            nameList.add(customer.getName());
//            addressList.add(customer.getAddress());
//            phoneList.add(customer.getPhone());
//            genderList.add(customer.getGender());
//        }


        //創建同類型欄位清單，使用StreamAPI
        List<String> nameList = list.stream().map(Customer::getName).collect(Collectors.toList());
        List<String> addressList = list.stream().map(Customer::getAddress).collect(Collectors.toList());
        List<String> phoneList = list.stream().map(Customer::getPhone).collect(Collectors.toList());
        List<String> genderList = list.stream().map(Customer::getGender).collect(Collectors.toList());

        //計算多少個(?)
        String nameConditionNum = calNumOfCondition(nameList);
        String addressConditionNum = calNumOfCondition(addressList);
        String phoneConditionNum = calNumOfCondition(phoneList);
        String genderConditionNum = calNumOfCondition(genderList);


        StringBuilder sqlStringBuilder = new StringBuilder();
        sqlStringBuilder.append("select * from customer where ");
        List<String> sqlCommand = new ArrayList<>();
        sqlCommand.add(sqlCommand("name",nameConditionNum));
        sqlCommand.add(sqlCommand("address",addressConditionNum));
        sqlCommand.add(sqlCommand("phone",phoneConditionNum));
        sqlCommand.add(sqlCommand("gender",genderConditionNum));
        sqlStringBuilder.append(String.join("and",sqlCommand));
        String sql = sqlStringBuilder.toString();
        List<Customer> customerList = new ArrayList<>();
        jdbcTemplate.query(conn -> {
            if(list.size() == 0){
                PreparedStatement preparedStatement = conn.prepareStatement("select * from customers");
                return preparedStatement;
            }
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            int paramIndex= 1;
            if(!nameList.isEmpty()){
            for(String nameParam:nameList){
                preparedStatement.setString(paramIndex,nameParam);
                paramIndex++;
            }}
            if(!addressList.isEmpty()){
                for(String addressParam:addressList){
                    preparedStatement.setString(paramIndex,addressParam);
                }
            }
            if(!phoneList.isEmpty()){
                for(String phoneParam:phoneList){
                    preparedStatement.setString(paramIndex,phoneParam);
                }
            }
            if(!genderList.isEmpty()){
                for(String genderParam:genderList){
                    preparedStatement.setString(paramIndex,genderParam);
                }
            }

            return preparedStatement;
        },rs -> {
            Customer customer = new Customer();
            customer.setName(rs.getString("name"));
            customer.setAddress(rs.getString("address"));
            customer.setPhone(rs.getString("phone"));
            customer.setGender(rs.getString("gender"));
            customerList.add(customer);
        });
        return customerList;
    }

    @Override
    public void remove(Customer customer) {
        jdbcTemplate.query(conn -> {
            String sql  = "Delete from customers where account = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,customer.getAccount());
            return preparedStatement;
        });
    }

    @Override
    public void modify(Customer customer) {
        Customer dbCustomer  = findByPK(customer.getAccount());
        if(dbCustomer!=null){

            try{
                jdbcTemplate.query(conn -> {
                    String sql = "UPDATE customers SET name = ? ,address = ?, phone = ?, birthday = ?, gender = ?, email = ?, email_Status = ? where account = ?";
                    System.out.println(sql);
                    PreparedStatement preparedStatement = conn.prepareStatement(sql);
                    preparedStatement.setString(1,customer.getName());
                    preparedStatement.setString(2,customer.getAddress()!=null ? customer.getAccount() : "");
                    preparedStatement.setString(3, customer.getPhone());
                    //prevent  date null
                    if(customer.getBirthday()!=null){
                        preparedStatement.setDate(4, Date.valueOf(customer.getBirthday()));
                    }else{
                        preparedStatement.setNull(4, Types.DATE);
                    }


                    preparedStatement.setString(5,customer.getGender());
                    preparedStatement.setString(6,customer.getEmail());
                    preparedStatement.setInt(7,customer.getEmailStatus());
                    preparedStatement.setString(8,customer.getAccount());


                    return preparedStatement;
                });
                System.out.println("Update Successfully...");

            }catch (RuntimeException e){
                System.err.println("Database update failed: " + e.getMessage());
                e.printStackTrace();
            }
        }
            





    }



    private Customer getCustomerFromDB(ResultSet rs) throws SQLException {
        Customer customer = new Customer();
        try{
            customer.setName(rs.getString("name"));
            customer.setAddress(rs.getString("address"));
            customer.setPhone(rs.getString("phone"));
            customer.setEmail(rs.getString("email"));
            customer.setGender(rs.getString("gender"));
            customer.setAccount(rs.getString("account"));
            customer.setSalt(rs.getString("salt"));
            customer.setEmailStatus(rs.getInt("email_Status"));
            if(rs.getString("birthday")==null){
                customer.setBirthday(null);
            }else{
                customer.setBirthday(LocalDate.parse(rs.getString("birthday")));
            }
            customer.setPassword(rs.getString("password_hash"));
        }catch (SQLException e){
            throw new SQLException("SQL出現異常",e);
        }

        return customer;

    }
    //計算查詢條件的數量
    private String calNumOfCondition(List<String> list){
        if(list.size()==0){
            return "";
        }
        StringBuilder name = new StringBuilder();
        for(int i = 0; i<list.size();i++){
            if(i!=list.size()-1){
                name.append("?,");
            }else {
                name.append("?");
            }
        }
        return name.toString();
    }
    //建立查詢用戶且符合條件的sql指令
    private String sqlCommand(String colName, String conditionNum){
        if(conditionNum.isEmpty()){
            return "";
        } else if (conditionNum.equals("?")) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(colName);
            stringBuilder.append(" =");
            stringBuilder.append(conditionNum);
            return stringBuilder.toString();
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(colName);
        stringBuilder.append(" in (");
        stringBuilder.append(conditionNum);
        stringBuilder.append(")");
        return stringBuilder.toString();

    }



}
