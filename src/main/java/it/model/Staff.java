package it.model;

import java.util.Date;
import java.util.List;

import it.db.api.Records;
import it.utils.Pair;

public class Staff {

    private final int idStaff;
    private final String codiceFiscale;
    private final String nome;
    private final String cognome;
    private final Date dataNascita;
    private final String ruolo;
    private final int idTeam;
    protected static final List<Pair<String, String>> valuesList = List.of(new Pair<>("idStaff", "INT NOT NULL PRIMARY KEY"),
            new Pair<>("codiceFiscale", "CHAR(20)"),
            new Pair<>("nome", "CHAR(30)"), new Pair<>("cognome", "CHAR(30)"),
            new Pair<>("dataNascita", "DATE"), new Pair<>("ruolo", "CHAR(30)"), 
            new Pair<>("idTeam", "int REFERENCES team(idTeam)"));
    
    public Staff(int idStaff, String codiceFiscale, String nome, String cognome, Date dataNascita, String ruolo,
            int idTeam) {
    this.idStaff = idStaff;
    this.codiceFiscale = codiceFiscale;
    this.nome = nome;
    this.cognome = cognome;
    this.dataNascita = dataNascita;
    this.ruolo = ruolo;
    this.idTeam = idTeam;
    }
    
    public int getIdStaff() {
        return idStaff;
    }
    public String getCodiceFiscale() {
        return codiceFiscale;
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
    public String getRuolo() {
        return ruolo;
    }
    public int getIdTeam() {
        return idTeam;
    }

    public static Records getRecords() {
        return new Records() {

            @Override
            public List<Pair<String, String>> getValues() {
                return valuesList;
            }

            @Override
            public String getTableName() {
                return "staff";
            }
            
        };
    }
}
