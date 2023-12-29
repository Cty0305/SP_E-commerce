package layer.dao.goodsItem;

import db.core.JDBCTemplate;
import layer.domain.goods.goodsItem;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class goodsItemDAOImp implements goodsItemDAO{

    JDBCTemplate jdbcTemplate = new JDBCTemplate();

    @Override
    public layer.domain.goods.goodsItem findById(String id) {
        List<goodsItem> list = new ArrayList<>();
        String sql = "Select * from goods_item where goods_item_ID = ?";
        jdbcTemplate.query(conn -> {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,id);
            return preparedStatement;
        },rs -> {
            goodsItem goodsItem = new goodsItem();
            goodsItem.goods.setGoods_ID(rs.getString("goods_ID"));
            goodsItem.setQuantity(rs.getInt("quantity"));
            goodsItem.setGoodsItem_ID(rs.getString("goods_item_ID"));
            goodsItem.setSize(rs.getInt("size"));
            list.add(goodsItem);

        });

        return list.get(0);
    }

    @Override
    public void modify(layer.domain.goods.goodsItem goodsItem) {
        jdbcTemplate.query(conn -> {
            String sql = "update goods_item set size = ?, quantity = ? where goods_item_ID = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, goodsItem.getSize());
            preparedStatement.setInt(2,goodsItem.getQuantity());
            preparedStatement.setString(3, goodsItem.getGoodsItem_ID());

            return preparedStatement;
        });
    }

    @Override
    public void remove(String id) {
        jdbcTemplate.query(conn -> {
            String sql = "DELETE from goods_item where goods_item_ID = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,id);
            return preparedStatement;

        });

    }

    @Override
    public void create(layer.domain.goods.goodsItem goodsItem) {
        jdbcTemplate.query(conn -> {
            String sql = "INSERT INTO goods_item (goods_ID, size, goods_item_ID, quantity) values (?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,goodsItem.getGoodsItem_ID());
            preparedStatement.setInt(2,goodsItem.getSize());
            preparedStatement.setString(3,goodsItem.goods.getGoods_ID());
            preparedStatement.setInt(4,goodsItem.getSize());

            return preparedStatement;
        });
    }

    @Override
    public String findGoodsItemIdBySize(String goods_ID, int size) {
        String sql = "Select * from goods_item where goods_ID = ? and size = ?";
        List<String> list = new ArrayList<>();
        jdbcTemplate.query(conn -> {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,goods_ID);
            preparedStatement.setInt(2,size);
            return preparedStatement;
        },rs -> {
            String id = rs.getString("goods_item_ID");
            list.add(id);
        });
        return list.get(0);
    }
}
