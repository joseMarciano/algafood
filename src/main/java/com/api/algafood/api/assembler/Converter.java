package com.api.algafood.api.assembler;

import java.util.List;

public interface Converter<ENTITY, OUTPUT, INPUT> {

    public ENTITY toDomainObject(INPUT inputDTO);

    public OUTPUT toDTO(ENTITY domain);

    public List<OUTPUT> toCollectionDTO(List<ENTITY> list);

    public void copyToDomainObject(INPUT inputDTO, ENTITY domain);
}
