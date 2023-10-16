package it.model;

import java.util.List;

import it.db.api.Records;
import it.utils.Pair;

public class Team {

    private final int idTeam;
    private final String nome;
    private final int idMoto;
    protected static final List<Pair<String, String>> valuesList = List.of(new Pair<>("idTeam", "INT NOT NULL PRIMARY KEY"), 
            new Pair<>("nome", "CHAR(40)"),
            new Pair<>("idMoto", "int REFERENCES moto(idMoto)"));

    public Team(int idTeam, String nome, int idMoto) {
        this.idTeam = idTeam;
        this.nome = nome;
        this.idMoto = idMoto;
    }

    public int getIdTeam() {
        return idTeam;
    }
    public String getNome() {
        return nome;
    }
    public int getIdMoto() {
        return idMoto;
    }

    public static Records getRecords() {
        return new Records() {

            @Override
            public List<Pair<String, String>> getValues() {
                return valuesList;    
            }

            @Override
            public String getTableName() {
                return "team";
            }
            
        };
    }

}
