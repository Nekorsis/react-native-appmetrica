package com.yandex.metrica.plugin.reactnative;

import com.facebook.react.bridge.ReadableMap;
import com.yandex.metrica.ecommerce.ECommerceAmount;
import com.yandex.metrica.ecommerce.ECommerceCartItem;
import com.yandex.metrica.ecommerce.ECommercePrice;
import com.yandex.metrica.ecommerce.ECommerceProduct;

abstract class ECommerceMapper {
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
