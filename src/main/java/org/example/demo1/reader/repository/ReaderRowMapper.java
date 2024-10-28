package org.example.demo1.reader.repository;

import org.example.demo1.reader.entity.Reader;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReaderRowMapper {
    public static Reader mapRow(ResultSet rs) throws SQLException {
        Integer id = rs.getInt("id");
        String name = rs.getString("name");
        String email = rs.getString("email");
        return new Reader(id, name, email);
    }
}
