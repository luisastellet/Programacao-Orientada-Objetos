package luisa;
import corejava.Console;

import luisa.exception.*;
import luisa.model.*;
import luisa.service.*;

import java.util.List;

public class PrincipalVoo {

    private final VooService vooService = new VooService();

    public void principal() {

        String origem;
        String destino;
        Voo umVoo;


        boolean continua = true;
        while (continua) {
            System.out.println('\n' + "========================================================");
            System.out.println('\n' + "O que você deseja fazer?");
            System.out.println('\n' + "1. Comprar um Voo");
            System.out.println("2. Remover um Voo");
            System.out.println("3. Listar os Voos");
            System.out.println("4. Listar os Trechos de um Voo");
            System.out.println("5. Listar as Execuções de Voo de um Voo");
            System.out.println("6. Listar as Execuções de Trecho de um Voo");
            System.out.println("7. Listar as Passagens de um Voo");
            System.out.println("8. Listar os Clientes de um Voo");
            System.out.println("9. Número de passageiros que participaram de Execuções de Trechos de um Voo a partir de uma data");
            
            System.out.println("10. Voltar");

            int opcao = Console.readInt('\n' + "Digite um número entre 1 e 10:");

            System.out.println();

            switch (opcao) {
                case 1 -> {
                    origem = Console.readLine("Vai sair de onde? ");
                    destino = Console.readLine("Vai para onde? ");
                    umVoo = new Voo(origem, destino);
                    try {
                        vooService.incluir(umVoo);
                    }
                    catch (ObjetoDuplicadoException e){
                        System.out.println(e.getMessage());
                        break;
                    }
                    System.out.println('\n' + "Voo número " + umVoo.getId() + " cadastrado com sucesso!");

                }
                case 2 ->    // Remover
                {
                    int id = Console.readInt("Digite o número do voo que seja remover: ");

                    try {
                        vooService.remover(id);
                    } catch (EntidadeNaoEncontradaException e) {
                        System.out.println('\n' + e.getMessage());
                        break;
                    }
                    System.out.println('\n' + "Voo removido com sucesso!");
                }
                case 3 -> {    // Listar tudo
                    List<Voo> voos = vooService.recuperarVoos();
                    for (Voo voo : voos) {
                        System.out.println(voo);
                    }
                }
                case 4 -> {
                    int id = Console.readInt("Qual o id do voo? ");
                    try{
                        umVoo = vooService.recuperarVooPorId(id);
                    }
                    catch (EntidadeNaoEncontradaException e) {
                        System.out.println('\n' + e.getMessage());
                        break;
                    }
                    List<Trecho> trechos = umVoo.getTrechos();
                    for (Trecho trecho : trechos) {
                        System.out.println(trecho);
                    }
                }
                case 5 -> {
                    int id = Console.readInt("Qual o id do voo? ");
                    try{
                        umVoo = vooService.recuperarVooPorId(id);
                    }
                    catch (EntidadeNaoEncontradaException e) {
                        System.out.println('\n' + e.getMessage());
                        break;
                    }
                    List<ExecVoo> execucoes = umVoo.getExecucoesVoos();
                    for (ExecVoo execVoo : execucoes) {
                        System.out.println(execVoo);
                    }
                }
                case 6 -> {
                    int id = Console.readInt("Qual o id do voo? ");
                    try{
                        umVoo = vooService.recuperarVooPorId(id);
                    }
                    catch (EntidadeNaoEncontradaException e) {
                        System.out.println('\n' + e.getMessage());
                        break;
                    }
                    List<Trecho> trechos = umVoo.getTrechos();
                    for (Trecho trecho : trechos) {
                        List<ExecTrecho> execTrechos = trecho.getExecucoesTrechos();
                        for(ExecTrecho execTrecho : execTrechos){
                            System.out.println(execTrecho);
                        }
                    }
                }
                case 7 -> {
                    int id = Console.readInt("Qual o id do voo? ");
                    try{
                        umVoo = vooService.recuperarVooPorId(id);
                    }
                    catch (EntidadeNaoEncontradaException e) {
                        System.out.println('\n' + e.getMessage());
                        break;
                    }
                    List<Trecho> trechos = umVoo.getTrechos();
                    for (Trecho trecho : trechos) {
                        List<ExecTrecho> execTrechos = trecho.getExecucoesTrechos();
                        for (ExecTrecho execTrecho : execTrechos) {
                            List<Passagem> passagens = execTrecho.getPassagens();
                            for (Passagem passagem : passagens) {
                                System.out.println(passagem);
                            }
                        } 
                    }
                }
                case 8 -> {
                    int id = Console.readInt("Qual o id do voo? ");
                    try{
                        umVoo = vooService.recuperarVooPorId(id);
                    }
                    catch (EntidadeNaoEncontradaException e) {
                        System.out.println('\n' + e.getMessage());
                        break;
                    }
                    List<Trecho> trechos = umVoo.getTrechos();
                    for (Trecho trecho : trechos) {
                        List<ExecTrecho> execTrechos = trecho.getExecucoesTrechos();
                        for (ExecTrecho execTrecho : execTrechos) {
                            List<Passagem> passagens = execTrecho.getPassagens();
                            for (Passagem passagem : passagens) {
                                System.out.println(passagem.getCliente());
                            }
                        } 
                    }
                }
                case 9 ->{
                    int id = Console.readInt("Qual o id do voo? ");
                    String data = Console.readLine("Informe a data e hora para busca (DD/MM/AAAA HH:MM:SS): ");
                    try{
                        umVoo = vooService.recuperarVooPorId(id);
                    }
                    catch (EntidadeNaoEncontradaException e) {
                        System.out.println('\n' + e.getMessage());
                        break;
                    }
                    System.out.println(vooService.calcularPassageiros(umVoo, data) + " passageiros voaram em execuções de trecho desse voo a partir desta data.");
                }
                case 10 ->    // Sair
                    continua = false;


                default -> System.out.println('\n' + "Opção inválida!");
            }
        }
    }


}
