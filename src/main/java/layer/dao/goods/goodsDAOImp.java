package layer.dao.goods;

import db.core.JDBCTemplate;
import layer.domain.goods.Goods;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class goodsDAOImp implements GoodsDAO {
    JDBCTemplate jdbcTemplate = new JDBCTemplate();
    @Override
    public void create(Goods goods) {
        String sql = "INSERT INTO goods (goods_ID,name,description, price,  brand, created_at, quantity) VALUES (?,?,?,?,?,?,?)";
        jdbcTemplate.query(conn -> {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, goods.getGoods_ID());
            preparedStatement.setString(2, goods.getName());
            preparedStatement.setString(3,goods.getDescription());
            preparedStatement.setFloat(4,goods.getPrice());
            preparedStatement.setString(5,goods.getBrand());
            preparedStatement.setTimestamp(6,goods.getCreatedTime());
            preparedStatement.setInt(7,goods.getQuantity());

            return preparedStatement;
        });
    }

    @Override
    public void remove(String pk) {
        String sql = "Delete from goods where id = ?";
        jdbcTemplate.query(conn -> {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,pk);

            return preparedStatement;
        });
    }

    @Override
    public void modify(Goods goods) {
        String sql = "update Goods set  brand=?, description=?, price=?, created_at=?, name=?, quantity=? where id=?";
        jdbcTemplate.query(conn -> {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, goods.getBrand());
            preparedStatement.setString(2, goods.getDescription());
            preparedStatement.setFloat(3, goods.getPrice());
            preparedStatement.setTimestamp(4, goods.getCreatedTime());
            preparedStatement.setString(5, goods.getName());
            preparedStatement.setString(6,goods.getGoods_ID());
            preparedStatement.setInt(7,goods.getQuantity());

            return preparedStatement;
        });
    }

    @Override
    public Goods findByPk(String pk) {
        List<Goods> list = new ArrayList<>();
        String sql = "Select * from goods where id = ?";
        jdbcTemplate.query(conn -> {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,pk);
            return preparedStatement;
        },rs -> {
            Goods goods = new Goods();
            goods.setGoods_ID(rs.getString("goods_ID"));
            goods.setName(rs.getString("name"));
            goods.setBrand(rs.getString("brand"));
            goods.setDescription(rs.getString("description"));
            goods.setPrice(rs.getFloat("price"));
            goods.setCreatedTime(rs.getTimestamp("created_at"));
            goods.setQuantity(rs.getInt("quantity"));

            list.add(goods);
        });

        if(list.size()==0){
            return null;
        }

        return list.get(0);
    }

    @Override
    public List<Goods> findAll() {
        List<Goods> list = new ArrayList<>();
        String sql = "Select * from goods order by created_at DESC ";
        jdbcTemplate.query(conn -> {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            return preparedStatement;
        },rs -> {
            Goods goods = new Goods();
            goods.setGoods_ID(rs.getString("goods_ID"));
            goods.setName(rs.getString("name"));
            goods.setBrand(rs.getString("brand"));
            goods.setDescription(rs.getString("description"));
            goods.setCreatedTime(rs.getTimestamp("created_at"));
            goods.setPrice(rs.getFloat("price"));
            goods.setQuantity(rs.getInt("quantity"));

            list.add(goods);

        });


        return list;
    }

    @Override
    public List<Goods> findStartEnd(int start, int end){ // 如果要查10筆資料 11-20，就要輸入10,20
        List<Goods> goodsList = new ArrayList<>();
        StringBuilder sql = new StringBuilder("select * from goods ");
        sql.append(" order by created_at DESC");
        sql.append(" limit " + (end-start));
        sql.append(" offset " + start);

        System.out.println(sql);
        jdbcTemplate.query(conn -> {
            PreparedStatement preparedStatement = conn.prepareStatement(String.valueOf(sql));
            return preparedStatement;
        },rs -> {
            Goods goods = new Goods();
            goods.setGoods_ID(rs.getString("goods_ID"));
            goods.setBrand(rs.getString("brand"));
            goods.setDescription(rs.getString("description"));
            goods.setName(rs.getString("name"));
            goods.setPrice(rs.getFloat("price"));
            goods.setCreatedTime(rs.getTimestamp("created_at"));
            goods.setQuantity(rs.getInt("quantity"));
            goodsList.add(goods);
        });




        return goodsList;


    }
}
