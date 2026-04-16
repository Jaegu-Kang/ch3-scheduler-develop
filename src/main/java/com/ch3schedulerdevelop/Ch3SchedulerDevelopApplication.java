package com.ch3schedulerdevelop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Ch3SchedulerDevelopApplication {

    public static void main(String[] args) {
        SpringApplication.run(Ch3SchedulerDevelopApplication.class, args);
    }

}
