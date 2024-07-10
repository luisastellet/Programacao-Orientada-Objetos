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
        }
        return execVoo;
    }

    public ExecVoo incluir(ExecVoo execVoo) {
        List<Trecho> trechos = execVoo.getVoo().getTrechos();
        boolean verOrigem = false, verDestino = false;

        for (Trecho trecho : trechos) {
            if(trecho.getOrigem().equals(execVoo.getVoo().getOrigem())) verOrigem = true;
            if(trecho.getDestino().equals(execVoo.getVoo().getDestino())) verDestino = true;
            if(verOrigem && verDestino) break;
        }
        if(verOrigem && verDestino){
            execVooDAO.incluir(execVoo);
            execVoo.getVoo().getExecucoesVoos().add(execVoo);
        }
        else throw new TrechosNaoCompletosException("Os trechos desse voo não estão completos, portanto não é possivel comprar uma execução de voo.");
        return execVoo;

    }


    public ExecVoo recuperarExecucaoDeVooPorId(int id) {
        ExecVoo execVoo = execVooDAO.recuperarPorId(id);
        if (execVoo == null)
            throw new EntidadeNaoEncontradaException("Execução de voo inexistente.");
        return execVoo;
    }

}
