package com.react.framework.utils;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 分页工具类
 *
 * @param <T>
 */
public class PageUtils<T> {
  public static final String PAGE_SIZE = "20";
  public static final String DEFAULT_SORT = "updateAt,desc";

  private Sort.Direction getSortDirection(String direction) {
    if (direction.toUpperCase().equals("ASC")) {
      return Sort.Direction.ASC;
    } else if (direction.toUpperCase().equals("DESC")) {
      return Sort.Direction.DESC;
    }
    return Sort.Direction.ASC;
  }

  public List<Sort.Order> getOrders(String[] sort) {
    List<Sort.Order> orders = new ArrayList<>();

    if (sort[0].contains(",")) {
      // will sort more than 2 fields
      // sortOrder="field, direction"
      for (String sortOrder : sort) {
        String[] _sort = sortOrder.split(",");
        orders.add(new Sort.Order(getSortDirection(_sort[1]), _sort[0]));
      }
    } else {
      // sort=[field, direction]
      orders.add(new Sort.Order(getSortDirection(sort[1]), sort[0]));
    }
    return orders;
  }

  public Map<String, Object> getResponse(Page<T> pageObject) {
    Map<String, Object> response = new HashMap<>();
    response.put("contents", pageObject.getContent());
    response.put("currentPage", pageObject.getNumber());
    response.put("totalItems", pageObject.getTotalElements());
    response.put("totalPages", pageObject.getTotalPages());

    return response;
  }
}
