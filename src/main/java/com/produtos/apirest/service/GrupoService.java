package com.produtos.apirest.service;

import com.produtos.apirest.entity.Grupo;


import java.util.List;

public interface GrupoService {
    public Grupo criarGrupo(String nomeGrupo);
    public List<Grupo> buscarGrupos();

}
