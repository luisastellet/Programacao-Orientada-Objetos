package luisa.model;
import luisa.util.Id;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Trecho implements Serializable{

    private String origem;
    private String destino;
    private int milhas;
    private double preco;
    @Id
    private int id;
    private List<ExecTrecho> execucoesTrechos;
    private Voo umVoo;


    public Trecho (String origem, String destino, int milhas, double preco, Voo umVoo)
    {
        if(umVoo.getTrechos().isEmpty()){
            this.origem = umVoo.getOrigem();
        }
        else{
            List<Trecho> trechos = umVoo.getTrechos();
            this.origem = trechos.get(trechos.size() - 1).destino;
        }
        this.destino = destino;
        this.milhas = milhas;
        this.preco = preco;
        this.umVoo = umVoo;
        this.execucoesTrechos = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String toString() {
        return "Id = " + id + "  |  Origem = " + origem + "  |  Destino = " + destino + "  |  Milhas = " + milhas + "  |  Preço = " + preco + "  |  Voo = " + getVoo().getId();
    }

    public String getOrigem (){
        return origem;
    }

    public String getDestino (){
        return destino;
    }

    public void setOrigem (String novo){
        this.origem = novo;
    }

    public int getMilhas (){
        return milhas;
    }

    public double getPreco (){
        return preco;
    }

    public List<ExecTrecho> getExecucoesTrechos() {
        return execucoesTrechos;
    }

    public Voo getVoo() {
        return umVoo;
    }
}
