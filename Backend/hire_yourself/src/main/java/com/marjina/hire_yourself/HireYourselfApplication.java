package com.marjina.hire_yourself;

import com.marjina.hire_yourself.common.property.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class HireYourselfApplication {

    public static void main(String[] args) {
        SpringApplication.run(HireYourselfApplication.class, args);
    }

}
