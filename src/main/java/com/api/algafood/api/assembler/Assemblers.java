package com.api.algafood.api.assembler;

import com.api.algafood.domain.repository.CozinhaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Assemblers<ENTITY,OUTPUT,INPUT> implements Converter<ENTITY, OUTPUT, INPUT> {

    private ModelMapper modelMapper;
    private CozinhaRepository cozinhaRepository;

    public Assemblers(ModelMapper modelMapper, CozinhaRepository cozinhaRepository) {
        this.modelMapper = modelMapper;
        this.cozinhaRepository = cozinhaRepository;
    }

    @Override
    public ENTITY toDomainObject(INPUT inputDTO, Class<ENTITY> type) {
        return modelMapper.map(inputDTO, type);
    }

    @Override
    public OUTPUT toDTO(ENTITY domain, Class<OUTPUT> type) {
        return modelMapper.map(domain,type);
    }

    @Override
    public List<OUTPUT> toCollectionDTO(List<ENTITY> list, Class<OUTPUT> type) {
        return list.stream().map(item -> toDTO(item,type)).collect(Collectors.toList());
    }
}
