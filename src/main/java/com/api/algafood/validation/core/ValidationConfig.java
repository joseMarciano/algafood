package com.api.algafood.validation.core;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class ValidationConfig {

    /*
     *LocalValidatorFactoryBean é a classe que faz integração e
     *configuração do BeanValidation com o Spring
     */

    @Bean
    public LocalValidatorFactoryBean validator(MessageSource messageSource) {
        /*
         *MessageSource é a classe responsável
         *por ler os parametros de messages.properties
         *e fazer a resolução de mensagens
         *
         */

        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource);
        System.out.println("********************************************");
        System.out.println("********************************************");
        System.out.println("*****VALIDATOR FACTORY BEAN FOI CHAMADO*****");
        System.out.println("********************************************");
        System.out.println("********************************************");
        System.out.println("********************************************");
        return bean;
    }
}
