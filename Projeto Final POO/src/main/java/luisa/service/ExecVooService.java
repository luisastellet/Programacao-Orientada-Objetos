package luisa.service;

import luisa.dao.ExecVooDAO;
import luisa.exception.*;
import luisa.model.*;
import luisa.util.FabricaDeDaos;

import java.util.List;

public class ExecVooService {

    private final ExecVooDAO execVooDAO = FabricaDeDaos.getDAO(ExecVooDAO.class);


    public List<ExecVoo> recuperarExecucoesDeVoo() {
        return execVooDAO.recuperarTodos();
    }

    public ExecVoo remover(int id){
        ExecVoo execVoo = this.recuperarExecucaoDeVooPorId(id);
        if(execVoo == null){
            throw new EntidadeNaoEncontradaException("\"Execução de voo inexistente, não é possível remover.");
        }
        if(execVoo.getExecucoesTrechos().isEmpty()) {
            execVoo.getVoo().getExecucoesVoos().remove(execVoo);
            execVooDAO.remover(execVoo.getId());
            return execVoo;
        }
        else {
            throw new ListaDoObjetoNaoVaziaException("Essa Execução de Voo já possui Execução de Trechos, então não pode ser excluída.");
        }
    }

    public ExecVoo incluir(ExecVoo execVoo) {
        execVoo.getVoo().getExecucoesVoos().add(execVoo);
        execVooDAO.incluir(execVoo);
        return execVoo;
    }

    public ExecVoo recuperarExecucaoDeVooPorId(int id) {
        ExecVoo execVoo = execVooDAO.recuperarPorId(id);
        if (execVoo == null)
            throw new EntidadeNaoEncontradaException("Execução de voo inexistente.");
        return execVoo;
    }

}
