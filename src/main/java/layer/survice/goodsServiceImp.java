package layer.survice;

import layer.dao.goods.GoodsDAO;
import layer.dao.goods.goodsDAOImp;
import layer.domain.goods.Goods;

import java.util.List;

public class goodsServiceImp implements goodsService{
    GoodsDAO goodsDAO = new goodsDAOImp();
    @Override
    public List<Goods> findAll() {
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

    @Override
    public Goods findByPk(String id) {
        Goods goods= new Goods();
        goods = goodsDAO.findByPk(id);

        return goods;
    }
}
