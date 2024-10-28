package org.example.demo1.reader.repository;


import org.example.demo1.exeptions.RepositoryException;
import org.example.demo1.reader.entity.Reader;
import org.example.demo1.utils.DatabaseManager;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ReaderRepository {
    private final DatabaseManager dbM;

    private static final String SAVE_READER_SQL = """
            INSERT INTO READERS(name, email)
                                VALUES(?, ?)
                    RETURNING ID""";
    private static final String EXISTS_BY_EMAIL_SQL = "SELECT 1 FROM READERS WHERE email = ?";
    private static final String FIND_BY_EMAIL_SQL = """
            SELECT id, name, email
            FROM READERS WHERE email = ?""";
    private static final String DELETE_BY_ID_SQL = "DELETE FROM READERS WHERE id = ?";
    private static final String GET_ALL_READERS_SQL = "SELECT id, name, email FROM READERS";
    public ReaderRepository(DatabaseManager dbM) {
        this.dbM = dbM;
    }

    public void save(Reader reader) {
        var conn = dbM.getConnection();
        try (var stmt = conn.prepareStatement(SAVE_READER_SQL, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, reader.getName());
            stmt.setString(2, reader.getEmail());
            int insertedRow = stmt.executeUpdate();
            if (insertedRow > 0) {
                try (var generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        reader.setId(generatedKeys.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }

    }

    public boolean existsByEmail(String email) {
        var conn = dbM.getConnection();
        try (var stmt = conn.prepareStatement(EXISTS_BY_EMAIL_SQL)) {
            stmt.setString(1, email);
            stmt.executeQuery();
            try (var rs = stmt.getResultSet()) {
                return rs.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Reader findByEmail(String email) {
        Reader reader = null;
        var conn = dbM.getConnection();
        try (var stmt = conn.prepareStatement(FIND_BY_EMAIL_SQL)) {
            stmt.setString(1, email);
            var resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                reader = ReaderRowMapper.mapRow(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reader;
    }

    public List<Reader> getAll() {
        List<Reader> readers = new ArrayList<>();
        var conn = dbM.getConnection();
        try (var stmt = conn.prepareStatement(GET_ALL_READERS_SQL)) {
            var resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                readers.add(ReaderRowMapper.mapRow(resultSet)); // Используем маппер
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return readers;
    }


    public boolean deleteById(int id) {
        var conn = dbM.getConnection();
        try (var stmt = conn.prepareStatement(DELETE_BY_ID_SQL)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
