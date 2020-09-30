package com.seniortest.api.repositories;

import com.seniortest.api.models.Cep;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CepRepository extends JpaRepository<Cep, Long> {
  
}