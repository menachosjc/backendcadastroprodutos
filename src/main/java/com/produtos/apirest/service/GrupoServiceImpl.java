package com.produtos.apirest.service;


import com.produtos.apirest.entity.Grupo;
import com.produtos.apirest.repository.GrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GrupoServiceImpl implements GrupoService {
    @Autowired
    GrupoRepository grupoRepository;

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Grupo criarGrupo(String nomeGrupo){
        Grupo grupo = new Grupo();
        grupo.setNomeGrupo(nomeGrupo);

        if(grupo != null){
            grupoRepository.save(grupo);
        }

        return grupo;
    }

    @Override
    public List<Grupo> buscarGrupos(){
        return grupoRepository.findAll();
    }
}
