package luisa.model;
import luisa.exception.DataHoraInvalidaException;
import luisa.util.Id;

import java.io.Serializable;
import java.text.NumberFormat;
import java.time.DateTimeException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ExecTrecho implements Serializable{

    @Id
    private int id;
    private ZonedDateTime dataHoraInicial;
    private ZonedDateTime dataHoraFinal;
    private List<Passagem> passagens;
    private ExecVoo umaExecVoo;
    private Trecho umTrecho;

    private static final NumberFormat NF;
    private static final DateTimeFormatter DTF;

    public ExecTrecho (String dataHoraInicial, String dataHoraFinal, ExecVoo umaExecVoo, Trecho umTrecho) {
        setDataHoraInicial(dataHoraInicial);
        setDataHoraFinal(dataHoraFinal);
        validarDatas();
        this.passagens = new ArrayList<>();
        this.umaExecVoo = umaExecVoo;
        this.umTrecho = umTrecho;
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
                "  |  Fim = " + getDataHoraFinal();
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

    public ExecVoo getExecVoo() {
        return umaExecVoo;
    }

    public Trecho getTrecho(){
        return umTrecho;
    }

    public List<Passagem> getPassagens() {
        return passagens;
    }

    public void setDataHoraInicial (String novaDataHora)
    {
        try
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

    public void setDataHoraFinal (String novaDataHora)
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

    public void validarDatas(){
        if (dataHoraFinal.isBefore(dataHoraInicial)) {
            throw new DataHoraInvalidaException("A data final não pode ser anterior à data inicial.");
        }
//        ZonedDateTime agora = ZonedDateTime.now(ZoneId.of("UTC"));
//        if(dataHoraInicial.isBefore(agora) | dataHoraFinal.isBefore(agora)){
//            throw new DataHoraInvalidaException("As datas e horários não podem já ter ocorrido.");
//        }
    }

    public boolean jaAconteceu() {
        ZonedDateTime agora = ZonedDateTime.now(ZoneId.of("UTC"));
        return dataHoraInicial.isBefore(agora);
    }

    public boolean posData(String data) {
        try
        {
            int dia = Integer.parseInt(data.substring(0,2));
            int mes = Integer.parseInt(data.substring(3,5));
            int ano = Integer.parseInt(data.substring(6,10));

            int hora =    Integer.parseInt(data.substring(11,13));
            int minuto =  Integer.parseInt(data.substring(14,16));
            int segundo = Integer.parseInt(data.substring(17,19));

            ZonedDateTime dataIndicada = ZonedDateTime.of(
                    ano, mes, dia, hora, minuto, segundo, 0,
                    ZoneId.of("America/Sao_Paulo")).withZoneSameInstant(ZoneId.of("UTC"));

            ZonedDateTime agora = ZonedDateTime.now(ZoneId.of("UTC"));
            return agora.isAfter(dataIndicada);
        }
        catch(StringIndexOutOfBoundsException | NumberFormatException | DateTimeException e)
        {
            throw new DataHoraInvalidaException("Data e hora inválida.");
        }

    }
}



