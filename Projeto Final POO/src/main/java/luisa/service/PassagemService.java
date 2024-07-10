package luisa.service;

import luisa.dao.ExecTrechoDAO;
import luisa.dao.PassagemDAO;
import luisa.exception.*;
import luisa.model.*;
import luisa.util.FabricaDeDaos;

import java.util.List;

public class PassagemService {
    
    private final PassagemDAO passagemDAO = FabricaDeDaos.getDAO(PassagemDAO.class);
    private final ExecTrechoDAO execTrechoDAO = FabricaDeDaos.getDAO(ExecTrechoDAO.class);

    public List<Passagem> recuperarPassagens() {
        return passagemDAO.recuperarTodos();
    }

    public Passagem incluir(Passagem umaPassagem, int[] numbers) {
        for (int number : numbers) {
            ExecTrecho umaExecTrecho = execTrechoDAO.recuperarPorId(number);
            if (umaExecTrecho == null) {
                throw new EntidadeNaoEncontradaException("Execução de trecho inexiste, não é possivel cadastrar a passagem.");
            }
            umaExecTrecho.getPassagens().add(umaPassagem);
            umaPassagem.getExecucoesTrechos().add(umaExecTrecho);
        }
        umaPassagem.getCliente().getPassagens().add(umaPassagem);
        passagemDAO.incluir(umaPassagem);
        return umaPassagem;
    }

    public Passagem remover(int id){
        Passagem umaPassagem = this.recuperarPassagemPorId(id);
        if(umaPassagem == null){
            throw new EntidadeNaoEncontradaException("Passagem inexistente, não é possível remover.");
        }
        if(umaPassagem.getExecucoesTrechos().isEmpty()){
            umaPassagem.getCliente().getPassagens().remove(umaPassagem);
            passagemDAO.remover(umaPassagem.getId());
        }
        else{
            throw new ListaDoObjetoNaoVaziaException("Essa passagem já possui execuções de trecho, então não pode ser excluída.");
        }
        return umaPassagem;
    }


    public Passagem recuperarPassagemPorId(int numero) {
        Passagem passagem = passagemDAO.recuperarPorId(numero);
        if (passagem == null)
            throw new EntidadeNaoEncontradaException("Passagem inexistente.");
        return passagem;
    }

    public void calcularPreco(Passagem umaPassagem){
        double resp = 0.0;
        List<ExecTrecho> execTrechos = umaPassagem.getExecucoesTrechos();
        for (ExecTrecho execTrecho : execTrechos) {
            resp += execTrecho.getTrecho().getPreco();
        }
        umaPassagem.setPreco(resp);
    }
}
