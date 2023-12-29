package layer.survice;

import layer.dao.goodsItem.goodsItemDAO;
import layer.dao.goodsItem.goodsItemDAOImp;

public class goodsItemService {
    goodsItemDAO goodsItemDAO = new goodsItemDAOImp();
    public String findGoodsItemIdBySize(String goods_id, int size){
        String id = goodsItemDAO.findGoodsItemIdBySize(goods_id,size);
        return id;
    }


}
