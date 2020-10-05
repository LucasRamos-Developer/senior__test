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
import com.seniortest.api.repositories.CidadeRepository;
import com.seniortest.api.response.PaginationResponse;

import java.util.ArrayList;
import java.util.HashMap;
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
  private ViaCepService viaCepService;

  public CidadeDTO create(CidadeDTO cidadeDTO) {
    Cidade novaCidade = new Cidade();
    return this.save(novaCidade, cidadeDTO);
  }

  public CidadeDTO findById(Long codigo) throws RelationNotFoundException {
    Optional<Cidade> cidade = cidadeRepo.findById(codigo);
    CidadeDTO cidadeDTO = new CidadeDTO();
    if (!cidade.isPresent()) {
      throw new RelationNotFoundException();
    }

    Cidade currentCidade = cidade.get();
    cidadeDTO.setCodigoIbge(currentCidade.getCodigoIbge());
    cidadeDTO.setNome(currentCidade.getNome());
    cidadeDTO.setUf(currentCidade.getUf());
    cidadeDTO.setCeps(this.convertToCepList(currentCidade.getCeps()));
    return cidadeDTO;
  }

  public CidadeDTO updateById (Long codigo, CidadeDTO cidadeDTO) throws RelationNotFoundException {
    Optional<Cidade> cidade = cidadeRepo.findById(codigo);
    if (!cidade.isPresent()) {
      throw new RelationNotFoundException();
    }

    Cidade currentCidade = cidade.get();
    if (isNull(cidadeDTO.getCodigoIbge())) {
      cidadeDTO.setCodigoIbge(codigo);
    }

    return this.save(currentCidade, cidadeDTO);
  }

  public String deleteById(Long codigo) throws RelationNotFoundException {
    Optional<Cidade> cidade = cidadeRepo.findById(codigo);
    if (!cidade.isPresent()) {
      throw new RelationNotFoundException();
    }

    cidadeRepo.deleteById(codigo);
    return "Registro deletado com sucesso";
  }

  public CidadeDTO save(Cidade cidade, CidadeDTO cidadeDTO) {
    cidade.setCodigoIbge(cidadeDTO.getCodigoIbge());
    cidade.setNome(cidadeDTO.getNome());
    cidade.setUf(cidadeDTO.getUf());
    if (!isNull(cidadeDTO.getCeps())) {
      cidadeDTO.getCeps().forEach(cep -> {
        Cep novoCep = new Cep();
        novoCep.setNumero(cep);
        novoCep.setCidade(cidade);

        cidade.addCep(novoCep);
      });
    }

    cidadeRepo.save(cidade);
    return cidadeDTO;
  }


  public PaginationResponse<CidadeSimpleDTO> getAllPaged(int page) {
    PaginationResponse<CidadeSimpleDTO> response = new PaginationResponse<>();
    int size = 20;
    int offset = page - 1;
    PageRequest pageRequest = PageRequest.of(offset                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               , size, Sort.Direction.ASC, "nome");
    Page<Cidade> cidades = cidadeRepo.findAll(pageRequest);
    
    response.setData(this.toListCidadeSimpleDTO(cidades.toList()));
    response.setPage(page);
    response.setSize(size);
    response.setTotalPages(cidades.getTotalPages());
    response.setTotalElements(cidades.getNumberOfElements());

    return response;
  }

  /**
   * BUsca uma cidade pelo numero do CEP, se não tiver salva no banco busca na
   * base via cep e cria uma nova cidade, se a cidade já existi somente adiciona o cep
   * 
   * @throws RelationNotFoundException
   */
  public CidadeDTO searchByCEP(String cepNumber) throws IOException, RelationNotFoundException {
    Cidade cidade = cidadeRepo.getCidadeByCEP(cepNumber);
    CidadeDTO cidadeDTO = new CidadeDTO();
    Cep novoCep = new Cep();
    
    if (isNull(cidade)) {
      cidade = this.addCidadeByCep(cepNumber);
      
      novoCep.setNumero(cepNumber);
      novoCep.setCidade(cidade);
  
      cidade.addCep(novoCep);
      cidade = cidadeRepo.save(cidade);
    }

    cidadeDTO.setCodigoIbge(cidade.getCodigoIbge());
    cidadeDTO.setNome(cidade.getNome());
    cidadeDTO.setUf(cidade.getUf());
    cidadeDTO.setCeps(this.convertToCepList(cidade.getCeps()));
    
    return cidadeDTO;
  }


  public Cidade addCidadeByCep(String cep) throws RelationNotFoundException, IOException {
    ViaCepDTO viaCEP = viaCepService.getInforByCEP(cep);
    if (isNull(viaCEP)) {
      throw new RelationNotFoundException();
    }
    return new Cidade(Long.parseLong(viaCEP.ibge), viaCEP.localidade, viaCEP.uf);
  }


  public CidadeDTO addCeps(Long codigo, List<String> ceps) throws RelationNotFoundException {
    Optional<Cidade> cidade = cidadeRepo.findById(codigo);
    if (!cidade.isPresent()) {
      throw new RelationNotFoundException();
    }

    Cidade currentCidade = cidade.get();
    if (!isNull(ceps)) {
      ceps.forEach(c -> {
        Cep cep = new Cep(c, currentCidade);
        currentCidade.addCep(cep);
      });
    }

    cidadeRepo.save(currentCidade);

    CidadeDTO cidadeDTO = new CidadeDTO();
    
    cidadeDTO.setCodigoIbge(currentCidade.getCodigoIbge());
    cidadeDTO.setNome(currentCidade.getNome());
    cidadeDTO.setUf(currentCidade.getUf());
    cidadeDTO.setCeps(this.convertToCepList(currentCidade.getCeps()));

    return cidadeDTO;
  }


  /**
   * Retorna a lista de cidades baseada no CEP
   */
  public List<CidadeSimpleDTO> getRouteByCeps(List<String> ceps) {
    HashMap<String,CidadeSimpleDTO> cidades = this.toHashMapCidadeWithCepDTO(cidadeRepo.getAllByCeps(ceps));
    return this.getCidadesUniqueList(cidades, ceps);
  }

  /**
   * Remove as cidades duplicadas conforme a lista de CEPS
   */
  public List<CidadeSimpleDTO> getCidadesUniqueList(HashMap<String,CidadeSimpleDTO> cidades, List<String> ceps){
    List<CidadeSimpleDTO> response = new ArrayList<>();
    List<Long> codigos = new ArrayList<>();
    ceps.forEach(cep -> {
      CidadeSimpleDTO cidade = cidades.get(cep);
      if (codigos.indexOf(cidade.getCodigoIbge()) < 0) {
        codigos.add(cidade.getCodigoIbge());
        response.add(cidade); 
      }
    });
    return response;
  }


  public List<CidadeSimpleDTO> getFilterByUF(String uf) {
    List<Cidade> cidades = cidadeRepo.findByUf(uf);

    List<CidadeSimpleDTO> list = new ArrayList<>();
    cidades.forEach(c -> {
      list.add(new CidadeSimpleDTO(c.getCodigoIbge(), c.getNome(), c.getUf()));
    });

    return list;
  }

  /**
   * Convert uma lista de cidade para uma lista de cidade sem o CEP
   */
  public List<CidadeSimpleDTO> toListCidadeSimpleDTO(List<Cidade> cidades) {
    List<CidadeSimpleDTO> list = new ArrayList<>();
    cidades.forEach(c -> list.add(new CidadeSimpleDTO(c.getCodigoIbge(), c.getNome(), c.getUf())));
    return list;
  }

  /**
   * Pega o retorno da consulta e transforma num hash map com o cep como chave
  */
  public HashMap<String,CidadeSimpleDTO> toHashMapCidadeWithCepDTO(List<ICidadeWithCepDTO> cidades) {
  HashMap<String,CidadeSimpleDTO> list = new HashMap<>();
    cidades.forEach(c -> {
      list.put(c.getCep(), new CidadeSimpleDTO(c.getCodigoIbge(), c.getNome(), c.getUf()));
    });
    return list;
  }

  /**
   * Conveste uma Set<Cep> em um lista de string
   */
  public List<String> convertToCepList(Set<Cep> ceps) {
    List<String> list = new ArrayList<>();
    if (isNull(ceps)){
      return list;
    }

    ceps.forEach(cep -> list.add(cep.getNumero()));
    return list;
  }
}
