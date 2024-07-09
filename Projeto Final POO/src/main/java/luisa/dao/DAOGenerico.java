package luisa.dao;

import java.util.List;
import java.util.Map;

//As funções básicas necessárias para todas as classes
public interface DAOGenerico<V> {
    Map<Integer, V> getMap();
    void setMap(Map<Integer, V> map);
    Integer getContador();
    void setContador(Integer contador);
    V incluir(V obj);
    V alterar(V obj);
    V remover(Integer id);
    V recuperarPorId(Integer id);
    List<V> recuperarTodos();
}