package luisa.service;

import luisa.exception.*;
import luisa.model.*;
import luisa.dao.TrechoDAO;
import luisa.util.FabricaDeDaos;

import java.util.List;

public class TrechoService {

    private final TrechoDAO trechoDAO = FabricaDeDaos.getDAO(TrechoDAO.class);

    public Trecho incluir(Trecho trecho) {
        trechoDAO.incluir(trecho);
        trecho.getVoo().getTrechos().add(trecho);
        return trecho;
    }

    public Trecho remover(int id) {
        Trecho trecho = this.recuperarTrechoPorId(id);
        if (trecho == null) {
            throw new EntidadeNaoEncontradaException("Trecho inexistente, não é possível remover.");
        }
        if (trecho.getExecucoesTrechos().isEmpty()){
            trecho.getVoo().getTrechos().remove(trecho);
            trechoDAO.remover(trecho.getId());
        }else {
            throw new ListaDoObjetoNaoVaziaException("Esse trecho já possui execuções de trechos, então não pode ser excluído.");
        }
        return trecho;
    }

    public Trecho recuperarTrechoPorId(int id) {
        Trecho trecho = trechoDAO.recuperarPorId(id);
        if (trecho == null)
            throw new EntidadeNaoEncontradaException("Trecho inexistente.");
        return trecho;
    }

    public List<Trecho> recuperarTrechos() {
        return trechoDAO.recuperarTodos();
    }

}
