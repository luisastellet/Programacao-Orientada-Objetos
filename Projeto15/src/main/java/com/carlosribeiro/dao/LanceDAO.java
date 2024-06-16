package com.carlosribeiro.dao;

import com.carlosribeiro.model.Lance;
import com.carlosribeiro.model.Produto;

import java.util.List;

public interface LanceDAO {
    Lance incluir(Lance lance);
    Lance alterar(Lance lance);
    Lance remover(Integer id);
    Lance recuperarPorId(Integer id);
    List<Lance> recuperarTodos();
    List<Lance> recuperarTodosOsLancesDeUmProduto(int id);
}
