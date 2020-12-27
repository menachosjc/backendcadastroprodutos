package com.produtos.apirest.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.produtos.apirest.jsonFormat.View;
import com.produtos.apirest.entity.Usuario;
import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "grupo")
public class Grupo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "grupo_id")
    private Long idGrupo;


    @JsonView({View.Usuario.class,View.Grupo.class})
    @Column(name = "grupo_nome")
    private String nomeGrupo;


    public Long getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Long idGrupo) {
        this.idGrupo = idGrupo;
    }

    public String getNomeGrupo() {
        return nomeGrupo;
    }

    public void setNomeGrupo(String nomeGrupo) {
        this.nomeGrupo = nomeGrupo;
    }
}
