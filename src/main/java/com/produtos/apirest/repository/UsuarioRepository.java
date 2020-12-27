package com.produtos.apirest.repository;

import com.produtos.apirest.entity.Produto;
import com.produtos.apirest.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    public Usuario findByIdUsuario(Long idUsuario);
    public Usuario findByNomeUsuario(String nomeUsuario);
    public Usuario findByUsernameUsuario(String usernameUsuario);
    public Usuario deleteByIdUsuario(Long idUsuario);

    @Query("select u from Usuario u where u.nomeUsuario = ?1 and u.usernameUsuario = ?2")
    public Usuario buscaUsuarioPorNomeEUsername(String nome, String username);

}
