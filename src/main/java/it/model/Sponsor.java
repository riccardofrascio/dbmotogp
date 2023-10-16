package it.model;

import java.util.List;

import it.db.api.Records;
import it.utils.Pair;

public class Sponsor {
    
    private final int idSponsor;
    private final String nome;
    private final String stato;
    private final String citta;
    protected static final List<Pair<String, String>> valuesList = List.of(new Pair<>("idSponsor", "INT NOT NULL PRIMARY KEY"),
            new Pair<>("nome", "CHAR(30)"), new Pair<>("stato", "CHAR(30)"),
            new Pair<>("citta", "CHAR(30)"));


    public Sponsor(int idSponsor, String nome, String stato, String citta) {
        this.idSponsor = idSponsor;
        this.nome = nome;
        this.stato = stato;
        this.citta = citta;
    }

    public int getIdSponsor() {
        return idSponsor;
    }
    public String getNome() {
        return nome;
    }
    public String getStato() {
        return stato;
    }
    public String getCitta() {
        return citta;
    }
    
    public static Records getRecords() {
        return new Records() {

            @Override
            public List<Pair<String, String>> getValues() {
                return valuesList;
            }

            @Override
            public String getTableName() {
                return "sponsor";
            }
            
        };
    }    
}
