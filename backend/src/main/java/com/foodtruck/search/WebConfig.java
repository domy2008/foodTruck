/************************************************************************************************
 * @author : cong
 * @comments: This class is responsible for configuring the CORS settings for the application.
 * 
 * ************************************************************************************************/
package com.foodtruck.search;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins("*")  // Replace with your frontend's actual origin
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
    }
}