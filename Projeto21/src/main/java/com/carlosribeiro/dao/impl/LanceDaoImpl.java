package com.carlosribeiro.dao.impl;

import com.carlosribeiro.dao.LanceDAO;
import com.carlosribeiro.model.Lance;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LanceDaoImpl extends DAOGenericoImpl<Lance> implements LanceDAO {

    public List<Lance> recuperarTodosOsLancesDeUmProduto(int id) {
        return map.values()
                          .stream()
                          .filter((lance) -> lance.getProduto().getId() == id)
                          .toList();
    }
}
