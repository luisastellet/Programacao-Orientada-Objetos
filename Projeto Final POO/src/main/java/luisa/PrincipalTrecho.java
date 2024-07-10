package luisa;
import corejava.Console;
import luisa.exception.*;
import luisa.model.*;
import luisa.service.*;

import java.util.List;

public class PrincipalTrecho {

    private final TrechoService trechoService = new TrechoService();
    private final VooService vooService = new VooService();

    public void principal() {

        String destino;
        int milhas;
        double preco;
        Trecho umTrecho;
        Voo umVoo;

        boolean continua = true;
        while (continua) {
            System.out.println('\n' + "========================================================");
            System.out.println('\n' + "O que você deseja fazer?");
            System.out.println('\n' + "1. Cadastrar um Trecho");
            System.out.println("2. Remover um Trecho");
            System.out.println("3. Listar os Trechos");
            System.out.println('\n' + "========================================================");
            System.out.println('\n' + "Opções extras");
            System.out.println("4. Listar o Voo de um Trecho");
            System.out.println("5. Listar as Execuções de Trecho de um Trecho");
            System.out.println("6. Listar as Execuções de Voo de um Trecho");
            System.out.println("7. Listar as Passagens de um Trecho");
            System.out.println("8. Listar os Clientes um Trecho");

            System.out.println("9. Voltar");

            int opcao = Console.readInt('\n' + "Digite um número entre 1 e 9:");

            System.out.println();

            switch (opcao) {
                case 1 -> {
                    int idVoo = Console.readInt("Informe o número do voo: ");
                    destino = Console.readLine("Vai para onde? ");
                    milhas = Console.readInt("Quantas milhas para esse trecho? ");
                    preco = Console.readDouble("Qual o preço desse trecho? ");
                    try {
                        umVoo = vooService.recuperarVooPorId(idVoo);
                    }
                    catch(EntidadeNaoEncontradaException e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                    umTrecho = new Trecho(umVoo.getOrigem(), destino, milhas, preco, umVoo);

                    trechoService.incluir(umTrecho);
                    System.out.println("\nTrecho de " + umTrecho.getOrigem() + " para " + destino + " cadastrado com sucesso!");
                }
                case 2 ->    // Remover
                {
                    int id = Console.readInt("Digite o número do trecho que deseja remover: ");

                    try {
                        trechoService.remover(id);
                        System.out.println('\n' + "Trecho removido com sucesso!");
                    } catch (EntidadeNaoEncontradaException e) {
                        System.out.println('\n' + e.getMessage());
                    }
                }
                case 3 -> {    // Listar tudo
                    List<Trecho> trechos = trechoService.recuperarTrechos();
                    for (Trecho trecho : trechos) {
                        System.out.println(trecho);
                    }
                }
                case 4 -> {
                    int id = Console.readInt("Qual o id do trecho? ");
                    try {
                        umTrecho = trechoService.recuperarTrechoPorId(id);
                    }catch(EntidadeNaoEncontradaException e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                    System.out.println(umTrecho.getVoo());
                }
                case 5 -> {
                    int id = Console.readInt("Qual o id do trecho? ");
                    try {
                        umTrecho = trechoService.recuperarTrechoPorId(id);
                    }catch(EntidadeNaoEncontradaException e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                    List<ExecTrecho> execucoes = umTrecho.getExecucoesTrechos();
                    for (ExecTrecho exec : execucoes) {
                        System.out.println(exec);
                    }
                }
                case 6 -> {
                    int id = Console.readInt("Qual o id do trecho? ");
                    try {
                        umTrecho = trechoService.recuperarTrechoPorId(id);
                    }catch(EntidadeNaoEncontradaException e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                    List<ExecTrecho> execucoes = umTrecho.getExecucoesTrechos();
                    for (ExecTrecho exec : execucoes) {
                        System.out.println(exec.getExecVoo());
                    }
                }
                case 7 -> {
                    int id = Console.readInt("Qual o id do trecho? ");
                    try {
                        umTrecho = trechoService.recuperarTrechoPorId(id);
                    }catch(EntidadeNaoEncontradaException e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                    List<ExecTrecho> execucoes = umTrecho.getExecucoesTrechos();
                    for (ExecTrecho exec : execucoes) {
                        List<Passagem> passagens = exec.getPassagens();
                        for(Passagem passagem : passagens){
                            System.out.println(passagem);
                        }
                    }
                }
                case 8 -> {
                    int id = Console.readInt("Qual o id do trecho? ");
                    try {
                        umTrecho = trechoService.recuperarTrechoPorId(id);
                    }catch(EntidadeNaoEncontradaException e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                    List<ExecTrecho> execucoes = umTrecho.getExecucoesTrechos();
                    for (ExecTrecho exec : execucoes) {
                        List<Passagem> passagens = exec.getPassagens();
                        for(Passagem passagem : passagens){
                            System.out.println(passagem.getCliente());
                        }
                    }
                }
                case 9 ->    // Sair
                    continua = false;

                default -> System.out.println('\n' + "Opção inválida!");
            }
        }
    }
}
