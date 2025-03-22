package org.csu.petstore.service;


import org.csu.petstore.entity.Cart;
import org.csu.petstore.vo.CartItem;
import org.csu.petstore.vo.CartItemListMapper;
import java.util.List;

public interface CartService {
    List<Cart> getCartListByUsername(String username);
    CartItemListMapper getCartItemListByUsername(String username);
    void addItemToCart(String username, String itemId);
    void updateCartItemQuantity(String username, String itemId, int quantity);
    void removeItem(String username, String itemId);
    void clearCart(String username);
    void updateCartByCartItemList(List<CartItem> cartItemList, String username);
}