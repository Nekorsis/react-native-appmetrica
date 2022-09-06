package com.yandex.metrica.plugin.reactnative;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.yandex.metrica.ecommerce.ECommerceAmount;
import com.yandex.metrica.ecommerce.ECommerceCartItem;
import com.yandex.metrica.ecommerce.ECommerceOrder;
import com.yandex.metrica.ecommerce.ECommercePrice;
import com.yandex.metrica.ecommerce.ECommerceProduct;

import java.util.ArrayList;
import java.util.List;

abstract class ECommerceMapper {
    static ECommerceOrder order(ReadableMap order) {
        ReadableArray cartItemsData = order.getArray("cartItems");
        List<ECommerceCartItem> cartItems = new ArrayList<>();
        int size = cartItemsData.size();
        for(int i  = 0; i < size; i ++) {
            ReadableMap data = cartItemsData.getMap(i);
            cartItems.add(ECommerceMapper.cartItem(data));
        }

        String id = order.getString("id");

        return new ECommerceOrder(id, cartItems);
    }
    static ECommerceCartItem cartItem(ReadableMap cartItemData) {
        Double quantity = cartItemData.getDouble("quantity");

        ECommerceProduct product = product(cartItemData.getMap("product"));

        return new ECommerceCartItem(product, product.getActualPrice(), quantity);
    }

    static ECommerceProduct product(ReadableMap productData) {
        String id = productData.getString("id");
        String name = productData.getString("name");

        ECommercePrice actualPrice = actualPrice(productData);

        return new ECommerceProduct(id)
                .setActualPrice(actualPrice)
                //      .setPayload(payload) // Optional.
                //      .setOriginalPrice(originalPrice) // Optional.
                .setName(name); // Optional.
    }

    static ECommercePrice actualPrice(ReadableMap productData) {
        return price(productData.getMap("actualPrice"));
    }
    static ECommercePrice originalPrice(ReadableMap productData) {
        return price(productData.getMap("originalPrice"));
    }

    static ECommercePrice price(ReadableMap price) {
        Double amount = price.getDouble("value");
        String currency = price.getString("currency");
        return new ECommercePrice(new ECommerceAmount(amount, currency));
        //               .setInternalComponents(Arrays.asList( // Optional.
        //                       new ECommerceAmount(30_590_000, "wood"),
        //                       new ECommerceAmount(26.92, "iron"),
        //                       new ECommerceAmount(new BigDecimal(5.5), "gold")
        //               ));
    }
}
