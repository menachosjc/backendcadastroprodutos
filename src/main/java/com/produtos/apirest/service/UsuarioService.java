package com.produtos.apirest.service;
import com.produtos.apirest.entity.Usuario;
import java.util.List;



public interface UsuarioService {

    public Usuario criarUsuario(String nomeUsuario, String usernameUsuario, Long idGrupoUsuario, String senha);
    public Usuario buscarPorId(Long id);
    public List<Usuario> listarUsuarios();
    public Usuario buscarPorUsername(String username);
    public Usuario buscaUsuarioPorNomeEUsername(String nomeUsuario, String username);

}
