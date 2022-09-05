/*
 * Version for React Native
 * © 2020 YANDEX
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * https://yandex.com/legal/appmetrica_sdk_agreement/
 */

package com.yandex.metrica.plugin.reactnative;

import android.app.Activity;
import android.util.Log;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.yandex.metrica.YandexMetrica;
import com.yandex.metrica.ecommerce.ECommerceEvent;
import com.yandex.metrica.ecommerce.ECommercePrice;
import com.yandex.metrica.ecommerce.ECommerceAmount;
import com.yandex.metrica.ecommerce.ECommerceCartItem;
import com.yandex.metrica.ecommerce.ECommerceProduct;
import com.yandex.metrica.ecommerce.ECommerceOrder;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class AppMetricaECommerce {
    public AppMetricaECommerce() {

    }

   public void addCartItemEvent(ReadableMap cartItem) {
       HashMap data = cartItem.toHashMap();

       HashMap productData = (HashMap)data.get("product");
       Double quantity = (Double)data.get("quantity");

       String id = (String)productData.get("id");
       HashMap price = (HashMap)productData.get("actualPrice");
       String name = (String)productData.get("name");

       Double amount = (Double)price.get("value");
       String currency = (String)price.get("currency");



       Log.d("AM:AMOUNT", amount.toString());
       Log.d("AM:ID", id);
       Log.d("AM:QUANTITY", quantity.toString());
       Log.d("AM:CURRENCY: ", currency.toString());

       ECommercePrice actualPrice = new ECommercePrice(new ECommerceAmount(amount, currency));
//               .setInternalComponents(Arrays.asList( // Optional.
//                       new ECommerceAmount(30_590_000, "wood"),
//                       new ECommerceAmount(26.92, "iron"),
//                       new ECommerceAmount(new BigDecimal(5.5), "gold")
//               ));

    ECommerceProduct product = new ECommerceProduct(id)
      .setActualPrice(actualPrice) // Optional.
//      .setPayload(payload) // Optional.
//      .setOriginalPrice(originalPrice) // Optional.
      .setName(name); // Optional.

    ECommerceCartItem addedItem = new ECommerceCartItem(product, actualPrice, quantity);

    ECommerceEvent addCartItemEvent = ECommerceEvent.addCartItemEvent(addedItem);

    YandexMetrica.reportECommerce(addCartItemEvent);
   }

   public void removeCartItemEvent(ReadableMap cartItem) {
        HashMap data = cartItem.toHashMap();

        HashMap productData = (HashMap)data.get("product");
        Double quantity = (Double)data.get("quantity");

        String id = (String)productData.get("id");
        HashMap price = (HashMap)productData.get("actualPrice");
        String name = (String)productData.get("name");

        Double amount = (Double)price.get("value");
        String currency = (String)price.get("currency");



        Log.d("AM:AMOUNT", amount.toString());
        Log.d("AM:ID", id);
        Log.d("AM:QUANTITY", quantity.toString());
        Log.d("AM:CURRENCY: ", currency.toString());

        ECommercePrice actualPrice = new ECommercePrice(new ECommerceAmount(amount, currency));
//               .setInternalComponents(Arrays.asList( // Optional.
//                       new ECommerceAmount(30_590_000, "wood"),
//                       new ECommerceAmount(26.92, "iron"),
//                       new ECommerceAmount(new BigDecimal(5.5), "gold")
//               ));

        ECommerceProduct product = new ECommerceProduct(id)
                .setActualPrice(actualPrice) // Optional.
//      .setPayload(payload) // Optional.
//      .setOriginalPrice(originalPrice) // Optional.
                .setName(name); // Optional.

        ECommerceCartItem removeItem = new ECommerceCartItem(product, actualPrice, quantity);

        ECommerceEvent removeCartItemEvent = ECommerceEvent.removeCartItemEvent(removeItem);

        YandexMetrica.reportECommerce(removeCartItemEvent);
    }

    public void purchaseEvent(ReadableMap order) {
        HashMap orderData = order.toHashMap();

        ArrayList<HashMap> cartItemsData = (ArrayList<HashMap>)orderData.get("cartItems");
        List<ECommerceCartItem> cartItems = new ArrayList<ECommerceCartItem>();

        for (HashMap data : cartItemsData) {
            Double quantity = (Double)data.get("quantity");
            HashMap productData = (HashMap)data.get("product");

            String id = (String)productData.get("id");
            HashMap price = (HashMap)productData.get("actualPrice");
            String name = (String)productData.get("name");

            Double amount = (Double)price.get("value");
            String currency = (String)price.get("currency");

            Log.d("AM:AMOUNT", amount.toString());
            Log.d("AM:ID", id);
            Log.d("AM:QUANTITY", quantity.toString());
            Log.d("AM:CURRENCY: ", currency.toString());


            ECommercePrice actualPrice = new ECommercePrice(new ECommerceAmount(amount, currency));
            ECommerceProduct product = new ECommerceProduct(id)
                    .setActualPrice(actualPrice)
                    .setName(name);
            ECommerceCartItem cartItem = new ECommerceCartItem(product, actualPrice, quantity);

            cartItems.add(cartItem);
        }


        String id = (String)orderData.get("id");
        Log.d("AM:cartItems", cartItems.toString());

        ECommerceOrder purchaseOrder = new ECommerceOrder(id, cartItems);

        ECommerceEvent purchaseEvent = ECommerceEvent.purchaseEvent(purchaseOrder);

        YandexMetrica.reportECommerce(purchaseEvent);
    }
}


