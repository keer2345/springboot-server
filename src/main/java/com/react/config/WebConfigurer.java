package com.react.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
@Slf4j
public class WebConfigurer extends WebMvcConfigurationSupport {
  private String uploadPath;

  @Override
  protected void addResourceHandlers(ResourceHandlerRegistry registry) {
    log.info("addResourceHandlers");
    uploadPath = "/home/user/xxx";
    registry.addResourceHandler("/file/images/**").addResourceLocations("file:///" + uploadPath);
    super.addResourceHandlers(registry);
  }
}
