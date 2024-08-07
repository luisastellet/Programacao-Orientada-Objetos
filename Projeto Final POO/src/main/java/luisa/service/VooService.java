package luisa.service;
import luisa.dao.VooDAO;
import luisa.exception.*;
import luisa.model.*;
import luisa.util.FabricaDeDaos;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class VooService {

    private final VooDAO vooDAO = FabricaDeDaos.getDAO(VooDAO.class);

    public Voo incluir(Voo voo) {
        List<Voo> voos = recuperarVoos();
        for (Voo v : voos) {
            if(voo.getOrigem().equals(v.getOrigem()) && voo.getDestino().equals(v.getDestino())) {
                throw new ObjetoDuplicadoException('\n' + "Este voo já foi cadastrado!\n" + v.toString());
            }
        }
        return vooDAO.incluir(voo);
    }

    public Voo remover(int id) {
        Voo umVoo = recuperarVooPorId(id);
        if (umVoo == null) {
            throw new EntidadeNaoEncontradaException("Voo inexistente, não é possível remover.");
        }
        if (umVoo.getExecucoesVoos().isEmpty() && umVoo.getTrechos().isEmpty()) {
            vooDAO.remover(umVoo.getId());
        } else {
            throw new ListaDoObjetoNaoVaziaException("Esse voo já possui execuções de voos ou trechos, então não pode ser excluído.");
        }
        return umVoo;
    }

    public Voo recuperarVooPorId(int id) {
        Voo voo = vooDAO.recuperarPorId(id);
        if (voo == null)
            throw new EntidadeNaoEncontradaException("Voo inexistente.");
        return voo;
    }

    public List<Voo> recuperarVoos() {
        return vooDAO.recuperarTodos();
    }

    public void calcularPassageiros(Voo umVoo, String referencia) {
        List<Trecho> trechos = umVoo.getTrechos();
        int resp = 0;
        for (Trecho trecho : trechos) {
            List<ExecTrecho> execTrechos = trecho.getExecucoesTrechos();
            for (ExecTrecho execTrecho : execTrechos) {
                resp = 0;
                if(execTrecho.posData(referencia, execTrecho.getDataHoraInicial())){
                    List<Passagem> passagens = execTrecho.getPassagens();
                    for (Passagem passagem : passagens) resp++;
                    System.out.println("A Execução de Trecho " + execTrecho.getId() + " teve " + resp + " passageiros.");
                }
            }
        }
    }
}
