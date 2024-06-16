package com.carlosribeiro.dao;

import com.carlosribeiro.model.Lance;
import com.carlosribeiro.model.Produto;

import java.util.List;

public interface TrechoDAO extends DAOGenerico <Produto>{
    List<Produto> recuperarProdutosOrdenadosPorNome();
}
