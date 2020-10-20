package com.api.algafood.core.modelmapper;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*Criando uma classe configuration para o ModelMapper já que é uma biblioteca de terceiro...
 * Com isso, a classe não vem com a anotação @Component, fazendo com que precise criar um classe de configuração
 * para retornar uma instância de ModelMapper para eu poder fazer injeção de dependencia
 */
@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        System.out.println("********************************************");
        System.out.println("*****     MODEL MAPPER INSTANCIADO     *****");
        System.out.println("********************************************");
        return new ModelMapper();
    }


}
