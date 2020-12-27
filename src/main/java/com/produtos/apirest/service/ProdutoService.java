package com.produtos.apirest.service;


import com.produtos.apirest.entity.Produto;
import com.produtos.apirest.entity.Usuario;
import com.produtos.apirest.model.ProdutoForm;

import java.util.List;

public interface ProdutoService {
    public Produto criarProduto(ProdutoForm novoProduto);
    public List<Produto> getProdutos(Long id);
    public boolean removerProduto(Long idProduto);
    public Produto buscarPorId(Long id);
}
