package com.react.framework.cache;

public interface Cache {
  void put(String key, Object value);

  /**
   * @param key
   * @param value
   * @param timeout
   */
  void put(String key, Object value, long timeout);

  void remove(String key);

  <T> T get(String key);
}
