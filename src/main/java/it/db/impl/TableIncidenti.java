package it.db.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;


import it.db.api.Records;
import it.model.Incidente;
import it.utils.Pair;
import it.utils.Tuple;

public class TableIncidenti extends AbstractTable<Incidente, Tuple<Integer, Integer,String>>{

    public TableIncidenti(Connection connection) {
        super(Incidente.getRecords(), connection);
    }

    @Override
    public boolean insertValue(Incidente value) {
        final String query = "INSERT INTO " + Incidente.getRecords().getTableName() 
        + " (idPilota, idGara, descrizione, tipologia) VALUES (?,?,?,?)";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setInt(1, value.getIdPilota());
            statement.setInt(2, value.getIdGara());
            statement.setString(3, value.getDescrizione());
            statement.setString(4, value.getTipologia());
            statement.executeUpdate();
            return true;
        } catch (final SQLIntegrityConstraintViolationException e) {
            return false;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public List<Incidente> readFromResultSet(ResultSet resultSet) {
        final List<Incidente> incidentiList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                final int idPilota = resultSet.getInt("idPilota");
                final int idGara = resultSet.getInt("idGara");
                final String condizioniMeteo = resultSet.getString("descrizione");
                final String idPista = resultSet.getString("tipologia");

                final Incidente incidente = new Incidente(idPilota, idGara, condizioniMeteo, idPista);
                incidentiList.add(incidente);
            }
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
        return incidentiList;
    }

    @Override
    public Records getRecords() {
        return Incidente.getRecords();
    }

    @Override
    public List<Tuple<Integer, Integer, String>> findAllPrimaryKey() {
        return this.findAll().stream().map(value -> new Tuple<>(value.getIdPilota(), value.getIdGara(), value.getDescrizione())).toList();

    }

    public List<Pair<Integer, Integer>> countMaxIncidenti() {
        final String query = "SELECT idPilota, COUNT(*) AS conteggio" +
                " FROM incidenti" +
                " GROUP BY idPilota" +
                " HAVING COUNT(*) = (" +
                " SELECT COUNT(*) AS max_conteggio" +
                " FROM incidenti" +
                " GROUP BY idPilota" +
                " ORDER BY max_conteggio DESC" +
                " LIMIT 1" +
                " );";

        List<Pair<Integer, Integer>> resultList = new ArrayList<>();
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            final ResultSet resultSet = statement.executeQuery(query);
            
            while (resultSet.next()) {
                final int idPilota = resultSet.getInt("idPilota");
                final int conteggio = resultSet.getInt("conteggio");
                final Pair<Integer, Integer> result = new Pair<>(idPilota, conteggio);
                resultList.add(result);
            }
            
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
        return resultList;
    }

}
