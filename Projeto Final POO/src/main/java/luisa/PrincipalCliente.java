package luisa;
import corejava.Console;

import luisa.exception.*;
import luisa.model.*;
import luisa.service.*;

import java.util.List;

public class PrincipalCliente {

    private final ClienteService clienteService = new ClienteService();

    public void principal() {

        String nome;
        Cliente umCliente;
        String cpf;

        boolean continua = true;
        while (continua) {
            System.out.println('\n' + "========================================================");
            System.out.println('\n' + "O que você deseja fazer?");
            System.out.println('\n' + "1. Cadastrar Cliente");
            System.out.println("2. Alterar dados do Cliente");
            System.out.println("3. Remover Cliente"); //se não tem passagem pra ocorrer ainda
            System.out.println("4. Listar todos os Clientes");
            System.out.println("5. Listar os dados de um Cliente, incluindo as milhas");
            System.out.println("6. Voltar");

            int opcao = Console.readInt('\n' + "Digite um número entre 1 e 6: ");

            System.out.println();

            switch (opcao) {
                case 1 -> {
                    nome = Console.readLine("Informe o nome do cliente a ser cadastrado: ");
                    cpf = Console.readLine("Informe o cpf do cliente: ");
                    umCliente = new Cliente(nome, cpf);
                    clienteService.incluir(umCliente);
                    System.out.println("\nCliente " + nome + " de número " + umCliente.getId() + " cadastrado com sucesso!");
                }
                case 2 ->
                {
                    int id = Console.readInt("Informe o número do cliente que deseja alterar: ");

                    try {
                        umCliente = clienteService.recuperarClientePorId(id);
                    } catch (EntidadeNaoEncontradaException e) {
                        System.out.println('\n' + e.getMessage());
                        break;
                    }
                    String novoNome = Console.readLine("Informe o novo nome do cliente que você deseja alterar: ");
                    clienteService.alterarNome(umCliente, novoNome);
                    String novoCpf = Console.readLine("Informe o novo cpf do cliente que você deseja alterar: ");
                    clienteService.alterarCpf(umCliente, novoCpf);
                    System.out.println('\n' + "Alteração efetuada com sucesso!");
                }
                case 3 ->
                {
                    int id = Console.readInt("Informe o número do cliente que você deseja remover: ");

                    try {
                        clienteService.remover(id);
                        System.out.println('\n' + "Cliente removido com sucesso!");
                    } catch (EntidadeNaoEncontradaException | ListaDoObjetoNaoVaziaException e) {
                        System.out.println('\n' + e.getMessage());
                    }
                }
                case 4 -> {    // Listar
                    List<Cliente> clientes = clienteService.recuperarClientes();
                    for (Cliente cliente : clientes) {
                        System.out.println(cliente);
                    }
                }
                case 5 -> {
                    int id = Console.readInt("Qual o id do cliente? ");
                    try {
                        umCliente = clienteService.recuperarClientePorId(id);
                    }catch(EntidadeNaoEncontradaException e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                    System.out.println(umCliente);
                    System.out.println("Quantidade de milhas até agora: " + clienteService.calcularMilhas(umCliente));
                }
                case 6 -> continua = false;

                default -> System.out.println('\n' + "Opção inválida!");
            }
        }
    }
}