package com.produtos.apirest.service;

import com.produtos.apirest.entity.Produto;
import com.produtos.apirest.entity.Usuario;
import com.produtos.apirest.exception.RegistroNaoEncontradoException;
import com.produtos.apirest.model.ProdutoForm;
import com.produtos.apirest.repository.ProdutoRepository;
import com.produtos.apirest.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoServiceImpl implements ProdutoService{
    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    @PreAuthorize("isAuthenticated()")
    public Produto criarProduto(ProdutoForm novoProduto){
        Produto produto = new Produto();

        produto.setUsuarios(new HashSet<Usuario>());
        produto.setNomeProduto(novoProduto.getNomeProduto());
        produto.setQuantidadeProduto(novoProduto.getQuantidadeProduto());
        produto.setValorProduto(novoProduto.getValorProduto());


        for(Long id : novoProduto.getIdUsuarios()){
            Usuario usuario = usuarioRepository.findByIdUsuario(id);
            if(usuario != null){
                produto.getUsuarios().add(usuario);
            }
        }

        produtoRepository.save(produto);

        return produto;

    }

    @Override
    public List<Produto> getProdutos(Long id){

        return produtoRepository.getProdutosPorUsuario(id);
    }

    @Override
    public boolean removerProduto(Long idProduto) {
        produtoRepository.deleteById(idProduto);

        return true;
    }

    @Override
    public Produto buscarPorId(Long id) {
        Optional<Produto> produtoOp = produtoRepository.findById(id);
        if(produtoOp.isPresent()){
            return produtoOp.get();
        }
        throw new RegistroNaoEncontradoException("Nao foi encontrado o produto");
    }



    /*
    @Override
    public Produto removerProduto(Long idProduto){

        return produtoRepository.deleteById(idProduto);
    }

*/
}
