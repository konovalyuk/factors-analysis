package com.intapp.platform.factorial.persistence;

import com.intapp.platform.factorial.persistence.domain.Factors;

import java.util.Collection;
import java.util.List;

/**
 * Created by aaltukhov on 2017-03-07.
 */
public interface IFactors {

    /**
     * Find all list.
     *
     * @return the list
     */
    List<Factors> findAll();

    /**
     * Find by id Factors.
     *
     * @param id the Factors id
     * @return the Factors
     */
    Factors findById(Long id);

    /**
     * Create Factors.
     *
     * @param Factors the Factors
     * @return the Factors
     */
    Factors save(Factors Factors);

    /**
     * Delete.
     *
     * @param id the Factors id
     */
    void delete(Long id);

    /**
     * Delete all.
     */
    void deleteAll();


}

