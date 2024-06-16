package com.carlosribeiro.dao;

import com.carlosribeiro.model.Lance;
import com.carlosribeiro.model.Produto;

import java.util.List;

public interface ProdutoDAO {
    Produto incluir(Produto produto);
    Produto alterar(Produto produto);
    Produto remover(Integer id);
    Produto recuperarPorId(Integer id);
    List<Produto> recuperarTodos();
    List<Produto> recuperarProdutosOrdenadosPorNome();
}
