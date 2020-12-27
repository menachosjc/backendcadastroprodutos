package com.produtos.apirest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.produtos.apirest.entity.Usuario;
import com.produtos.apirest.security.JwtUtils;
import com.produtos.apirest.model.LoginForm;
import com.produtos.apirest.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping(value = "/fazerlogin")
public class LoginController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private UsuarioService usuarioService;


    @PostMapping()
    public LoginForm autenticar(@RequestBody LoginForm login) throws JsonProcessingException {

        Authentication auth = new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword());
        Usuario user = usuarioService.buscarPorUsername(login.getUsername());
        auth = authManager.authenticate(auth);
        login.setPassword(null);
        login.setIdUsuario(user.getIdUsuario());
        login.setToken(JwtUtils.generateToken(auth));
        return login;
    }
}
