package it.model;

import java.util.Date;
import java.util.List;

import it.db.api.Records;
import it.utils.Pair;

public class Pilota {
    
    private final int idPilota;
    private final String nome;
    private final String cognome;
    private final Date dataNascita;
    private final String nazionalita;
    private int punteggioAttuale;
    protected static final List<Pair<String, String>> valuesList = List.of(new Pair<>("idPilota", "INT NOT NULL PRIMARY KEY"),
            new Pair<>("nome", "CHAR(30)"), new Pair<>("cognome", "CHAR(30)"),
            new Pair<>("dataNascita", "DATE"), new Pair<>("nazionalita", "CHAR(30)"), 
            new Pair<>("punteggioAttuale", "int"));

    
    public Pilota(int idPilota, String nome, String cognome, Date dataNascita, String nazionalita, int punteggioAttuale) {
        this.idPilota = idPilota;
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.nazionalita = nazionalita;
        this.punteggioAttuale = punteggioAttuale;
    }

    public int getIdPilota() {
        return idPilota;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public Date getDataNascita() {
        return dataNascita;
    }

    public String getNazionalita() {
        return nazionalita;
    }

    public int getPunteggioAttuale() {
        return punteggioAttuale;
    }

    public void setPunteggioAttuale(int punteggioAttuale) {
        this.punteggioAttuale = punteggioAttuale;
    }

    public static Records getRecords() {
        return new Records() {

            @Override
            public List<Pair<String, String>> getValues() {
                return valuesList;
            }

            @Override
            public String getTableName() {
                return "piloti";
            }
            
        };
    }


}
