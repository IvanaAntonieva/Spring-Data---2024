package org.modelmapperexercise.service;

import org.modelmapperexercise.service.dtos.CartItemDTO;

public interface ShoppingCartService {

    String addItem(CartItemDTO item);

    String removeItem(CartItemDTO item);

    String buyItem();
}
