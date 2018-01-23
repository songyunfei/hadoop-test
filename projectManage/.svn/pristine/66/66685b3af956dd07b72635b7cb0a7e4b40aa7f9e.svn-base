
package com.linkGap.projectManage.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
* @author renhengli
* @date 2017年11月21日
* @version 1.0 
*/
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	/**
	 * 防止一些静态css中引用图片中带有/static路径，因此加上static的映射
	 */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //将所有/static/** 访问都映射到classpath:/static/ 目录下
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
}

