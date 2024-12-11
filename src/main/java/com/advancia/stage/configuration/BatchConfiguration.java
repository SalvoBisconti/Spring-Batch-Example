package com.advancia.stage.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

@Configuration
public class BatchConfiguration {

    @Bean
    public Resource myFileResource() {
        return new FileSystemResource("./fileMessages.txt");
        
    }

}
