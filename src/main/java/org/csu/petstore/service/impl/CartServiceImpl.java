package org.csu.petstore.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.servlet.http.HttpSession;
import org.csu.petstore.entity.Cart;
import org.csu.petstore.persistence.CartMapper;
import org.csu.petstore.persistence.ItemQuantityMapper;
import org.csu.petstore.service.CartService;
import org.csu.petstore.service.CatalogService;
import org.csu.petstore.vo.CartItem;
import org.csu.petstore.vo.CartItemListMapper;
import org.csu.petstore.vo.ItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service("cartService")
public class CartServiceImpl implements CartService {

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private ItemQuantityMapper itemQuantityMapper;

    @Autowired
    private CatalogService catalogService;

    @Autowired
    private HttpSession session;

    @Override
    public List<Cart> getCartListByUsername(String username) {
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return cartMapper.selectList(queryWrapper);
    }

    @Override
    public CartItemListMapper getCartItemListByUsername(String username) {
        CartItemListMapper cartItemListMapper = new CartItemListMapper();
        List<CartItem> cartItemList = new ArrayList<>();
        List<Cart> cartList = getCartListByUsername(username);

        for (Cart cart : cartList) {
            CartItem cartItem = new CartItem();
            cartItem.setCart(cart);
            ItemVO itemVO = catalogService.getItem(cart.getItemId());
            cartItem.setItemVO(itemVO);

            // 获取库存信息
            Integer stockQuantity = itemQuantityMapper.selectById(cart.getItemId()) != null
                    ? itemQuantityMapper.selectById(cart.getItemId()).getQuantity()
                    : 0;
            cartItem.setInStock(stockQuantity);

            // 计算总价
            BigDecimal listPrice = itemVO.getListPrice() != null ? itemVO.getListPrice() : BigDecimal.ZERO;
            cartItem.setTotal(listPrice.multiply(new BigDecimal(cart.getQuantity())));

            cartItemList.add(cartItem);
        }
        cartItemListMapper.setCartItemList(cartItemList);
        return cartItemListMapper;
    }

    @Override
    public void addItemToCart(String username, String itemId) {
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username).eq("itemId", itemId).eq("isRemoved", 0);
        System.out.println(cartMapper.selectOne(queryWrapper));
        Cart cart = cartMapper.selectOne(queryWrapper);
        ItemVO itemVO = catalogService.getItem(itemId);


        if (cart != null) {
            cart.setQuantity(cart.getQuantity() + 1);
            cart.setTotalCost(cart.getListPrice().multiply(new BigDecimal(cart.getQuantity())));
            cartMapper.update(cart, queryWrapper);



        } else {
            cart = new Cart();
            cart.setUsername(username);
            cart.setItemId(itemId);
            cart.setQuantity(1);
            cart.setProductId(itemVO.getProductId());
            cart.setDescription(itemVO.getDescriptionText());
            cart.setIn_stock(itemQuantityMapper.selectById(itemId) != null
                    ? itemQuantityMapper.selectById(itemId).getQuantity()
                    : 0);
            cart.setListPrice(itemVO.getListPrice());
            cart.setTotalCost(itemVO.getListPrice().multiply(new BigDecimal(cart.getQuantity())));
            cart.setIsRemoved(0);
            cartMapper.insert(cart);
        }

    }

    @Override
    public void updateCartItemQuantity(String username, String itemId, int quantity) {
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username).eq("itemId", itemId).eq("isRemoved",0);
        Cart cart = cartMapper.selectOne(queryWrapper);

        if (cart != null) {
            cart.setQuantity(quantity);
//            cart.setIn_stock(cart.getIn_stock() - quantity);
            cart.setTotalCost(cart.getListPrice().multiply(new BigDecimal(quantity)));
            cartMapper.update(cart, queryWrapper);
        }
    }

    @Override
    public void removeItem(String username, String itemId) {
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username).eq("itemId", itemId);
        Cart cart = cartMapper.selectOne(queryWrapper);

        if (cart != null) {
            cart.setIsRemoved(1);
            cartMapper.update(cart, queryWrapper);
        }
    }

    @Override
    public void clearCart(String username) {
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        List<Cart> cartList = cartMapper.selectList(queryWrapper);

        for (Cart cart : cartList) {
            cart.setIsRemoved(1);
            cartMapper.update(cart, queryWrapper);
        }
    }

    @Override
    public void updateCartByCartItemList(List<CartItem> cartItemList, String username) {
        for (CartItem cartItem : cartItemList) {
            Cart cart = cartItem.getCart();
            int quantity = cart.getQuantity();
            if (quantity < 1) {
                removeItem(username, cart.getItemId());
            } else {
                updateCartItemQuantity(username, cart.getItemId(), quantity);
            }
        }
    }
}