package com.david.resourceserver.config.token;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
//@EnableWebMvc
@ComponentScan({ "com.david.resourceserver.resource" })
public class ResourceServerWebConfig implements WebMvcConfigurer {
    //
}