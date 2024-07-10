package luisa.model;
import luisa.util.Id;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Voo implements Serializable{

    private String origem;
    private String destino;
    @Id
    private int id;
    private List<ExecVoo> execucoesVoos;
    private List<Trecho> trechos;


    public Voo (String origem, String destino)
    {	this.origem = origem;
        this.destino = destino;
        this.execucoesVoos = new ArrayList<>();
        this.trechos = new ArrayList<>();

    }

    public String toString() {
        return "Id = " + id + "  |  Origem = " + origem + "  |  Destino = " + destino;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrigem() {
        return origem;
    }

    public String getDestino() {
        return destino;
    }

    public List<ExecVoo> getExecucoesVoos() {
        return execucoesVoos;
    }

    public List<Trecho> getTrechos() {
        return trechos;
    }
}
