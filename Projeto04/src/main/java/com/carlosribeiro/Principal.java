package com.carlosribeiro;
import java.util.List;
import corejava.*;

public class Principal {
    public static void main(String[] args) {
        final int TAM = Console.readInt('\n' + "Informe o tamanho da lista de empregados: ");

        ListaDeObjetos ListaDeEmpregados = new ListaDeObjetos(TAM);

        String nome;
        double salario;
        Empregado umEmpregado;

        boolean continua = true;
        while (continua) {
            System.out.println('\n' + "==============================================================");
            System.out.println('\n' + "O que você deseja fazer?");
            System.out.println('\n' + "1. Cadastrar um empregado");
            System.out.println("2. Alterar um empregado");
            System.out.println("3. Remover um empregado");
            System.out.println("4. Listar relação de empregados");
            System.out.println("5. Listar apenas Número e Nome dos empregados");
            System.out.println("6. Sair");

            int opcao = Console.readInt('\n' + "Digite um número entre 1 e 6:");

            switch (opcao) {
                case 1:
                    nome = Console.readLine('\n' + "Digite o nome do empregado: ");
                    salario = Console.readDouble("Digite o salario do empregado: ");
                    umEmpregado = new Empregado(nome, salario);

        // ==>

                    System.out.println('\n' + "Empregado cadastrado com sucesso!");
                    break;

                case 2:    // Alterar
                {
                    int resposta = Console.readInt('\n' + "Digite o número do empregado "
                            + "que você deseja alterar: ");

        // ==>

                    if (umEmpregado != null)            // Significa que achei o empregado que se deseja alterar
                    {
                        System.out.println('\n' + "O que você deseja alterar?");
                        System.out.println('\n' + "1. Nome");
                        System.out.println("2. Salario");

                        int opcaoAlteracao = Console.readInt('\n' + "Digite 1 ou 2:");

                        switch (opcaoAlteracao) {
                            case 1:
                                String novoNome = Console.readLine("Digite o novo nome: ");
                                umEmpregado.setNome(novoNome);
                                System.out.println('\n' + "Alteração de nome efetuada com sucesso!");
                                break;
                            case 2:
                                double novoSalario = Console.readDouble("Digite o novo salário: ");
                                umEmpregado.setSalario(novoSalario);
                                System.out.println('\n' + "Alteração de salário efetuada com sucesso!");
                                break;
                            default:
                                System.out.println('\n' + "Opção inválida!");
                                break;
                        }
                    } else {
                        System.out.println('\n' + "Empregado não encontrado!");
                    }
                    break;
                }

                case 3:    // Remover
                {
                    int resposta = Console.readInt('\n' + "Digite o número do empregado "
                            + "que você deseja remover: ");
                    if(listadeEmpregados.remover(resposta))
                        System.out.println('\n' + "Empregado removido com sucesso!");
                    else
                        System.out.println('\n' + "Empregado não encontrado!");
                    break;
                }

                case 4:    // Listar tudo
                    if(listaDeEmpregados.vazia())
                        System.out.println('\n' + "Não há empregados na lista.");
                    else { //tem empregado ainda na lista
                        System.out.println("");
                        umEmpregado = (Empregado) listaDeEmpregados.recuperarPrimeiro();

                        while (umEmpregado != null) {
                            System.out.println(umEmpregado);
                            umEmpregado = (Empregado) listaDeEmpregados.recuperarProximo();

                        }
//						umEmpregado = (Empregado) listaDeEmpregados.recuperarProximo();
                    }
                    break;

                case 5:    // Listar Numero e Nome
                    if (listaDeEmpregados.vazia())
                        System.out.println('\n' + "Não há empregados na lista.");
                    else {
                        System.out.println("");
                        umEmpregado = listaDeEmpregados.recuperarPrimeiro();

                        while (umEmpregado != null) {
                            System.out.println("número = " + umEmpregado.getNumero() +
                                    "   Nome = " + umEmpregado.getNome());
                            umEmpregado = listaDeEmpregados.recuperarProximo();
                        }
                    }
                    break;

                case 6:    // Sair
                    continua = false;
                    break;

                default:
                    System.out.println('\n' + "Opção inválida!");
            }
        }
    }
}