package com.intapp.platform.factorial.persistence.springdata;

import com.google.common.collect.Lists;
import com.intapp.platform.factorial.persistence.IFactors;
import com.intapp.platform.factorial.persistence.domain.Factors;
import com.intapp.platform.factorial.persistence.repository.FactorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

/**
 * The type Factors persistence service.
 */
@Repository
@Transactional
public class FactorsImpl implements IFactors {

    @Value("${app.current.user}")
    private String currentUser;

    @Autowired
    private FactorsRepository factorsRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Factors> findAll() {
        return Lists.newArrayList(factorsRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Factors findById(Long id) {
        return factorsRepository.findOne(id);
    }

    @Override
    public Factors save(Factors Factors) {
        return factorsRepository.save(Factors);
    }

    @Override
    public void delete(Long id) {
        factorsRepository.delete(id);
    }

    @Override
    public void deleteAll() {
        factorsRepository.deleteAll();
    }

}
