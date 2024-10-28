package org.example.demo1.utils;


import org.example.demo1.exeptions.DatabaseManagerException;

import java.sql.*;

public class DatabaseManager {
    //Хранит текущее соединение с БД
    private Connection connection;
    private final String url;
    private final String login;
    private final String password;

    //Ициализирует объект DatabaseManager
    public DatabaseManager(String url, String login, String password) {
        this.url = url;
        this.login = login;
        this.password = password;
    }
    //Открывает соединение с БД, если не открыто, устанавливает режим auto-commit
    public void  openConnection(boolean autoCommit){
       if(!isOpenConnection())
           try {
               connection = DriverManager.getConnection(url, login, password);
               connection.setAutoCommit(autoCommit);
           } catch (SQLException e) {
               throw new DatabaseManagerException(e);
           }

    }

    public Connection getConnection() {
        if (!isOpenConnection())
            throw new DatabaseManagerException("Ошибка при создании соединения!");
        return connection;
    }

    public boolean closeConnection() {
        try {
            if (isOpenConnection()) {
                connection.close();
                connection = null;
                return true;
            }
            return false;
        } catch (SQLException e) {
            throw new DatabaseManagerException(e);
        }
    }

    public boolean isOpenConnection() {
        try {
            if (connection == null || connection.isClosed())
                return false;
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public void commitCurrentConnection() {
        if (!isOpenConnection())
            throw new DatabaseManagerException("Ошибка, нет открытого подключения.");
        try {
            connection.commit();
        } catch (SQLException e) {
            throw new DatabaseManagerException(e);
        }
    }

    public void rollbackCurrentConnection() {
        if (!isOpenConnection())
            throw new DatabaseManagerException("Ошибка, нет открытого подключения.");
        try {
            connection.rollback();
        } catch (SQLException e) {
            throw new DatabaseManagerException(e);
        }
    }

}
