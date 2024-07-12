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
            System.out.println('\n' + "1. Cadastrar um Voo");
            System.out.println("2. Remover um Voo");
            System.out.println("3. Listar os Voos");
            System.out.println("4. Número de passageiros que participaram de Execuções de Trechos de um Voo a partir de uma data");
            System.out.println("5. Voltar");

            int opcao = Console.readInt('\n' + "Digite um número entre 1 e 5:");

            System.out.println();

            switch (opcao) {
                case 1 -> {
                    origem = Console.readLine("Vai sair de onde? ");
                    destino = Console.readLine("Vai para onde? ");
                    umVoo = new Voo(origem, destino);
                    try {
                        vooService.incluir(umVoo);
                        System.out.println('\n' + "Voo número " + umVoo.getId() + " cadastrado com sucesso!");
                    }
                    catch (ObjetoDuplicadoException e){
                        System.out.println(e.getMessage());
                        break;
                    }
                }
                case 2 ->    // Remover
                {
                    int id = Console.readInt("Digite o número do voo que seja remover: ");

                    try {
                        vooService.remover(id);
                        System.out.println('\n' + "Voo removido com sucesso!");
                    } catch (EntidadeNaoEncontradaException | ListaDoObjetoNaoVaziaException e) {
                        System.out.println('\n' + e.getMessage());
                        break;
                    }
                }
                case 3 -> {    // Listar tudo
                    List<Voo> voos = vooService.recuperarVoos();
                    for (Voo voo : voos) {
                        System.out.println(voo);
                    }
                }
                case 4 ->{
                    int id = Console.readInt("Qual o id do voo? ");
                    String data = Console.readLine("Informe a data e hora para busca (DD/MM/AAAA HH:MM:SS): ");
                    try{
                        umVoo = vooService.recuperarVooPorId(id);
                    }
                    catch (EntidadeNaoEncontradaException e) {
                        System.out.println('\n' + e.getMessage());
                        break;
                    }
                    vooService.calcularPassageiros(umVoo, data);
                }
                case 5 ->    // Sair
                    continua = false;


                default -> System.out.println('\n' + "Opção inválida!");
            }
        }
    }


}
