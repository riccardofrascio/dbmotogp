package it.db.api;

import java.sql.ResultSet;
import java.util.List;


/**
 * Represents a database table, with rows represented by objects of type V
 * and primary key of type K.
 * @param <V> the type of the objects saved in the table
 * @param <K> the type of the primary key of the table
 */
public interface Table<V /*extends Value*/,K> {
    /**
     * @return the name of the table
     */
    String getTableName();
    
    /**
     * Creates the database table
     * @return false if the table could not be created
     */
    boolean createTable();
    
    /**
     * Drops the database table
     * @return false if the table could not be dropped
     */
    boolean dropTable();

    /**
     * Saves an object to the database
     * @param value the object to save to the underlying database
     * @return false if the object could not be saved (e.g. an object with the same key
     *         is already present)
     */
    boolean insertValue(final V value);

    Records getRecords();

    List<V> findAll();

    List<V> readFromResultSet(ResultSet resultSet);

    List<K> findAllPrimaryKey();
}
