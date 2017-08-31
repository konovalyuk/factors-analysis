package com.intapp.platform.factorial.service.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by maksymk on 4/5/2017.
 */
@Service
public class DtoConverter {

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Dto convertor r.
     *
     * @param <T>                the type parameter
     * @param <R>                the type parameter
     * @param item               the item
     * @param typeParameterClass the type parameter class
     * @return the r
     */
    public <T,R> R dtoConvertor(T item, Class<R> typeParameterClass) {
        return modelMapper.map(item, typeParameterClass);

    }

    /**
     * Dto list convertor list.
     *
     * @param <T>                the type parameter
     * @param <R>                the type parameter
     * @param list               the list
     * @param typeParameterClass the type parameter class
     * @return the list
     */
    public <T,R> List<R> dtoListConvertor(List<T> list, Class<R> typeParameterClass) {
        return list.stream()
                .map(item -> modelMapper.map(item, typeParameterClass))
                .collect(Collectors.toList());
    }
}
