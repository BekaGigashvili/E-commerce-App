package com.javaprojects.ecommerce.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    List<CartItem> cartItems = new ArrayList<>();

    public void addCartItem(CartItem cartItem) {
        cartItems.add(cartItem);
    }
    public void removeCartItem(CartItem cartItem) {
        cartItems.remove(cartItem);
    }
    public List<CartItem> getCartItems() {
        return cartItems;
    }
    public void clear(){
        cartItems.clear();
    }
}
