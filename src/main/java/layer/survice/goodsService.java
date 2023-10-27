package layer.survice;

import layer.domain.goods.Goods;

import java.util.List;

public interface goodsService {
 List<Goods> findAll();
 List<Goods> queryByStartEnd(int start, int end);
 void createGoods(Goods goods);
}
