/*
 * Version for React Native
 * © 2020 YANDEX
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * https://yandex.com/legal/appmetrica_sdk_agreement/
 */

import { NativeModules } from 'react-native';

const { AppMetrica } = NativeModules;

export default {

  activate(config) {
    AppMetrica.activate(config);
  },

  // Android
  async getLibraryApiLevel() {
    return AppMetrica.getLibraryApiLevel();
  },

  async getLibraryVersion() {
    return AppMetrica.getLibraryVersion();
  },

  pauseSession() {
    AppMetrica.pauseSession();
  },

  reportAppOpen(deeplink) {
    AppMetrica.reportAppOpen(deeplink);
  },

  reportError(error) {
    AppMetrica.reportError(error);
  },

  reportEvent(eventName, attributes) {
    AppMetrica.reportEvent(eventName, attributes);
  },

  reportECommerce: () => {
    AppMetrica.reportECommerce();
  },

  reportReferralUrl(referralUrl) {
    AppMetrica.reportReferralUrl(referralUrl);
  },

  requestAppMetricaDeviceID(listener) {
    AppMetrica.requestAppMetricaDeviceID(listener);
  },

  resumeSession() {
    AppMetrica.resumeSession();
  },

  sendEventsBuffer() {
    AppMetrica.sendEventsBuffer();
  },

  setLocation(location) {
    AppMetrica.setLocation(location);
  },

  setLocationTracking(enabled) {
    AppMetrica.setLocationTracking(enabled);
  },

  setStatisticsSending(enabled) {
    AppMetrica.setStatisticsSending(enabled);
  },

  setUserProfileID(userProfileID) {
    AppMetrica.setUserProfileID(userProfileID);
  },

  // ECommerce methods
  addCartItemEvent: (data) => {
    AppMetrica.addCartItemEvent(data);
  },

  removeCartItemEvent: (data) => {
    AppMetrica.removeCartItemEvent(data);
  },

  beginCheckoutEvent: (data) => {
    AppMetrica.beginCheckoutEvent(data)
  },
  purchaseEvent: (data) => {
    AppMetrica.purchaseEvent(data)
  },
};
