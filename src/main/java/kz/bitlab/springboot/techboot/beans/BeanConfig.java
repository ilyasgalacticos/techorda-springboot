package kz.bitlab.springboot.techboot.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;


@Configuration
public class BeanConfig {

    @Bean(name = "ilyasBean")
    public TestB testB(){
        TestB testB = new TestB("Ilyas", 4545);
        testB.setCode("Mark");
        return testB;
    }

    @Bean(name = "johnBean")
    public TestB testBa(){
        TestB testB = new TestB("John", 9999);
        return testB;
    }

}
