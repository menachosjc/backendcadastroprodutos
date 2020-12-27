package com.produtos.apirest.entity;
import com.fasterxml.jackson.annotation.JsonView;
import com.produtos.apirest.jsonFormat.View;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id")
    @JsonView(View.Usuario.class)
    private Long idUsuario;

    @JsonView(View.Usuario.class)
    @Column(name = "usuario_nome")
    private String nomeUsuario;

    @JsonView(View.Usuario.class)
    @Column(name = "usuario_username")
    private String usernameUsuario;

    @Column(name = "usuario_senha")
    private String senhaUsuario;

    @JsonView(View.Usuario.class)
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "usuarios")
    private Set<Produto> produtos;

    @JsonView(View.Usuario.class)
    @ManyToOne
    @JoinColumn(name = "grupo_id")
    private Grupo grupos;

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getUsernameUsuario() {
        return usernameUsuario;
    }

    public void setUsernameUsuario(String usernameUsuario) {
        this.usernameUsuario = usernameUsuario;
    }

    public String getSenhaUsuario() {
        return senhaUsuario;
    }

    public void setSenhaUsuario(String senhaUsuario) {
        this.senhaUsuario = senhaUsuario;
    }

    public Set<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(Set<Produto> produtos) {
        this.produtos = produtos;
    }

    public Grupo getGrupo() {
        return grupos;
    }

    public void setGrupo(Grupo grupos) {
        this.grupos = grupos;
    }
}
