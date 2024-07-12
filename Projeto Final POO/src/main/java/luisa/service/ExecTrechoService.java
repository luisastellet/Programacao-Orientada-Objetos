package luisa.service;

import luisa.dao.ExecTrechoDAO;
import luisa.dao.PassagemDAO;
import luisa.exception.*;
import luisa.model.*;
import luisa.util.FabricaDeDaos;

import java.util.List;

public class ExecTrechoService {

    private final ExecTrechoDAO execTrechoDAO = FabricaDeDaos.getDAO(ExecTrechoDAO.class);
    private final PassagemDAO passagemDAO = FabricaDeDaos.getDAO(PassagemDAO.class);


    public List<ExecTrecho> recuperarExecucoesDeTrecho() {
        return execTrechoDAO.recuperarTodos();
    }

    public ExecTrecho remover(int id) {
        ExecTrecho execTrecho = recuperarExecucaoDeTrechoPorId(id);
        if (execTrecho == null) {
            throw new EntidadeNaoEncontradaException("Execução de trecho inexistente, não é possível remover.");
        }
        List<Passagem> passagens = execTrecho.getPassagens();
        for (Passagem passagem : passagens) {
            passagem.getCliente().getPassagens().remove(passagem);
            passagem.getExecucoesTrechos().remove(execTrecho);
            passagemDAO.remover(passagem.getId());
        }

        execTrecho.getExecVoo().getExecucoesTrechos().remove(execTrecho);
        execTrecho.getTrecho().getExecucoesTrechos().remove(execTrecho);
        execTrechoDAO.remover(id);

        return execTrecho;
    }

    public ExecTrecho incluir (ExecTrecho execTrecho){
        if (!execTrecho.getTrecho().getVoo().equals(execTrecho.getExecVoo().getVoo())) {
            throw new VoosNaoRelacionadosException("Não é possível cadastrar essa execução de trecho, pois a execução de voo e o trecho não são do mesmo voo");

        }
        execTrechoDAO.incluir(execTrecho);
        execTrecho.getExecVoo().getExecucoesTrechos().add(execTrecho);
        execTrecho.getTrecho().getExecucoesTrechos().add(execTrecho);
        return execTrecho;

    }

    public ExecTrecho recuperarExecucaoDeTrechoPorId(int id) {
        ExecTrecho execTrecho = execTrechoDAO.recuperarPorId(id);
        if (execTrecho == null)
            throw new EntidadeNaoEncontradaException("Execução de trecho inexistente.");
        return execTrecho;
    }


}
