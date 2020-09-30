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
import com.seniortest.api.models.Cep;
import com.seniortest.api.models.Cidade;
import com.seniortest.api.repositories.CepRepository;
import com.seniortest.api.repositories.CidadeRepository;
import com.seniortest.api.response.PaginationResponse;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.io.IOException;

@Service
public class CidadeService {

  @Autowired
  private CidadeRepository cidadeRepo;

  @Autowired
  private CepRepository cepRep;

  @Autowired
  private ViaCepService viaCepService;


  public Cidade save(Cidade cidade){
    return cidadeRepo.save(cidade);
  }

  public PaginationResponse<CidadeSimpleDTO> getAllPaged(int page) {
    PaginationResponse<CidadeSimpleDTO> response = new PaginationResponse<CidadeSimpleDTO>();
    int size = 20;
    int offset = page - 1;
    PageRequest pageRequest = PageRequest.of(offset, size, Sort.Direction.ASC, "nome");
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
      cidade = cidadeRepo.save(new Cidade(Long.parseLong(viaCEP.ibge), viaCEP.localidade));
      
      newCep.setNumero(cepNumber);
      newCep.setCidade(cidade);

      cepRep.save(newCep);
    }

    cidadeDTO.setCodigoIBGE(cidade.getCodigoIBGE());
    cidadeDTO.setNome(cidade.getNome());
    cidadeDTO.setCeps(this.convertToCepList(cidade.getCeps(), newCep.getNumero()));

    
    return cidadeDTO;
  }


  public List<CidadeSimpleDTO> convertToCidadesSimpleDTO(List<Cidade> cidades) {
    List<CidadeSimpleDTO> list = new ArrayList<>();
    cidades.forEach(c -> list.add(new CidadeSimpleDTO(c.getCodigoIBGE(), c.getNome())));
    return list;
  }

  public Set<String> convertToCepList(Set<Cep> ceps, String newCep) {
    Set<String> list = new HashSet<>();

    list.add(newCep);

    if (isNull(ceps)) {
      return list;
    }

    ceps.forEach(cep -> list.add(cep.getNumero()));
    return list;
  }
}
