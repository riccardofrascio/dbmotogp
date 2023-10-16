package it.db.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import it.db.api.Records;
import it.model.Contratto;
import it.utils.Pair;

public class TableContratti extends AbstractTable<Contratto, Pair<Integer, Integer>>{

    public TableContratti(Connection connection) {
        super(Contratto.getRecords(), connection);
    }

    @Override
    public boolean insertValue(Contratto value) {
        final String query = "INSERT INTO " + Contratto.getRecords().getTableName() 
        + " (idPilota, annoInizio, annoFine, idTeam) VALUES (?,?,?,?)";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setInt(1, value.getIdPilota());
            statement.setInt(2, value.getAnnoInizio());
            statement.setInt(3, value.getAnnoFine());
            statement.setInt(4, value.getIdTeam());
            statement.executeUpdate();
            return true;
        } catch (final SQLIntegrityConstraintViolationException e) {
            return false;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public List<Contratto> readFromResultSet(ResultSet resultSet) {
        final List<Contratto> contrattoList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                final int idPilota = resultSet.getInt("idPilota");
                final int annoInizio = resultSet.getInt("annoInizio");
                final int annoFine = resultSet.getInt("annoFine");
                final int idTeam = resultSet.getInt("idTeam");

                final Contratto contratto = new Contratto(idPilota, annoInizio, annoFine, idTeam);
                contrattoList.add(contratto);
            }
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
        return contrattoList;
    }

    @Override
    public Records getRecords() {
        return Contratto.getRecords();
    }

    @Override
    public List<Pair<Integer, Integer>> findAllPrimaryKey() {
        return this.findAll().stream().map(value -> new Pair<>(value.getIdPilota(), value.getAnnoInizio())).toList();
    }
    
}
