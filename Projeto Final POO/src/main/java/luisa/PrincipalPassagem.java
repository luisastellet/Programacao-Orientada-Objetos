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
    private final ExecTrechoService execTrechoService = new ExecTrechoService();


    public void principal() {

        Passagem umaPassagem;
        Cliente umCliente;
        ExecTrecho umaExecTrecho;
        int num;
        List<Integer> numeros = new ArrayList<>(); //não sei o tamanho ainda do vetor, por isso Integer

        boolean continua = true;
        while (continua) {
            System.out.println('\n' + "========================================================");
            System.out.println('\n' + "O que você deseja fazer?");
            System.out.println('\n' + "1. Cadastrar Passagem");
            System.out.println("2. Remover Passagem");
            System.out.println("3. Listar todas as Passagens");
            System.out.println("4. Relatório de Passagens");

            System.out.println("5. Voltar");

            int opcao = Console.readInt('\n' + "Digite um número entre 1 e 5:");


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
                    List<Passagem> passagens = passagemService.recuperarPassagens();
                    if(passagens.isEmpty()){
                        num = 10;
                    }
                    else num = passagens.get(passagens.size()-1).getNumero();
                    umaPassagem = new Passagem(umCliente, num);

                    int qtd = Console.readInt("Quantas execuções de trecho essa passagem é associada?");
                    if(qtd == 0) break;
                    for(int i = 0; i < qtd; i++) {
                        int idExecTrecho = Console.readInt("Digite o id de uma execução de trecho associada à passagem:");
                        try{
                            umaExecTrecho = execTrechoService.recuperarExecucaoDeTrechoPorId(idExecTrecho);
                        }
                        catch (EntidadeNaoEncontradaException e){
                            System.out.println(e.getMessage());
                            break;
                        }
                        umaPassagem.getExecucoesTrechos().add(umaExecTrecho);
                    }
                    try {
                        passagemService.calcularPreco(umaPassagem);
                        passagemService.incluir(umaPassagem);
                        System.out.println("\nPassagem número " + umaPassagem.getId() + " cadastrada com sucesso!");
                    }
                    catch(EntidadeNaoEncontradaException e) {
                        System.out.println(e.getMessage());
                        break;
                    }
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
                        break;
                    }
                }
                case 3 -> {    // Listar todas as passagens
                    try{
                        List<Passagem> passagens = passagemService.recuperarPassagens();
                        for (Passagem passagem : passagens) {
                            System.out.println(passagem);
                        }
                    }
                    catch (EntidadeNaoEncontradaException e){
                        System.out.println(e.getMessage());
                    }

                }
                case 4 -> {
                    try {
                        List<Passagem> passagens = passagemService.recuperarPassagens();
                        for (Passagem passagem : passagens) {
                            System.out.println("Cliente: "  + passagem.getCliente().getNome());
                            System.out.println("Execuções de trecho: ");
                            List<ExecTrecho> execucoes = passagem.getExecucoesTrechos();
                            for (ExecTrecho execTrecho : execucoes){
                                System.out.println("Id: " + execTrecho.getId() + "  |  " + "Origem: " + execTrecho.getTrecho().getOrigem()+ " |  Destino: " + execTrecho.getTrecho().getDestino() + "  |  Início: " + execTrecho.getDataHoraInicial() + "  |  Fim: " + execTrecho.getDataHoraFinal());
                            }
                            System.out.println('\n');
                        }
                    }catch(EntidadeNaoEncontradaException e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                }
                case 5 -> continua = false;

                default -> System.out.println('\n' + "Opção inválida!");
            }
        }
    }
}
