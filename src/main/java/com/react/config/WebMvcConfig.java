package com.react.config;

import cn.dev33.satoken.interceptor.SaAnnotationInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@Slf4j
public class WebMvcConfig implements WebMvcConfigurer {
  /**
   * 注册sa-token的拦截器，打开注解式鉴权功能 (如果您不需要此功能，可以删除此类)
   *
   * @param registry
   */
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    // 注册注解拦截器，并排除不需要注解鉴权的接口地址 (与登录拦截器无关)
    registry
        .addInterceptor(new SaAnnotationInterceptor())
        .addPathPatterns("/**")
        .excludePathPatterns(
            "/error",
            "/swagger-resources/**",
            "/account/login",
            "/account/register",
            "/captcha",
            "/thumbnail");
  }

  //  @Override
  //  public void addResourceHandlers(ResourceHandlerRegistry registry) {
  //    File file = new File("web");
  //    log.info(file.getAbsolutePath());
  //    registry
  //        .addResourceHandler("/admin/**")
  //        .addResourceLocations("file:" + file.getAbsolutePath() + "/admin/");
  //  }

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry
        .addMapping("/**")
        //        .allowedOrigins("*")
        .allowedOriginPatterns("*")
        .allowCredentials(true)
        .allowedMethods("*")
        .maxAge(3600);
  }
}
