package luisa.service;

import luisa.dao.ExecVooDAO;
import luisa.dao.PassagemDAO;
import luisa.dao.TrechoDAO;
import luisa.exception.DataHoraInvalidaException;
import luisa.exception.EntidadeNaoEncontradaException;
import luisa.exception.ListaDoObjetoNaoVaziaException;
import luisa.exception.VoosNaoRelacionadosException;
import luisa.model.Cliente;
import luisa.model.ExecTrecho;
import luisa.dao.ExecTrechoDAO;
import luisa.model.Passagem;
import luisa.model.Trecho;
import luisa.util.FabricaDeDaos;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class ExecTrechoService {

    private final ExecTrechoDAO execTrechoDAO = FabricaDeDaos.getDAO(ExecTrechoDAO.class);

    public List<ExecTrecho> recuperarExecucoesDeTrecho() {
        return execTrechoDAO.recuperarTodos();
    }

    public ExecTrecho remover(int id) {
        ExecTrecho execTrecho = this.recuperarExecucaoDeTrechoPorId(id);
        if (execTrecho == null) {
            throw new EntidadeNaoEncontradaException("Execução de trecho inexistente, não é possível remover.");
        }
        execTrecho.getExecVoo().getExecucoesTrechos().remove(execTrecho);
        execTrecho.getTrecho().getExecucoesTrechos().remove(execTrecho);

        //removendo todas as passagens dessa exectrecho e tirando as passagens das listas dos clientes
        List<Passagem> passagens = execTrecho.getPassagens();
        for (Passagem passagem : passagens) {
            passagem.getCliente().getPassagens().remove(passagem);
            passagens.remove(passagem);
        }

        execTrechoDAO.remover(execTrecho.getId());

        return execTrecho;
    }

    public ExecTrecho incluir (ExecTrecho execTrecho){
        if (Objects.equals(execTrecho.getTrecho().getVoo(), execTrecho.getExecVoo().getVoo())) {
            execTrechoDAO.incluir(execTrecho);
            execTrecho.getTrecho().getExecucoesTrechos().add(execTrecho);
        } else
            throw new VoosNaoRelacionadosException("Não é possível cadastrar essa execução de trecho, pois a execução de voo e o trecho não são do mesmo voo");

        return execTrecho;
    }

    public ExecTrecho recuperarExecucaoDeTrechoPorId(int id) {
        ExecTrecho execTrecho = execTrechoDAO.recuperarPorId(id);
        if (execTrecho == null)
            throw new EntidadeNaoEncontradaException("Execução de trecho inexistente.");
        return execTrecho;
    }

}
