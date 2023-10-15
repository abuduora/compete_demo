package com.total.goodneighbor.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        // 允许跨域请求的来源，例如：*表示允许来自任何来源的请求
        config.addAllowedOrigin("*");

        // 允许的HTTP方法，例如：GET、POST
        config.addAllowedMethod("*");

        // 允许的头部信息，例如：Authorization
        config.addAllowedHeader("*");

        // 是否支持凭证（cookies等）
        config.setAllowCredentials(true);

        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}
