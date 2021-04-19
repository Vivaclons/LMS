package kz.spring.demo.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

@org.springframework.context.annotation.Configuration
@ComponentScan("kz.spring.demo.demo")
@PropertySource("/application.properties")
@EnableJpaRepositories(basePackages = "kz.spring.demo.demo.repository")
public class Configuration {

    @Bean
    public Scanner getScannerBean() {
        return new Scanner(System.in);
    }

    @Bean
    public BufferedReader getBufferedReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }
}
