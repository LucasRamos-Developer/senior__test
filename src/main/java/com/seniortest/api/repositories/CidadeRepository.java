package com.seniortest.api.repositories;

import java.util.List;

import com.seniortest.api.interfaces.ICidadeWithCepDTO;
import com.seniortest.api.models.Cidade;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {

  @Query(value="SELECT * FROM cidade LEFT JOIN cep ON cidade.codigo_ibge=cep.cidade_codigo WHERE cep.numero = :cep", nativeQuery=true)
  public Cidade getCidadeByCEP(@Param("cep") String cep);

  @Query(value="SELECT cidade.codigo_ibge as codigoIbge, cidade.nome as nome, cep.numero as cep FROM cidade LEFT JOIN cep ON cidade.codigo_ibge=cep.cidade_codigo WHERE cep.numero IN :ceps", nativeQuery=true)
  public List<ICidadeWithCepDTO> getAllByCeps(@Param("ceps") List<String> ceps);
}