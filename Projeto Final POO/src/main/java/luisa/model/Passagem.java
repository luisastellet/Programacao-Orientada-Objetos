package luisa.model;
import luisa.util.Id;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Passagem implements Serializable {

    private double preco; //soma dos trechos
    private int id;
    private Cliente umCliente;
    private int numero;
    private List<ExecTrecho> execucoesTrecho;

    public Passagem(Cliente cliente, int numero) {
        this.preco = 0;
        this.umCliente = cliente;
        this.numero = numero;
        this.execucoesTrecho = new ArrayList<>();
    }

    public String toString() {
        return "Id = " + id + " Número = " + numero + "  Preço = " + preco;
    }

    @Id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getPreco() {
        return preco;
    }

    public double getNumero() {
        return numero;
    }

    public void setPreco(double novoPreco) {
        this.preco += novoPreco;
    }

    public Cliente getCliente() {
        return umCliente;
    }

    public List<ExecTrecho> getExecucoesTrechos() {
        return execucoesTrecho;
    }

}
