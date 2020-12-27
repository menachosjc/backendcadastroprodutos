package com.produtos.apirest.service;
import com.produtos.apirest.entity.Grupo;
import com.produtos.apirest.entity.Usuario;
import com.produtos.apirest.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

@Service
public class SegurancaServiceImpl implements SegurancaService {

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepo.findByUsernameUsuario(username);
        if(usuario == null){
            throw new UsernameNotFoundException("Usuario " + username + "nao foi encontrado");
        }
        return User.builder()
                .username(usuario.getNomeUsuario())
                .password(usuario.getSenhaUsuario())
                .authorities(usuario.getGrupo().getNomeGrupo()).build();
    }
}
