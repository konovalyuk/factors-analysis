package com.intapp.platform.factorial.persistence.repository;

import com.intapp.platform.factorial.persistence.domain.Factors;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FactorsRepository extends JpaRepository<Factors, Long> {

  }

