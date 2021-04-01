package com.employeedia.demo;

import com.employeedia.demo.model.Employee;
import com.employeedia.demo.service.EmployeediaService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
@EnableSwagger2
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(EmployeediaService service){
        return args -> {
            //read from mock_data.json
            ObjectMapper objectMapper = new ObjectMapper();
            TypeReference<List<Employee>> typeReference = new TypeReference<>(){};
            InputStream inputStream = TypeReference.class.getResourceAsStream("/mock_data.json");
            try {
                List<Employee> employees = objectMapper.readValue(inputStream, typeReference);
                service.addEmployees(employees);
                System.out.println("Entries saved!!");
            } catch (IOException e){
                System.out.println("unable to fetch employee data: " + e.getMessage());
            }
        };
    }

    @Bean //uri -> http://localhost:8080/swagger-ui.html#/
    public Docket productApi(){
        return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("com.employeedia.demo")).build();
    }
}
