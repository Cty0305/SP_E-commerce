package layer.dao.goodsItem;

import layer.domain.goods.goodsItem;

public interface goodsItemDAO {

    goodsItem findById(String id);
    void modify(goodsItem goodsItem);
    void remove(String id);
    void create(goodsItem goodsItem);
    String findGoodsItemIdBySize(String goods_ID,int size);

}
