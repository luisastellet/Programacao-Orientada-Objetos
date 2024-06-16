package com.carlosribeiro.service;

import com.carlosribeiro.dao.ProdutoDAO;
import com.carlosribeiro.dao.impl.ProdutoDAOImpl;
import com.carlosribeiro.model.Produto;

import java.util.List;

public class ProdutoService {

    private ProdutoDAO produtoDAO = new ProdutoDAOImpl();

    public Produto incluir(Produto produto) {
        return produtoDAO.incluir(produto);
    }

    public Produto remover(int numero) {
        return produtoDAO.remover(numero);
    }

    public Produto recuperarProdutoPorNumero(int numero) {
        return produtoDAO.recuperarProdutoPorNumero(numero);
    }

    public List<Produto> recuperarProdutos() {
        return produtoDAO.recuperarProdutos();
    }

    public List<Produto> recuperarProdutosOrdenadosAscendentementePorNome() {
        return produtoDAO.recuperarProdutosOrdenadosAscendentementePorNome();
    }

    public List<Produto> recuperarProdutosOrdenadosDescendentementePorNome() {
        return produtoDAO.recuperarProdutosOrdenadosDescendentementePorNome();
    }

    public boolean listaVazia() {
        return produtoDAO.listaVazia();
    }
}
