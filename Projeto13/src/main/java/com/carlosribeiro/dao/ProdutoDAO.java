package com.carlosribeiro.dao;

import com.carlosribeiro.model.Produto;

import java.util.List;

public interface ProdutoDAO {
    Produto incluir(Produto produto);
    Produto remover(int numero);
    Produto recuperarProdutoPorNumero(int numero);
    List<Produto> recuperarProdutos();
    List<Produto> recuperarProdutosOrdenadosAscendentementePorNome();
    List<Produto> recuperarProdutosOrdenadosDescendentementePorNome();
    boolean listaVazia();
}
