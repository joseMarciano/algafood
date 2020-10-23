package com.api.algafood.api.assembler;

import java.util.List;

public interface Converter<ENTITY, OUTPUT, INPUT> {

    ENTITY toDomainObject(INPUT inputDTO, Class<ENTITY> type);

    OUTPUT toDTO(ENTITY domain, Class<OUTPUT> type);

    List<OUTPUT> toCollectionDTO(List<ENTITY> list, Class<OUTPUT> type);

}
