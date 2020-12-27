package com.produtos.apirest.service;

import com.produtos.apirest.entity.Grupo;
import com.produtos.apirest.entity.Usuario;
import com.produtos.apirest.exception.RegistroNaoEncontradoException;
import com.produtos.apirest.repository.GrupoRepository;
import com.produtos.apirest.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private GrupoRepository grupoRepo;

    @Autowired
    private PasswordEncoder passEncoder;


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional
    public Usuario criarUsuario(String nomeUsuario, String usernameUsuario, Long idGrupoUsuario, String senha){
        Usuario usuario = new Usuario();
        Grupo grupo = grupoRepo.findByIdGrupo(idGrupoUsuario);

        usuario.setNomeUsuario(nomeUsuario);
        usuario.setUsernameUsuario(usernameUsuario);
        usuario.setGrupo(grupo);
        usuario.setSenhaUsuario(passEncoder.encode(senha));

        usuarioRepo.save(usuario);


        return usuario;
    }


    @Override

    public Usuario buscarPorId(Long id){
        Optional<Usuario> usuarioOp = usuarioRepo.findById(id);
        if(usuarioOp.isPresent()){
            return usuarioOp.get();
        }
        throw new RegistroNaoEncontradoException("Nao foi encontrado o usuario");
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Usuario> listarUsuarios() {
        return usuarioRepo.findAll();
    }

    @Override
    public Usuario buscaUsuarioPorNomeEUsername(String nomeUsuario, String username){
        return usuarioRepo.buscaUsuarioPorNomeEUsername(nomeUsuario,username);
    }

    @Override
    public Usuario buscarPorUsername(String username) {
        return usuarioRepo.findByUsernameUsuario(username);
    }
}
