package com.seniortest.api.controllers;

import java.util.List;

import javax.management.relation.RelationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.ResponseEntity;

import com.seniortest.api.dtos.CidadeDTO;
import com.seniortest.api.dtos.CidadeSimpleDTO;
import com.seniortest.api.models.Cidade;
import com.seniortest.api.response.DefaultResponse;
import com.seniortest.api.response.FilterResponse;
import com.seniortest.api.response.PaginationResponse;
import com.seniortest.api.response.TrackingResponse;
import com.seniortest.api.services.CidadeService;

@RestController
@RequestMapping(value = "/cidades")
public class CidadeController {

  @Autowired
  private CidadeService cidadeService;

  @GetMapping
  @ResponseBody
  public ResponseEntity<PaginationResponse<CidadeSimpleDTO>> getPaged(@RequestParam(name = "page", defaultValue = "1") int page) {
    PaginationResponse<CidadeSimpleDTO> response = cidadeService.getAllPaged(page);
    return ResponseEntity.ok(response);
  }


  @PostMapping
  public ResponseEntity<DefaultResponse<CidadeDTO>> create(@RequestBody Cidade cidade){
    DefaultResponse<CidadeDTO> response = new DefaultResponse<>();
    try {
      CidadeDTO newCidade = cidadeService.save(cidade);
      response.setData(newCidade);
      response.setMessage("Uma nova cidade adicionada com sucesso");
      return ResponseEntity.ok(response);
    } catch (Exception e) {
      response.setMessage(e.getMessage());
      return ResponseEntity.badRequest().body(response);
    }
  }

  @GetMapping(value = { "/{id}" })
  public ResponseEntity<DefaultResponse<CidadeDTO>> getById(@PathVariable long id) {
    DefaultResponse<CidadeDTO> response = new DefaultResponse<>();
    try {
      response.setData(cidadeService.findById(id));
      response.setMessage("Registro Encontratdo");
      return ResponseEntity.ok(response);
    } catch (RelationException e) {
      response.setMessage(e.getMessage());
      return ResponseEntity.notFound().build() ;
    } catch (Exception e) {
      response.setMessage(e.getMessage());
      return ResponseEntity.badRequest().body(response);
    }
  }

  @PutMapping(value = { "/{id}" })
  public ResponseEntity<DefaultResponse<CidadeDTO>> update(@PathVariable Long id, @RequestBody Cidade cidade) {
    DefaultResponse<CidadeDTO> response = new DefaultResponse<>();
    try {
      response.setData(cidadeService.findById(id));
      response.setMessage("Registro Encontratdo");
      return ResponseEntity.ok(response);
    } catch (RelationException e) {
      response.setMessage(e.getMessage());
      return ResponseEntity.notFound().build() ;
    } catch (Exception e) {
      response.setMessage(e.getMessage());
      return ResponseEntity.badRequest().body(response);
    }
  }

  @GetMapping(value = "/search")
  @ResponseBody
  public ResponseEntity<DefaultResponse<CidadeDTO>> searchByCEP(@RequestParam(name = "cep") String cep){
    DefaultResponse<CidadeDTO> response = new DefaultResponse<>();
    try {
      response.setData(cidadeService.searchByCEP(cep)); 
      response.setMessage("Cidade encontrada pra o CEP: " + cep);
      return ResponseEntity.ok(response);
    } catch (Exception e) {
      response.setMessage(e.getMessage());
      return ResponseEntity.badRequest().body(response);
    }
  }

  @GetMapping(value = "/filter")
  @ResponseBody
  public ResponseEntity<FilterResponse<CidadeSimpleDTO>> filterByUF(@RequestParam(name = "uf") String uf){
    FilterResponse<CidadeSimpleDTO> response = new FilterResponse<>();
    response.setFilter(uf);
    try {
      response.setData(cidadeService.getFilterByUF(uf));
      response.setMessage("Filtro realizado com sucesso: "+uf);
      return ResponseEntity.ok(response);
    } catch (Exception e) {
      response.setMessage(e.getMessage());
      return ResponseEntity.badRequest().body(response);
    }
  }

  @GetMapping(value = "/tracking-route")
  @ResponseBody
  public ResponseEntity<TrackingResponse<CidadeSimpleDTO>> searchByCEP(@RequestParam(name = "ceps") List<String> ceps){
    TrackingResponse<CidadeSimpleDTO> response = new TrackingResponse<>();
    List<CidadeSimpleDTO> cidades = cidadeService.getRouteByCeps(ceps); 

    response.setData(cidades);
    response.setTracking(ceps);

    return ResponseEntity.ok(response);
  }

}
