package luisa;
import corejava.Console;
import luisa.exception.EntidadeNaoEncontradaException;
import luisa.model.*;
import luisa.service.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.ConsoleHandler;

public class PrincipalPassagem {
    private final PassagemService passagemService = new PassagemService();
    private final ClienteService clienteService = new ClienteService();

    private int numero = 10;

    public void principal() {

        double preco;
        Passagem umaPassagem;
        Cliente umCliente;
        List<Integer> numeros = new ArrayList<>(); //não sei o tamanho ainda do vetor, por isso Integer

        boolean continua = true;
        while (continua) {
            System.out.println('\n' + "========================================================");
            System.out.println('\n' + "O que você deseja fazer?");
            System.out.println('\n' + "1. Cadastrar passagem");
            System.out.println("2. Remover passagem");
            System.out.println("3. Listar todas as passagens");
            System.out.println("4. Listar as execuções de trecho de uma passagem");
            System.out.println("5. Listar o cliente de uma passagem");
            System.out.println("6. Voltar");

            int opcao = Console.readInt('\n' + "Digite um número entre 1 e 6:");

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
                         System.out.println("aaaa" + numbers[i]);
                     }

                     umaPassagem = new Passagem(umCliente, numero);
                     numero += 10;
                     passagemService.incluir(umaPassagem, numbers);
                     System.out.println("\nPassagem número " + umaPassagem.getId() + " cadastrada com sucesso!");
                 }
                 case 2 ->
                 {
                     int numero = Console.readInt(
                             "Informe o número da passagem que você deseja remover: ");

                     try {
                         passagemService.remover(numero);
                         System.out.println('\n' + "Passagem removida com sucesso!");
                     } catch (EntidadeNaoEncontradaException e) {
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
                 case 6 -> continua = false;

                 default -> System.out.println('\n' + "Opção inválida!");
             }
        }
    }
}
