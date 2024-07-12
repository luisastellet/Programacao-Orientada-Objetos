package luisa.model;
import luisa.exception.DataHoraInvalidaException;
import luisa.util.Id;

import java.io.Serializable;
import java.text.NumberFormat;
import java.time.DateTimeException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.time.format.DateTimeFormatter;

public class ExecVoo implements Serializable {

    @Id
    private int id;
    private ZonedDateTime dataHoraInicial;
    private ZonedDateTime dataHoraFinal;
    private List<ExecTrecho> execucoesTrecho;
    private Voo umVoo;


    private static final NumberFormat NF;
    private static final  DateTimeFormatter DTF;

    // Formatador para imprimir e efetuar o parse de objetos date-time

    public ExecVoo(String dataHoraInicial, String dataHoraFinal, Voo umVoo) {
        setDataHoraInicial(dataHoraInicial);
        setDataHoraFinal(dataHoraFinal);
        validarDatas();
        this.umVoo = umVoo;
        this.execucoesTrecho = new ArrayList<>();

    }

    static
    {	NF = NumberFormat.getNumberInstance(new Locale("pt", "BR"));
        DTF = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        NF.setMaximumFractionDigits (2);	   // O default é 3.
        NF.setMinimumFractionDigits (2);
    }

    public String toString() {
        return "Id = " + id +
                "  |  Início = " + getDataHoraInicial() +
                "  |  Fim = " + getDataHoraFinal() +
                "  |  Voo = " + getVoo().getId();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDataHoraInicial()
    {
        return DTF.format(dataHoraInicial.withZoneSameInstant(ZoneId.of("America/Sao_Paulo")));
    }

    public String getDataHoraFinal()
    {
        return DTF.format(dataHoraFinal.withZoneSameInstant(ZoneId.of("America/Sao_Paulo")));
    }

    public List<ExecTrecho> getExecucoesTrechos() {
        return execucoesTrecho;
    }

    public Voo getVoo() {
        return umVoo;
    }

    public void setDataHoraInicial (String novaDataHora) throws DataHoraInvalidaException
    {
        try                         //  dd/mm/aaaa hh:mm:ss
        {
            int dia = Integer.parseInt(novaDataHora.substring(0,2));
            int mes = Integer.parseInt(novaDataHora.substring(3,5));
            int ano = Integer.parseInt(novaDataHora.substring(6,10));

            int hora =    Integer.parseInt(novaDataHora.substring(11,13));
            int minuto =  Integer.parseInt(novaDataHora.substring(14,16));
            int segundo = Integer.parseInt(novaDataHora.substring(17,19));

            this.dataHoraInicial = ZonedDateTime.of(
                            ano, mes, dia, hora, minuto, segundo, 0,
                            ZoneId.of("America/Sao_Paulo")).withZoneSameInstant(ZoneId.of("UTC"));

        }
        catch(StringIndexOutOfBoundsException | NumberFormatException | DateTimeException e)
        {
            throw new DataHoraInvalidaException("Data e hora inválida.");
        }
    }


    public void setDataHoraFinal (String novaDataHora) throws DataHoraInvalidaException
    {
        try                         //  dd/mm/aaaa hh:mm:ss
        {
            int dia = Integer.parseInt(novaDataHora.substring(0,2));
            int mes = Integer.parseInt(novaDataHora.substring(3,5));
            int ano = Integer.parseInt(novaDataHora.substring(6,10));

            int hora =    Integer.parseInt(novaDataHora.substring(11,13));
            int minuto =  Integer.parseInt(novaDataHora.substring(14,16));
            int segundo = Integer.parseInt(novaDataHora.substring(17,19));

            this.dataHoraFinal = ZonedDateTime.of(
                    ano, mes, dia, hora, minuto, segundo, 0,
                    ZoneId.of("America/Sao_Paulo")).withZoneSameInstant(ZoneId.of("UTC"));

        }
        catch(StringIndexOutOfBoundsException | NumberFormatException | DateTimeException e)
        {
            throw new DataHoraInvalidaException("Data e hora inválida.");
        }
    }

    public void validarDatas() throws DataHoraInvalidaException {
        if (dataHoraFinal.isBefore(dataHoraInicial)) {
            throw new DataHoraInvalidaException("A data final não pode ser anterior à data inicial.");
        }
    }

}