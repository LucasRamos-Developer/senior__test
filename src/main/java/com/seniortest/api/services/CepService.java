package com.seniortest.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seniortest.api.models.Cep;
import com.seniortest.api.repositories.CepRepository;

@Service
public class CepService {

  @Autowired
  private CepRepository cepRep;

  public Cep save(Cep cep){
    return cepRep.save(cep);
  }

}
