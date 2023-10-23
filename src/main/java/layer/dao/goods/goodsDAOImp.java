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
        String sql = "INSERT INTO goods (goods_ID,name,description, price,  brand, created_at) VALUES (?,?,?,?,?,?)";
        jdbcTemplate.query(conn -> {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, goods.getGoods_ID());
            preparedStatement.setString(2, goods.getName());
            preparedStatement.setString(3,goods.getDescription());
            preparedStatement.setFloat(4,goods.getPrice());
            preparedStatement.setString(5,goods.getBrand());
            preparedStatement.setTimestamp(6,goods.getCreatedTime());

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
        String sql = "update Goods set  brand=?, description=?, price=?, created_at=?, name=? where id=?";
        jdbcTemplate.query(conn -> {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, goods.getBrand());
            preparedStatement.setString(2, goods.getDescription());
            preparedStatement.setFloat(3, goods.getPrice());
            preparedStatement.setTimestamp(4, goods.getCreatedTime());
            preparedStatement.setString(5, goods.getName());
            preparedStatement.setInt(6,goods.getGoods_ID());

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
            goods.setGoods_ID(rs.getInt("goods_ID"));
            goods.setName(rs.getString("name"));
            goods.setBrand(rs.getString("brand"));
            goods.setDescription(rs.getString("description"));
            goods.setPrice(rs.getFloat("price"));
            goods.setCreatedTime(rs.getTimestamp("created_at"));

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
        String sql = "Select * from goods";
        jdbcTemplate.query(conn -> {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            return preparedStatement;
        },rs -> {
            Goods goods = new Goods();
            goods.setGoods_ID(rs.getInt("goods_ID"));
            goods.setName(rs.getString("name"));
            goods.setBrand(rs.getString("brand"));
            goods.setDescription(rs.getString("description"));
            goods.setCreatedTime(rs.getTimestamp("created_at"));
            goods.setPrice(rs.getFloat("price"));

            list.add(goods);

        });


        return list;
    }
}
