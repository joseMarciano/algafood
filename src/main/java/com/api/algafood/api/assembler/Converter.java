package com.api.algafood.api.assembler;

import java.util.List;

public interface Converter<T,S,U> {

    public T toDomainObject (U inputDTO);
    public  S toDTO(T domain);
    public List<S> toCollectionDTO(List<T> list);
}
