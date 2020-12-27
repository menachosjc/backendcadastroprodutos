package com.produtos.apirest.repository;

import com.produtos.apirest.entity.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GrupoRepository extends JpaRepository<Grupo,Long> {
    public Grupo findByIdGrupo(Long idGrupo);
    public Grupo findByNomeGrupo(String nomeGrupo);
}
