package com.produtos.apirest.controller;


import com.fasterxml.jackson.annotation.JsonView;
import com.produtos.apirest.jsonFormat.View;
import com.produtos.apirest.model.UsuarioForm;


import com.produtos.apirest.entity.Usuario;
import com.produtos.apirest.service.UsuarioService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping(value = "/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService  usuarioService;

    @JsonView(View.Usuario.class)
    @PostMapping("/novousuario")
    public ResponseEntity<Usuario> salvarUsuario(@RequestBody UsuarioForm novoUsuario, UriComponentsBuilder uriComponentsBuilder){
       Usuario usuario  = usuarioService.criarUsuario(
               novoUsuario.getNomeUsuario(),
               novoUsuario.getUsernameUsuario(),
               novoUsuario.getIdGrupo(),
               novoUsuario.getSenhaUsuario()
       );
       HttpHeaders responseHeaders = new HttpHeaders();
       responseHeaders.setLocation(
        uriComponentsBuilder.path("/usuario/" + usuario.getIdUsuario()).build().toUri()
       );
       return new ResponseEntity<Usuario>(usuario, responseHeaders, HttpStatus.CREATED);
    }

    @JsonView(View.Usuario.class)
    @GetMapping("/todos")
    public List<Usuario> listarTodosUsuarios(){
        return usuarioService.listarUsuarios();
    }

    @JsonView(View.Usuario.class)
    @GetMapping("/{idUsuario}")
    public Usuario buscarUsuarioPorId(@PathVariable("idUsuario") Long idUsuario){
        return usuarioService.buscarPorId(idUsuario);
    }


    @JsonView(View.Usuario.class)
    @GetMapping("nomeeusernamme/{nome}/{username}")
    public Usuario buscarUsuarioPorNomeEUsername(@PathVariable("nome") String nome,@PathVariable("username") String username){
        return usuarioService.buscaUsuarioPorNomeEUsername(nome,username);
    }


}
