package com.intapp.platform.factorial.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intapp.platform.factorial.persistence.IFactors;
import com.intapp.platform.factorial.service.converter.DtoConverter;
import com.intapp.platform.factorial.service.dto.FactorsDTOPost;
import com.intapp.platform.factorial.service.dto.FactorsDTOGet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


/**
 * Created by maksymk on 4/6/2017.
 */
@Service
public class FactorsService {

    private static Logger logger = LoggerFactory.getLogger(FactorsService.class);

    private final IFactors iFactors;
    private final DtoConverter dtoConverter;
    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Instantiates a new Factors service.
     *
     * @param iFactors           the Factors
     * @param dtoConverter       the dto converter
     */
    public FactorsService(IFactors iFactors, DtoConverter dtoConverter) {
        this.iFactors = iFactors;
        this.dtoConverter = dtoConverter;
    }

    /**
     * Find all list.
     *
     * @return the list
     */
    public List<FactorsDTOGet> findAll() {
        return null;
    }

    /**
     * Find by id Factors dto.
     *
     * @param FactorsId the Factors id
     * @return the Factors dto
     */
    public FactorsDTOGet findById(String FactorsId) {
        return null;
    }


    /**
     * Create Factors dto.
     *
     * @param FactorsDTOPost the Factors dto
     * @return the Factors dto
     */
    public FactorsDTOGet create(FactorsDTOPost FactorsDTOPost) {
        return null;
    }

    /**
     * Update Factors dto.
     *
     * @param FactorsId      the Factors id
     * @param FactorsDTOPost the Factors dto
     * @return the Factors dto
     */
    public FactorsDTOGet update(String FactorsId, FactorsDTOPost FactorsDTOPost) {
        return null;
    }


    /**
     * Delete.
     *
     * @param FactorsId the Factors id
     */
    public void delete(String FactorsId) {
    }

    /**
     * Delete all.
     */
    public void deleteAll() {
        iFactors.deleteAll();
    }

    private static <T, U> List<U> convertList(List<T> from, Function<T, U> func) {
        return from.stream().map(func).collect(Collectors.toList());
    }
}
