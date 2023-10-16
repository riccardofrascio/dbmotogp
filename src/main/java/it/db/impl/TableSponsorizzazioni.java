package it.db.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.db.api.Records;
import it.model.Sponsorizzazione;
import it.utils.Pair;
import it.utils.Tuple;
import it.utils.Utils;

public class TableSponsorizzazioni extends AbstractTable<Sponsorizzazione, Tuple<Integer, Integer, Date>>{

    public TableSponsorizzazioni(Connection connection) {
        super(Sponsorizzazione.getRecords(), connection);
    }

    @Override
    public boolean insertValue(Sponsorizzazione value) {
        final String query = "INSERT INTO " + Sponsorizzazione.getRecords().getTableName() 
        + " (idSponsor, idTeam, dataInizio, dataFine, pagamento) VALUES (?,?,?,?,?)";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setInt(1, value.getIdSponsor());
            statement.setInt(2, value.getIdTeam());
            statement.setDate(3, Utils.dateToSqlDate(value.getDataInizio()));
            statement.setDate(4, Utils.dateToSqlDate(value.getDataFine()));
            statement.setDouble(5, value.getPagamento());
            statement.executeUpdate();
            return true;
        } catch (final SQLIntegrityConstraintViolationException e) {
            return false;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public List<Sponsorizzazione> readFromResultSet(ResultSet resultSet) {
        final List<Sponsorizzazione> sponsorizzazioniList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                final int idSponsor = resultSet.getInt("idSponsor");
                final int idTeam = resultSet.getInt("idTeam");
                final Date dataInizio = Utils.sqlDateToDate(resultSet.getDate("dataInizio"));
                final Date dataFine = Utils.sqlDateToDate(resultSet.getDate("dataFine"));
                final Double pagamento = resultSet.getDouble("pagamento");

                final Sponsorizzazione sponsorizzazione = new Sponsorizzazione(idSponsor, idTeam, dataInizio, dataFine, pagamento);
                sponsorizzazioniList.add(sponsorizzazione);
            }
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
        return sponsorizzazioniList;
    }

    @Override
    public Records getRecords() {
        return Sponsorizzazione.getRecords();
    }

    @Override
    public List<Tuple<Integer, Integer, Date>> findAllPrimaryKey() {
        return this.findAll().stream().map(value -> new Tuple<>(value.getIdSponsor(), value.getIdTeam(), value.getDataInizio())).toList();

    }

    public Pair<Integer, Double> countMaxIncassi() {
        final String query = "SELECT idTeam, SUM(pagamento) AS somma_pagamenti" + 
                " FROM sponsorizzazioni" +
                " GROUP BY idTeam" + 
                " ORDER BY somma_pagamenti DESC" +
                " LIMIT 1; ";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            final ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            final int idTeam = resultSet.getInt("idTeam");
            final Double sommaPagamenti = resultSet.getDouble("somma_pagamenti");
            return new Pair<>(idTeam, sommaPagamenti);

        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
