package com.carlosribeiro;

import corejava.Console;

public class Principal {
    public static void main(String[] args) {

        PrincipalProduto principalProduto = new PrincipalProduto();
        PrincipalLance principalLance = new PrincipalLance();

        boolean continua = true;
        while (continua) {
            System.out.println('\n' + "========================================================");
            System.out.println('\n' + "O que você deseja fazer?");
            System.out.println('\n' + "1. Tratar Produtos");
            System.out.println("2. Tratar Lances");
            System.out.println("3. Sair");

            int opcao = Console.readInt('\n' + "Digite um número entre 1 e 3:");

            System.out.println();

            switch (opcao) {
                case 1 -> {
                    principalProduto.principal();
                }
                case 2 -> {
                    principalLance.principal();
                }
                case 3 -> {
                    continua = false;
                }

                default -> System.out.println('\n' + "Opção inválida!");
            }
        }
    }
}