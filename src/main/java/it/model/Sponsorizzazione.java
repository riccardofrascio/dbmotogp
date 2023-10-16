package it.model;

import java.util.Date;
import java.util.List;

import it.db.api.Records;
import it.utils.Pair;

public class Sponsorizzazione {
    
    private final int idSponsor;
    private final int idTeam;
    private final Date dataInizio;
    private final Date dataFine;
    private final Double pagamento;
    protected static final List<Pair<String, String>> valuesList = List.of(new Pair<>("idSponsor", "int REFERENCES sponsor(idSponsor)"),
            new Pair<>("idTeam", "int REFERENCES team(idTeam)"), new Pair<>("dataInizio", "DATE"),
            new Pair<>("dataFine", "DATE"),
            new Pair<>("pagamento", "DECIMAL(10,4)"),
            new Pair<>("", "PRIMARY KEY(idSponsor, idTeam, dataInizio)"));
    
    public Sponsorizzazione(int idSponsor, int idTeam, Date dataInizio, Date dataFine, Double pagamento) {
        this.idSponsor = idSponsor;
        this.idTeam = idTeam;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.pagamento = pagamento;
    }

    public int getIdSponsor() {
        return idSponsor;
    }
    public int getIdTeam() {
        return idTeam;
    }
    public Date getDataInizio() {
        return dataInizio;
    }
    public Date getDataFine() {
        return dataFine;
    }
    public Double getPagamento() {
        return pagamento;
    }
    
    public static Records getRecords() {
        return new Records() {

            @Override
            public List<Pair<String, String>> getValues() {
                return valuesList;
            }

            @Override
            public String getTableName() {
                return "sponsorizzazioni";
            }
            
        };
    }   



}
