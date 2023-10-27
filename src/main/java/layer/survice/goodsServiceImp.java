package layer.survice;

import layer.dao.goods.GoodsDAO;
import layer.dao.goods.goodsDAOImp;
import layer.domain.goods.Goods;

import java.util.List;

public class goodsServiceImp implements goodsService{
    GoodsDAO goodsDAO = new goodsDAOImp();
    @Override
    public List<Goods> findAll() {
        System.out.println("執行一次DBIO");
        return goodsDAO.findAll();
    }

    @Override
    public List<Goods> queryByStartEnd(int start, int end){
       List<Goods> goodsList = goodsDAO.findStartEnd(start,end);
       return goodsList;
    }

    @Override
    public void createGoods(Goods goods){
        goodsDAO.create(goods);
    }
}
