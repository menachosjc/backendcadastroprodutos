package com.produtos.apirest.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.produtos.apirest.entity.Grupo;
import com.produtos.apirest.jsonFormat.View;
import com.produtos.apirest.service.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/grupo")
public class GrupoController {

    @Autowired
    private GrupoService grupoService;

    @PostMapping
    public ResponseEntity<Grupo> cadastrarGrupo(@RequestBody Grupo grupo, UriComponentsBuilder uriComponentsBuilder){
        grupo = grupoService.criarGrupo(grupo.getNomeGrupo());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(uriComponentsBuilder.path("/grupo/" + grupo.getIdGrupo()).build().toUri());
        return new ResponseEntity<Grupo>(grupo, responseHeaders, HttpStatus.CREATED);
    }

    @JsonView(View.Grupo.class)
    @GetMapping
    public List<Grupo> listarGrupos(){
        return grupoService.buscarGrupos();
    }
}
