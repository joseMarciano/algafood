package com.api.algafood.core.modelmapper;


import com.api.algafood.api.model.representation.endereco.EnderecoCompletaListagem;
import com.api.algafood.domain.model.embeddable.Endereco;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
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
        var modelMapper = new ModelMapper();
        System.out.println("********************************************");
        System.out.println("*****     MODEL MAPPER INSTANCIADO     *****");
        System.out.println("********************************************");

        /* Fiz um mapeamento de endereco para enderecoCompletaListagem
         * Com diferença de que nós adicionamos apenas o nome do estado dentro de
         * cidadeRepresentation
         *
         */
        TypeMap<Endereco, EnderecoCompletaListagem> enderecoToRepresentation =
                modelMapper.createTypeMap(Endereco.class, EnderecoCompletaListagem.class);
        enderecoToRepresentation.<String>addMapping(
                endereco -> {
                    //
                    return endereco.getCidade().getEstado().getNome();
                    },
                (enderecoCompletaListagem, value) ->{
                 //
                 enderecoCompletaListagem.getCidade().setEstado(value);
                });

        return modelMapper;
    }


}
