package com.carlosribeiro.dao;

import com.carlosribeiro.model.Lance;
import com.carlosribeiro.model.Produto;

import java.util.List;

public interface VooDAO extends DAOGenerico<Lance> {
    List<Lance> recuperarTodosOsLancesDeUmProduto(int id);
}
