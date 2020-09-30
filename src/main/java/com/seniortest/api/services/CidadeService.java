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
import java.util.List;
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
    PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.ASC, "nome");
    Page<Cidade> cidades = cidadeRepo.findAll(pageRequest);
    
    response.setData(this.convertToCidadesSimpleDTO(cidades.getContent()));
    response.setPage(page);
    response.setSize(size);

    return response;
  }


  public CidadeDTO searchByCEP(String cepNumber) throws IOException {
    Cidade cidade = cidadeRepo.getCidadeByCEP(cepNumber);
    CidadeDTO cidadeDTO = new CidadeDTO();
    
    if (isNull(cidade)) {
      ViaCepDTO viaCEP = viaCepService.getInforByCEP(cepNumber);
      cidade = cidadeRepo.save(new Cidade(viaCEP.ibge, viaCEP.localidade));

      cepRep.save(new Cep(cepNumber, cidade));
    }

    cidadeDTO.setCodigoIBGE(cidade.getCodigoIBGE());
    cidadeDTO.setNome(cidade.getNome());
    cidadeDTO.setCeps(this.convertToCepList(cidade));

    
    return cidadeDTO;
  }


  public List<CidadeSimpleDTO> convertToCidadesSimpleDTO(List<Cidade> cidades) {
    List<CidadeSimpleDTO> list = new ArrayList<>();
    cidades.forEach(c -> list.add(new CidadeSimpleDTO(c.getCodigoIBGE(), c.getNome())));
    return list;
  }

  public List<String> convertToCepList(Cidade cidade) {
    List<String> list = new ArrayList<>();
    cidade.getCeps().forEach(cep -> list.add(cep.getNumero()));
    return list;
  }
}
