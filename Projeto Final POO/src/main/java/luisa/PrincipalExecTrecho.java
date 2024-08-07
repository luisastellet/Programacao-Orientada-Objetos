package luisa;

import corejava.Console;
import luisa.exception.*;
import luisa.model.*;
import luisa.service.*;

import java.util.List;

public class PrincipalExecTrecho {

    private final ExecTrechoService execTrechoService = new ExecTrechoService();
    private final TrechoService trechoService = new TrechoService();
    private final ExecVooService execVooService = new ExecVooService();
    
    public void principal() {

        String dataHoraInicial;
        String dataHoraFinal;
        ExecTrecho umaExecTrecho;
        Trecho umTrecho;
        ExecVoo umaExecVoo;

        boolean continua = true;
        while (continua) {
            System.out.println('\n' + "========================================================");
            System.out.println('\n' + "O que você deseja fazer?");
            System.out.println('\n' + "1. Cadastrar Execução de Trecho");
            System.out.println("2. Remover Execução de Trecho"); //se não tem passagem pra ocorrer ainda
            System.out.println("3. Listar todas as Execuções de Trecho");
            System.out.println("4. Listar todas as Execuções de Trecho de um Trecho");
            System.out.println("5. Voltar");

            int opcao = Console.readInt('\n' + "Digite um número entre 1 e 5:");

            System.out.println();

            switch (opcao) {
                case 1 -> {
                    //dataHoraInicial = Console.readLine("Informe a data e hora inicial da execução do trecho (DD/MM/AAAA HH:MM:SS): ");
                    int idExecVoo = Console.readInt("De qual execução voo é? ");
                    int idTrecho = Console.readInt("De qual trecho é? ");
                    try {
                        umaExecVoo = execVooService.recuperarExecucaoDeVooPorId(idExecVoo);
                        umTrecho = trechoService.recuperarTrechoPorId(idTrecho);
                    } catch (EntidadeNaoEncontradaException | DataHoraInvalidaException | VoosNaoRelacionadosException e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                    if(umaExecVoo.getExecucoesTrechos().isEmpty()){
                        dataHoraInicial = umaExecVoo.getDataHoraInicial();
                    }
                    else{ //as outras exec trecho
                        dataHoraInicial = Console.readLine("Informe a data e hora inicial da execução do trecho (DD/MM/AAAA HH:MM:SS): ");
                    }

                    dataHoraFinal = Console.readLine("Informe a data e hora final da execução do trecho (DD/MM/AAAA HH:MM:SS): ");
                    try {

                        umaExecTrecho = new ExecTrecho(dataHoraInicial, dataHoraFinal, umaExecVoo, umTrecho);
                        execTrechoService.incluir(umaExecTrecho);
                        System.out.println("\nExecução de trecho de número " + umaExecTrecho.getId() + " cadastrada com sucesso!");
                    }
                    catch (DataHoraInvalidaException | VoosNaoRelacionadosException e){
                        System.out.println(e.getMessage());
                    }

                }
                case 2 -> {
                    int id = Console.readInt(
                            "Informe o número da execução de trecho que você deseja remover: ");

                    try {
                        execTrechoService.remover(id);
                        System.out.println('\n' + "Execução de trecho removida com sucesso!");
                    } catch (EntidadeNaoEncontradaException | JaAconteceuException e) {
                        System.out.println('\n' + e.getMessage());
                    }
                }
                case 3 -> {    // Listar
                    List<ExecTrecho> execTrechos = execTrechoService.recuperarExecucoesDeTrecho();
                    if(execTrechos != null){
                        for (ExecTrecho execucao : execTrechos) {
                            System.out.println(execucao);
                        }
                    }
                }
                case 4 ->{
                    int id = Console.readInt("Informe o id do Trecho: ");
                    try{
                        umTrecho = trechoService.recuperarTrechoPorId(id);
                        List<ExecTrecho> execTrechos = umTrecho.getExecucoesTrechos();
                        for ( ExecTrecho execucao : execTrechos ){
                            System.out.println(execTrechos);
                        }
                    }
                    catch ( EntidadeNaoEncontradaException e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                }
                case 5 -> continua = false;

                default -> System.out.println('\n' + "Opção inválida!");
            }
        }
    }
}

