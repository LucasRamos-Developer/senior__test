package com.seniortest.api.repositories;

import com.seniortest.api.models.Cidade;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {

  @Query(value="SELECT * FROM cidade LEFT JOIN cep ON cidade.codigo_ibge=cep.cidade_codigo WHERE cep.numero = :cep", nativeQuery=true)
  public Cidade getCidadeByCEP(@Param("cep") String cep);
}