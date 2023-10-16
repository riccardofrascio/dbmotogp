package it.db.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import it.db.api.Records;
import it.model.Pista;

public class TablePiste extends AbstractTable<Pista, Integer>{
    
    

    public TablePiste(Connection connection) {
        super(Pista.getRecords(), connection);
    }

    @Override
    public boolean insertValue(Pista value) {
        final String query = "INSERT INTO " + Pista.getRecords().getTableName() 
        + " (idPista, stato, citta, indirizzo, lunghezza, curveSx, curveDx) VALUES (?,?,?,?,?,?,?)";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setInt(1, value.getIdPista());
            statement.setString(2, value.getStato());
            statement.setString(3, value.getCitta());
            statement.setString(4, value.getIndirizzo());
            statement.setInt(5, value.getLunghezza());
            statement.setInt(6, value.getCurveSx());
            statement.setInt(7, value.getCurveDx());
            statement.executeUpdate();
            return true;
        } catch (final SQLIntegrityConstraintViolationException e) {
            return false;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public List<Pista> readFromResultSet(ResultSet resultSet) {
        final List<Pista> pisteList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                final int idPista = resultSet.getInt("idPista");
                final String stato = resultSet.getString("stato");
                final String citta = resultSet.getString("citta");
                final String indirizzo = resultSet.getString("indirizzo");
                final int lunghezza = resultSet.getInt("lunghezza");
                final int curveSx = resultSet.getInt("curveSx");
                final int curveDx = resultSet.getInt("curveDx");

                final Pista pista = new Pista(idPista, stato, citta, indirizzo, lunghezza, curveSx, curveDx);
                pisteList.add(pista);
            }
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
        return pisteList;
    }
    
    @Override
    public Records getRecords() {
        return Pista.getRecords();
    }

    @Override
    public List<Integer> findAllPrimaryKey() {
        return this.findAll().stream().map(value -> value.getIdPista()).toList();
    }
}
