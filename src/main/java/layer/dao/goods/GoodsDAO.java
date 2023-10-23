package layer.dao.goods;

import layer.domain.goods.Goods;

import java.util.List;

public interface GoodsDAO {
    void create(Goods goods);
    void remove(String pk);
    void modify(Goods goods);
    Goods findByPk(String pk);
    List<Goods> findAll();
}
