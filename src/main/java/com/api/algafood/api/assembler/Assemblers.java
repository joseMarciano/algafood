package com.api.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Assemblers<ENTITY,OUTPUT,INPUT> implements Converter<ENTITY, OUTPUT, INPUT> {

    private ModelMapper modelMapper;

    public Assemblers(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
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
    public List<OUTPUT> toCollectionDTO(Collection<ENTITY> list, Class<OUTPUT> type) {
        return list.stream().map(item -> toDTO(item,type)).collect(Collectors.toList());
    }
}
