package com.example.task_2.spring_boot.configuration;

import com.example.task_2.spring_boot.ThisIsMyFirstConditionalBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomBeanConfiguration {

    @Bean
    @ConditionalOnProperty(prefix = "custom", name = "bean", havingValue = "enable")
    public ThisIsMyFirstConditionalBean getConditionalBean() {
        return new ThisIsMyFirstConditionalBean();
    }
}
