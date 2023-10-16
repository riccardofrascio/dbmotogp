package it.db.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import it.db.api.Records;
import it.model.Classifica;
import it.utils.Pair;

public class TableClassifica extends AbstractTable<Classifica, Pair<Integer, Integer>>{

    public TableClassifica(Connection connection) {
        super(Classifica.getRecords(), connection);
    }

    @Override
    public boolean insertValue(Classifica value) {
        final String query = "INSERT INTO " + Classifica.getRecords().getTableName() + " (idPilota, anno, punteggio) VALUES (?,?,?)";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setInt(1, value.getIdPilota());
            statement.setInt(2, value.getAnno());
            statement.setInt(3, value.getPunteggio());
            statement.executeUpdate();
            return true;
        } catch (final SQLIntegrityConstraintViolationException e) {
            return false;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public List<Classifica> readFromResultSet(ResultSet resultSet) {
        final List<Classifica> classificaList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                final int idPilota = resultSet.getInt("idPilota");
                final int anno = resultSet.getInt("anno");
                final int punteggio = resultSet.getInt("punteggio");

                final Classifica classifica = new Classifica(idPilota, anno, punteggio);
                classificaList.add(classifica);
            }
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
        return classificaList;
    }

    @Override
    public Records getRecords() {
        return Classifica.getRecords();
    }

    @Override
    public List<Pair<Integer, Integer>> findAllPrimaryKey() {
        return this.findAll().stream().map(value -> new Pair<>(value.getIdPilota(), value.getAnno())).toList();
    }

}
