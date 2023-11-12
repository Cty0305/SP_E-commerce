package layer.survice;
import layer.dao.cart.cartDAO;
import layer.dao.cart.cartDAOImp;
import layer.dao.goods.GoodsDAO;
import layer.dao.goods.goodsDAOImp;
import layer.domain.goods.Cart;
import layer.domain.goods.Goods;

import java.util.List;
import java.util.UUID;

public class cartService {

    cartDAO cartDAO = new cartDAOImp();
    public List<Cart> findByAccount(String account){
        GoodsDAO goodsDAO = new goodsDAOImp();
        Cart cart = new Cart();
        List<Cart> cartList  = cartDAO.findByAccount(account);
        for (Cart cartItem:cartList ){
            Goods goods = goodsDAO.findByPk(cartItem.getGoods_ID());
            cartItem.setGoods(goods);
        }


        return cartList;
    }

    public void addToCart(Cart cart){
        List<Cart> cartList = cartDAO.findByAccount(cart.getAccount());
        int quantity=0;
        String cart_ID;
        int flag = 0;
        for(Cart dbcart:cartList){
            if(dbcart.getGoods_ID().equals(cart.getGoods_ID())){
                quantity = dbcart.getQuantity()+1;
                cart_ID = dbcart.getCart_ID();
                cart.setQuantity(quantity);
                cart.setCart_ID(cart_ID);
                cartDAO.modify(cart);
                flag = 1;
            }
        }
        if(flag==0){
            cart.setQuantity(1);
            cart.setCart_ID(UUID.randomUUID().toString());
            cartDAO.create(cart);
        }
    }




}
