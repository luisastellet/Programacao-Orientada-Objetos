package luisa;
import corejava.Console;
import luisa.exception.EntidadeNaoEncontradaException;
import luisa.exception.TrechoEmExecException;
import luisa.model.ExecTrecho;
import luisa.model.Trecho;
import luisa.model.Voo;
import luisa.service.TrechoService;
import luisa.service.VooService;

import java.util.List;

public class PrincipalTrecho {

    private final TrechoService trechoService = new TrechoService();
    private final VooService vooService = new VooService();

    public void principal() {

        String origem;
        String destino;
        int milhas;
        double preco;
        Trecho umTrecho;
        Voo umVoo;

        boolean continua = true;
        while (continua) {
            System.out.println('\n' + "========================================================");
            System.out.println('\n' + "O que você deseja fazer?");
            System.out.println('\n' + "1. Cadastrar um trecho");
            System.out.println("2. Remover um trecho");
            System.out.println("3. Listar os trechos");
            System.out.println("4. Listar o voo de um determinado trecho");
            System.out.println("5. Listar as execuções de trecho de um determinado trecho");
            System.out.println("6. Voltar");

            int opcao = Console.readInt('\n' + "Digite um número entre 1 e 6:");

            System.out.println();

             switch (opcao) {
                 case 1 -> {
                     origem = Console.readLine("Vai sair de onde? ");
                     destino = Console.readLine("Vai para onde? ");
                     milhas = Console.readInt("Quantas milhas para esse trecho? ");
                     preco = Console.readDouble("Qual o preço desse trecho? ");
                     int idVoo = Console.readInt("Informe o número do voo: ");
                     try {
                         umVoo = vooService.recuperarVooPorId(idVoo);
                     }
                     catch(EntidadeNaoEncontradaException e) {
                         System.out.println(e.getMessage());
                         break;
                     }
                     umTrecho = new Trecho(origem, destino, milhas, preco, umVoo);

                     trechoService.incluir(umTrecho);
                     System.out.println("\nTrecho de " + origem + " para " + destino + " cadastrado com sucesso!");
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
                 case 6 ->    // Sair
                     continua = false;

                 default -> System.out.println('\n' + "Opção inválida!");
             }
        }
    }
}
