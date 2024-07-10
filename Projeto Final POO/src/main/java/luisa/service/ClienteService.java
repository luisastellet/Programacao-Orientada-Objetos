package luisa.service;

import luisa.dao.ClienteDAO;
import luisa.exception.*;
import luisa.model.*;
import luisa.util.FabricaDeDaos;

import java.util.List;

public class ClienteService {
    
    private final ClienteDAO clienteDAO = FabricaDeDaos.getDAO(ClienteDAO.class);

    public Cliente incluir(Cliente cliente) {
        return clienteDAO.incluir(cliente);
    }

    public void alterarNome(Cliente cliente, String novoNome) {
        cliente.setNome(novoNome);
    }

    public void alterarCpf(Cliente cliente, String novoCpf) {
        cliente.setCpf(novoCpf);
    }

    public Cliente recuperarClientePorId(int id) {
        Cliente cliente = clienteDAO.recuperarPorId(id);
        if (cliente == null)
            throw new EntidadeNaoEncontradaException("Cliente inexistente.");
        return cliente;
    }

    public Cliente remover(int id) {
        Cliente cliente = recuperarClientePorId(id);
        if (cliente == null) {
            throw new EntidadeNaoEncontradaException("Cliente inexistente, não é possível remover.");
        }
        if (cliente.getPassagens().isEmpty()){
            clienteDAO.remover(cliente.getId());
        }else {
            throw new ListaDoObjetoNaoVaziaException("Esse cliente já possui passagens, então não pode ser excluído.");
        }
        return cliente;
    }

    public List<Cliente> recuperarClientes() {
        return clienteDAO.recuperarTodos();
    }

    public int calcularMilhas(Cliente cliente){
        int resp = 0;
        List<Passagem> passagens = cliente.getPassagens();
        for(Passagem passagem : passagens){
            List<ExecTrecho> execTrechos = passagem.getExecucoesTrechos();
            for(ExecTrecho execTrecho : execTrechos){
                if(execTrecho.jaAconteceu()) resp += execTrecho.getTrecho().getMilhas();
            }
        }
        return resp;
    }


}
