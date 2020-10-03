package com.seniortest.api.services;

import static java.util.Objects.isNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.seniortest.api.dtos.CidadeDTO;
import com.seniortest.api.dtos.CidadeSimpleDTO;
import com.seniortest.api.dtos.ViaCepDTO;
import com.seniortest.api.interfaces.ICidadeWithCepDTO;
import com.seniortest.api.models.Cep;
import com.seniortest.api.models.Cidade;
import com.seniortest.api.repositories.CepRepository;
import com.seniortest.api.repositories.CidadeRepository;
import com.seniortest.api.response.PaginationResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.management.relation.RelationNotFoundException;

import java.io.IOException;

@Service
public class CidadeService {

  @Autowired
  private CidadeRepository cidadeRepo;

  @Autowired
  private CepRepository cepRep;

  @Autowired
  private ViaCepService viaCepService;

  public CidadeDTO save(Cidade cidade) {
    Cidade newCidade = cidadeRepo.save(cidade);
    CidadeDTO cidadeDTO = new CidadeDTO();
    Set<String> cepsList = this.convertToCepList(newCidade.getCeps(), null);

    cidadeDTO.setCeps(cepsList);
    cidadeDTO.setcodigoIbge(newCidade.getcodigoIbge());
    cidadeDTO.setNome(newCidade.getNome());
    cidadeDTO.setUf(newCidade.getUf());

    return cidadeDTO;
  }

  public CidadeDTO findById(Long codigo) throws RelationNotFoundException {
    Optional<Cidade> cidade = cidadeRepo.findById(codigo);
    CidadeDTO resp = new CidadeDTO();

    if (!cidade.isPresent()) {
      throw new RelationNotFoundException();
    }

    Cidade currentCidade = cidade.get();
    
    resp.setcodigoIbge(currentCidade.getcodigoIbge());
    resp.setNome(currentCidade.getNome());
    resp.setUf(currentCidade.getUf());

    return resp;
  }

  public CidadeDTO updateById (Long codigo, Cidade newCidade) throws RelationNotFoundException {
    Optional<Cidade> cidade = cidadeRepo.findById(codigo);

    if (!cidade.isPresent()) {
      throw new RelationNotFoundException();
    }

    Cidade currentCidade = cidade.get();
    
    currentCidade.setNome(newCidade.getNome());
    currentCidade.setcodigoIbge(newCidade.getcodigoIbge());
    currentCidade.setUf(newCidade.getUf());

    CidadeDTO cidadeDTO = new CidadeDTO();

    cidadeDTO.setcodigoIbge(currentCidade.getcodigoIbge());
    cidadeDTO.setNome(currentCidade.getNome());
    cidadeDTO.setUf(currentCidade.getUf());
    cidadeDTO.setCeps(this.convertToCepList(newCidade.getCeps(), null));

    return cidadeDTO;
  }

  public PaginationResponse<CidadeSimpleDTO> getAllPaged(int page) {
    PaginationResponse<CidadeSimpleDTO> response = new PaginationResponse<CidadeSimpleDTO>();
    int size = 20;
    int offset = page - 1;
    PageRequest pageRequest = PageRequest.of(offset                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               , size, Sort.Direction.ASC, "nome");
    Page<Cidade> cidades = cidadeRepo.findAll(pageRequest);
    
    response.setData(this.convertToCidadesSimpleDTO(cidades.getContent()));
    response.setPage(page);
    response.setSize(size);

    return response;
  }


  public CidadeDTO searchByCEP(String cepNumber) throws IOException {
    Cidade cidade = cidadeRepo.getCidadeByCEP(cepNumber);
    CidadeDTO cidadeDTO = new CidadeDTO();
    Cep newCep = new Cep();
    
    if (isNull(cidade)) {
      ViaCepDTO viaCEP = viaCepService.getInforByCEP(cepNumber);
      cidade = cidadeRepo.save(
        new Cidade(
          Long.parseLong(viaCEP.ibge), 
          viaCEP.localidade, 
          viaCEP.uf
        )
      );
      newCep.setNumero(cepNumber);
      newCep.setCidade(cidade);
      cepRep.save(newCep);
    }

    cidadeDTO.setcodigoIbge(cidade.getcodigoIbge());
    cidadeDTO.setNome(cidade.getNome());
    cidadeDTO.setUf(cidade.getUf());
    cidadeDTO.setCeps(this.convertToCepList(cidade.getCeps(), newCep.getNumero()));
    
    return cidadeDTO;
  }


  public List<CidadeSimpleDTO> getRouteByCeps(List<String> ceps) {
    HashMap<String,CidadeSimpleDTO> cidades = this.convertToCidadeWithCepDTO(cidadeRepo.getAllByCeps(ceps));
    return this.getCidadesUniqueList(cidades, ceps);
  }


  public List<CidadeSimpleDTO> getCidadesUniqueList(HashMap<String,CidadeSimpleDTO> cidades, List<String> ceps){
    List<CidadeSimpleDTO> response = new ArrayList<>();
    List<Long> codigos = new ArrayList<>();
    ceps.forEach(cep -> {
      CidadeSimpleDTO cidade = cidades.get(cep);
      if (codigos.indexOf(cidade.getcodigoIbge()) < 0) {
        codigos.add(cidade.getcodigoIbge());
        response.add(cidade); 
      }
    });
    return response;
  }

  public List<CidadeSimpleDTO> getFilterByUF(String uf) {
    List<ICidadeWithCepDTO> cidades = new ArrayList<>();
    List<CidadeSimpleDTO> list = new ArrayList<>();

    cidades.forEach(c -> {
      list.add(new CidadeSimpleDTO(c.getcodigoIbge(), c.getNome()));
    });

    return list;
  }


  public List<CidadeSimpleDTO> convertToCidadesSimpleDTO(List<Cidade> cidades) {
    List<CidadeSimpleDTO> list = new ArrayList<>();
    cidades.forEach(c -> list.add(new CidadeSimpleDTO(c.getcodigoIbge(), c.getNome())));
    return list;
  }

  public HashMap<String,CidadeSimpleDTO> convertToCidadeWithCepDTO(List<ICidadeWithCepDTO> cidades) {
  HashMap<String,CidadeSimpleDTO> list = new HashMap<>();
    cidades.forEach(c -> {
      list.put(c.getCep(), new CidadeSimpleDTO(c.getcodigoIbge(), c.getNome()));
    });
    return list;
  }

  public Set<String> convertToCepList(Set<Cep> ceps, String newCep) {
    Set<String> list = new HashSet<>();

    if (!isNull(newCep)) {
      list.add(newCep);
    }

    if (isNull(ceps)){
      return list;
    }

    ceps.forEach(cep -> list.add(cep.getNumero()));
    return list;
  }
}
