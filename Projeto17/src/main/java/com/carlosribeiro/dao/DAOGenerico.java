package com.carlosribeiro.dao;

import java.util.List;

public interface DAOGenerico<K, V> {
    V incluir(K id, V obj);
    V alterar(K id, V obj);
    V remover(K id);
    V recuperarPorId(K id);
    List<V> recuperarTodos();
}
