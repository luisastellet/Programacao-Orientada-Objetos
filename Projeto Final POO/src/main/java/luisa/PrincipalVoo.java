package luisa;
import corejava.Console;

import luisa.exception.EntidadeNaoEncontradaException;
import luisa.model.ExecTrecho;
import luisa.model.ExecVoo;
import luisa.model.Trecho;
import luisa.model.Voo;
import luisa.service.VooService;

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
            System.out.println('\n' + "1. Comprar um voo");
            System.out.println("2. Remover um voo");
            System.out.println("3. Listar os voos");
            System.out.println("4. Listar os trechos de um voo");
            System.out.println("5. Listar as execuções de voo de um voo");
            System.out.println("6. Número de passageiros participaram de execuções de trechos de um voo a partir de uma data");
            System.out.println("7. Voltar");

            int opcao = Console.readInt('\n' + "Digite um número entre 1 e 7:");

            System.out.println();

            switch (opcao) {
                case 1 -> {
                    origem = Console.readLine("Vai sair de onde? ");
                    destino = Console.readLine("Vai para onde? ");
                    umVoo = new Voo(origem, destino);
                    vooService.incluir(umVoo);
                    System.out.println("\nVoo número " + umVoo.getId() + " cadastrado com sucesso!");
                }
                case 2 ->    // Remover
                {
                    int id = Console.readInt("Digite o número do voo que seja remover: ");

                    try {
                        vooService.remover(id);
                        System.out.println('\n' + "Voo removido com sucesso!");
                    } catch (EntidadeNaoEncontradaException e) {
                        System.out.println('\n' + e.getMessage());
                    }
                }
                case 3 -> {    // Listar tudo
                    List<Voo> voos = vooService.recuperarVoos();
                    for (Voo voo : voos) {
                        System.out.println(voo);
                    }
                }
                case 4 -> {
                    int id = Console.readInt("Qual o id do voo? ");
                    umVoo = vooService.recuperarVooPorId(id);
                    List<Trecho> trechos = umVoo.getTrechos();
                    for (Trecho trecho : trechos) {
                        System.out.println(trecho);
                    }
                }
                case 5 -> {
                    int id = Console.readInt("Qual o id do voo? ");
                    umVoo = vooService.recuperarVooPorId(id);
                    List<ExecVoo> execucoes = umVoo.getExecucoesVoos();
                    for (ExecVoo exec : execucoes) {
                        System.out.println(exec);
                    }
                }
                case 6 ->{
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
                case 7 ->    // Sair
                    continua = false;


                default -> System.out.println('\n' + "Opção inválida!");
            }
        }
    }


}
