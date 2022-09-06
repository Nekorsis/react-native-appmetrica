/*
 * Version for React Native
 * © 2020 YANDEX
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * https://yandex.com/legal/appmetrica_sdk_agreement/
 */

package com.yandex.metrica.plugin.reactnative;

import java.util.ArrayList;
import java.util.List;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.yandex.metrica.YandexMetrica;
import com.yandex.metrica.ecommerce.ECommerceEvent;
import com.yandex.metrica.ecommerce.ECommerceCartItem;
import com.yandex.metrica.ecommerce.ECommerceOrder;


public class AppMetricaECommerce {
    
//    private static final String TAG = "AppMetricaModule:ECommerce";

    public AppMetricaECommerce() {
    }

   public void addCartItemEvent(ReadableMap data) {
    ECommerceCartItem cartItem = ECommerceMapper.cartItem(data);

    ECommerceEvent addCartItemEvent = ECommerceEvent.addCartItemEvent(cartItem);

    YandexMetrica.reportECommerce(addCartItemEvent);
   }

   public void removeCartItemEvent(ReadableMap cartItemData) {
        ECommerceCartItem cartItem = ECommerceMapper.cartItem(cartItemData);

        ECommerceEvent removeCartItemEvent = ECommerceEvent.removeCartItemEvent(cartItem);

        YandexMetrica.reportECommerce(removeCartItemEvent);
    }

    public void beginCheckoutEvent(ReadableMap order) {
        ECommerceOrder purchaseOrder = ECommerceMapper.order(order);

        ECommerceEvent purchaseEvent = ECommerceEvent.beginCheckoutEvent(purchaseOrder);

        YandexMetrica.reportECommerce(purchaseEvent);
    }


    public void purchaseEvent(ReadableMap order) {
        ECommerceOrder purchaseOrder = ECommerceMapper.order(order);

        ECommerceEvent purchaseEvent = ECommerceEvent.purchaseEvent(purchaseOrder);

        YandexMetrica.reportECommerce(purchaseEvent);
    }
}


