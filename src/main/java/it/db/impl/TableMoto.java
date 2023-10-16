package it.db.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.db.api.Records;
import it.model.Moto;

public class TableMoto extends AbstractTable<Moto, Integer>{

    public TableMoto(Connection connection) {
        super(Moto.getRecords(), connection);
    }

    @Override
    public boolean insertValue(Moto value) {
        final String query = "INSERT INTO " + Moto.getRecords().getTableName() 
        + " (idMoto, casaProduttrice, cilindrata, peso) VALUES (?,?,?,?)";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setInt(1, value.getIdMoto());
            statement.setString(2, value.getCasaProduttrice());
            statement.setInt(3, value.getCilindrata());
            statement.setInt(4, value.getPeso());
            statement.executeUpdate();
            return true;
        } catch (final SQLIntegrityConstraintViolationException e) {
            return false;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public List<Moto> readFromResultSet(ResultSet resultSet) {
        final List<Moto> motoList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                final int idMoto = resultSet.getInt("idMoto");
                final String casaProduttrice = resultSet.getString("casaProduttrice");
                final int cilindrata = resultSet.getInt("cilindrata");
                final int peso = resultSet.getInt("peso");
                final Moto moto = new Moto(idMoto, casaProduttrice, cilindrata, peso);
                motoList.add(moto);
            }
        } catch (final SQLException e) {
            e.printStackTrace();
        }
        return motoList;
    }

    @Override
    public Records getRecords() {
        return Moto.getRecords();
    }

    @Override
    public List<Integer> findAllPrimaryKey() {
        return this.findAll().stream().map(value -> value.getIdMoto()).toList();
    }

    public List<String> findAllCasaProduttrice() {
        List<String> listCasaProduttrice = new ArrayList<>();
        try (final Statement statement = this.connection.createStatement()) {
            final ResultSet resultSet = statement.executeQuery("SELECT DISTINCT casaProduttrice FROM moto");
            while (resultSet.next()) {
                listCasaProduttrice.add(resultSet.getString("casaProduttrice"));
            }
            return listCasaProduttrice;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public int findVittorieCasaProduttrice(String casaProduttrice) {
        final String query = "SELECT count(*) as numeroVittorie FROM contratti, gare, tabelloneGare" + 
		    " WHERE contratti.idTeam IN (SELECT idTeam FROM team" + 
			" WHERE team.idMoto IN (SELECT idMoto" + 
							    " FROM moto where casaproduttrice = '"+casaProduttrice+"' ))  AND gare.idGara = tabelloneGare.idGara" + 
            " AND tabelloneGare.idPilota = contratti.idPilota" +
            " AND YEAR(gare.data) >= contratti.annoInizio AND YEAR(gare.data) < contratti.annoFine" +
            " AND tabelloneGare.posizione = 1;";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            final ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            return resultSet.getInt("numeroVittorie");
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

}
