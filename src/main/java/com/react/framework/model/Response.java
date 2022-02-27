package com.react.framework.model;

import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.slf4j.MDC;
import org.springframework.context.annotation.DependsOn;

@Data
@AllArgsConstructor
@DependsOn
public class Response<T> {
  private final String code;

  private final String msg;

  private final T data;

  private final String requestId;

  private final Boolean success;

  public Response(String code, String msg, T data) {
    this.code = code;
    this.msg = msg;
    this.data = data;
    this.requestId = MDC.get("requestId");
    this.success = StrUtil.equals("200", code);
  }

  public static <Void> Response<Void> ok() {
    return new Response<Void>("200", "操作成功", null);
  }

  public static <T> Response<T> ok(T data) {
    return new Response<>("200", "操作成功", data);
  }

  public static <T> Response<T> ok(String msg, T data) {
    return new Response<>("200", msg, data);
  }

  public static <T> Response<T> error(String msg) {
    return new Response<>("400", msg, null);
  }

  public static <T> Response<T> error(T data) {
    return new Response<>("400", "", data);
  }

  public static <T> Response<T> error(String code, String msg) {
    return new Response<>(code, msg, null);
  }

  public static <T> Response<T> error(String code, String msg, T data) {
    return new Response<>(code, msg, data);
  }
}
