package it.db.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import it.db.api.Records;
import it.model.Sponsor;

public class TableSponsor extends AbstractTable<Sponsor, Integer>{

    public TableSponsor(Connection connection) {
        super(Sponsor.getRecords(), connection);
    }

    @Override
    public boolean insertValue(Sponsor value) {
        final String query = "INSERT INTO " + Sponsor.getRecords().getTableName() 
        + " (idSponsor, nome, stato, citta) VALUES (?,?,?,?)";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setInt(1, value.getIdSponsor());
            statement.setString(2, value.getNome());
            statement.setString(3, value.getStato());
            statement.setString(4, value.getCitta());
            statement.executeUpdate();
            return true;
        } catch (final SQLIntegrityConstraintViolationException e) {
            return false;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public List<Sponsor> readFromResultSet(ResultSet resultSet) {
       final List<Sponsor> sponsorList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                final int idSponsor = resultSet.getInt("idSponsor");
                final String nome = resultSet.getString("nome");
                final String stato = resultSet.getString("stato");
                final String citta = resultSet.getString("citta");

                final Sponsor sponsor = new Sponsor(idSponsor, nome, stato, citta);
                sponsorList.add(sponsor);
            }
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
        return sponsorList;
    }

    @Override
    public Records getRecords() {
        return Sponsor.getRecords();
    }

    @Override
    public List<Integer> findAllPrimaryKey() {
        return this.findAll().stream().map(value -> value.getIdSponsor()).toList();
    }
}
