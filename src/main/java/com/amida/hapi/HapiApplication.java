package com.amida.hapi;

import org.apache.lucene.document.Field;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ResourceCondition;

@SpringBootApplication
public class HapiApplication {

    public static void main(String[] args) {

        SpringApplication.run(HapiApplication.class, args);
    }

}
