package luisa.service;

import luisa.dao.VooDAO;
import luisa.exception.DataHoraInvalidaException;
import luisa.exception.EntidadeNaoEncontradaException;
import luisa.model.ExecVoo;
import luisa.dao.ExecVooDAO;
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
        }
        return execVoo;
    }

    public ExecVoo incluir(ExecVoo execVoo) {
        execVooDAO.incluir(execVoo);
        execVoo.getVoo().getExecucoesVoos().add(execVoo);
        return execVoo;
    }

    public ExecVoo recuperarExecucaoDeVooPorId(int id) {
        ExecVoo execVoo = execVooDAO.recuperarPorId(id);
        if (execVoo == null)
            throw new EntidadeNaoEncontradaException("Execução de voo inexistente.");
        return execVoo;
    }

}
