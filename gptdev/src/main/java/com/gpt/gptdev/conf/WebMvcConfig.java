//package com.gpt.gptdev.conf;
//
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
//
//import java.util.List;
//
//@Slf4j
//@Configuration
//public class WebMvcConfig extends WebMvcConfigurationSupport {
////    /**
////     * 设置静态资源映射
////     * @param registry
////     */
////    @Override
////    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
////        log.info("开始进行静态资源映射");
////        registry.addResourceHandler("/backend/**").addResourceLocations("classpath:/backend/");
////        registry.addResourceHandler("/front/**").addResourceLocations("classpath:/front/");
////    }
//
//    /**
//     * 将我们在common包下JacksonObjectMapper对象映射器配置到spring容器中
//     * @param converters
//     */
//    @Override
//    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
//        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
//        mappingJackson2HttpMessageConverter.setObjectMapper(new JacksonObjectMapper());
//        converters.add(0,mappingJackson2HttpMessageConverter);
//    }
//}