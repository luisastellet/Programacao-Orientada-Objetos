package luisa.service;

import luisa.dao.ExecTrechoDAO;
import luisa.exception.*;
import luisa.model.*;
import luisa.util.FabricaDeDaos;

import java.util.List;

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
        boolean ver = false;
        if (execTrecho.getTrecho().getVoo().equals(execTrecho.getExecVoo().getVoo())) {
            if(execTrecho.getExecVoo().getExecucoesTrechos().isEmpty()){
                if(execTrecho.getExecVoo().getDataHoraInicial().equals(execTrecho.getDataHoraInicial())) ver = true;
            }
            if(!execTrecho.getExecVoo().getExecucoesTrechos().isEmpty()){
                if(execTrecho.checagemExecucoes(execTrecho.getExecVoo().getExecucoesTrechos().getLast().getDataHoraFinal(), execTrecho.getDataHoraInicial())) ver = true;
            }
            if(ver){
                execTrechoDAO.incluir(execTrecho);
                execTrecho.getTrecho().getExecucoesTrechos().add(execTrecho);
            }
            else throw new DataHoraInvalidaException ("Datas e horas não seguem um padrão lógico.");
            
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
