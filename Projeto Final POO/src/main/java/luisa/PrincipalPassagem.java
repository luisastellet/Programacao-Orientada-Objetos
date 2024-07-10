package luisa;
import corejava.Console;
import luisa.exception.*;
import luisa.model.*;
import luisa.service.*;

import java.util.ArrayList;
import java.util.List;

public class PrincipalPassagem {

    private final PassagemService passagemService = new PassagemService();
    private final ClienteService clienteService = new ClienteService();

    public void principal() {

        Passagem umaPassagem;
        Cliente umCliente;
        int num;
        List<Integer> numeros = new ArrayList<>(); //não sei o tamanho ainda do vetor, por isso Integer

        boolean continua = true;
        while (continua) {
            System.out.println('\n' + "========================================================");
            System.out.println('\n' + "O que você deseja fazer?");
            System.out.println('\n' + "1. Cadastrar Passagem");
            System.out.println("2. Remover Passagem");
            System.out.println("3. Listar todas as Passagens");
            System.out.println("4. Listar as Execuções de Trecho de uma Passagem");
            System.out.println("5. Listar o Cliente de uma Passagem");
            System.out.println("6. Listar as Execuções de Voo de uma Passagem");
            System.out.println("7. Listar o Voo de uma Passagem");
            System.out.println("8. Listar os Trechos de uma Passagem");

            System.out.println("9. Voltar");

            int opcao = Console.readInt('\n' + "Digite um número entre 1 e 9:");

            System.out.println();

            switch (opcao) {
                case 1 -> {
                    int idCliente = Console.readInt("Informe o número do cliente correspondente: ");
                    try {
                        umCliente = clienteService.recuperarClientePorId(idCliente);
                    }catch(EntidadeNaoEncontradaException e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                    String aux = Console.readLine("Digite os ids das execuções de trechos associadas (separando por um espaço em branco): ");
                    String[] numbersString = (aux.split(" ")); //tem todos os ids das execuções

                    int[] numbers = new int[numbersString.length];
                    for (int i = 0; i < numbersString.length; i++) {
                        numbers[i] = Integer.parseInt(numbersString[i]);
                    }
                    if(passagemService.recuperarPassagens().getLast() == null){
                        num = 10;
                    }
                    else num = passagemService.recuperarPassagens().getLast().getNumero();
                    umaPassagem = new Passagem(umCliente, num);

                    try {
                        passagemService.incluir(umaPassagem, numbers);
                        passagemService.calcularPreco(umaPassagem);
                    }
                    catch(EntidadeNaoEncontradaException e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println("\nPassagem número " + umaPassagem.getId() + " cadastrada com sucesso!");
                }
                case 2 ->
                {
                    int numero = Console.readInt(
                            "Informe o número da passagem que você deseja remover: ");

                    try {
                        passagemService.remover(numero);
                        System.out.println('\n' + "Passagem removida com sucesso!");
                    } catch (EntidadeNaoEncontradaException | ListaDoObjetoNaoVaziaException e) {
                        System.out.println('\n' + e.getMessage());
                    }
                }
                case 3 -> {    // Listar todas as passagens
                    List<Passagem> passagens = passagemService.recuperarPassagens();
                    for (Passagem passagem : passagens) {
                        System.out.println(passagem);
                    }
                }
                case 4 -> {
                    int id = Console.readInt("Qual o id da passagem? ");
                    try {
                        umaPassagem = passagemService.recuperarPassagemPorId(id);
                    }catch(EntidadeNaoEncontradaException e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                    List<ExecTrecho> execucoes = umaPassagem.getExecucoesTrechos();
                    for (ExecTrecho exec : execucoes) {
                        System.out.println(exec);
                    }
                }
                case 5 -> {
                    int id = Console.readInt("Qual o id da passagem? ");
                    try {
                        umaPassagem = passagemService.recuperarPassagemPorId(id);
                    }catch(EntidadeNaoEncontradaException e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                    System.out.println(umaPassagem.getCliente());
                }
                case 6 -> {
                int id = Console.readInt("Qual o id da passagem? ");
                    try {
                        umaPassagem = passagemService.recuperarPassagemPorId(id);
                    }catch(EntidadeNaoEncontradaException e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                    List<ExecTrecho> execucoes = umaPassagem.getExecucoesTrechos();
                    for (ExecTrecho exec : execucoes) {
                        System.out.println(exec.getExecVoo());
                    }
                }
                case 7 -> {
                    int id = Console.readInt("Qual o id da passagem? ");
                    try {
                        umaPassagem = passagemService.recuperarPassagemPorId(id);
                    }catch(EntidadeNaoEncontradaException e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                    List<ExecTrecho> execucoes = umaPassagem.getExecucoesTrechos();
                    for (ExecTrecho exec : execucoes) {
                        System.out.println(exec.getExecVoo().getVoo());
                    }
                }
                case 8 -> {
                    int id = Console.readInt("Qual o id da passagem? ");
                    try {
                        umaPassagem = passagemService.recuperarPassagemPorId(id);
                    }catch(EntidadeNaoEncontradaException e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                    List<ExecTrecho> execucoes = umaPassagem.getExecucoesTrechos();
                    for (ExecTrecho exec : execucoes) {
                        System.out.println(exec.getTrecho());
                    }
                }
                case 9 -> continua = false;

                default -> System.out.println('\n' + "Opção inválida!");
            }
        }
    }
}
