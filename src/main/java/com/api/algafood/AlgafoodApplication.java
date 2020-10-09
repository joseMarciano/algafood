package com.api.algafood;

import com.api.algafood.insfrastructure.repository.CustomJpaRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
public class AlgafoodApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlgafoodApplication.class, args);
    }


    @PostConstruct // setando timeZone UTC para toda aplicação
    void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

}
