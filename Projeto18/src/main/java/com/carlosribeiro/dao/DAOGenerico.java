package com.carlosribeiro.dao;

import java.util.List;

public interface DAOGenerico<V> {
    V incluir(V obj);
    V alterar(V obj);
    V remover(Integer id);
    V recuperarPorId(Integer id);
    List<V> recuperarTodos();
}
