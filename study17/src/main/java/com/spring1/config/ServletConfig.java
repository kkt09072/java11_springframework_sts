package com.spring1.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.spring1"})
public class ServletConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		registry.addResourceHandler("/admin/**").addResourceLocations("/WEB-INF/views/admin");
		registry.addResourceHandler("/common/**").addResourceLocations("/WEB-INF/views/common");
		registry.addResourceHandler("/include/**").addResourceLocations("/WEB-INF/views/include");
		registry.addResourceHandler("/board/**").addResourceLocations("/WEB-INF/views/board");
		registry.addResourceHandler("/databank/**").addResourceLocations("/WEB-INF/views/databank");
		registry.addResourceHandler("/check/**").addResourceLocations("/WEB-INF/views/check");
		registry.addResourceHandler("/free/**").addResourceLocations("/WEB-INF/views/free");
		registry.addResourceHandler("/member/**").addResourceLocations("/WEB-INF/views/member");
		registry.addResourceHandler("/qna/**").addResourceLocations("/WEB-INF/views/qna");
		registry.addResourceHandler("/sample/**").addResourceLocations("/WEB-INF/views/sample");
		registry.addResourceHandler("/user/**").addResourceLocations("/WEB-INF/views/user");
		registry.addResourceHandler("/util/**").addResourceLocations("/WEB-INF/views/util");
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
        InternalResourceViewResolver bean = new InternalResourceViewResolver();
        bean.setViewClass(JstlView.class);
        bean.setPrefix("/WEB-INF/views/");
        bean.setSuffix(".jsp");
        registry.viewResolver(bean);
	}

	
}
