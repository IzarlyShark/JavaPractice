package org.example.demo1.reader.service;

import org.example.demo1.exeptions.ServiceException;
import org.example.demo1.reader.entity.Reader;
import org.example.demo1.reader.repository.ReaderRepository;
import org.example.demo1.utils.DatabaseManager;

import java.util.List;

public class ReaderService {
    private final ReaderRepository readerRepository;
    private final DatabaseManager databaseManager;

    public ReaderService(ReaderRepository readerRepository, DatabaseManager databaseManager) {
        this.readerRepository = readerRepository;
        this.databaseManager = databaseManager;
    }

    public List<Reader> getAllReaders() {
        try {
            databaseManager.openConnection(true);
            return readerRepository.getAll();
        } finally {
            databaseManager.closeConnection();
        }
    }

    public Reader findReaderByEmail(String email) {
        try {
            databaseManager.openConnection(true);
            return readerRepository.findByEmail(email);
        } finally {
            databaseManager.closeConnection();
        }
    }

    public void addReader(String name, String email) {
        if (name == null || name.isBlank())
            throw new ServiceException("Name cannot be blank");
        else if (email == null || email.isBlank())
            throw new ServiceException("Email cannot be blank");
        try {
            databaseManager.openConnection(false);
            if (readerRepository.existsByEmail(email))
                throw new ServiceException("Email already exists");
            readerRepository.save(new Reader(name, email));
            databaseManager.commitCurrentConnection();
        } catch (Exception e) {
            if (databaseManager.isOpenConnection())
                databaseManager.rollbackCurrentConnection();
            throw new ServiceException(e);
        } finally {
            //В любом случае соединение закроется
            databaseManager.closeConnection();
        }
    }

    public boolean deleteReader(int id) {
        try {
            databaseManager.openConnection(true);
            return readerRepository.deleteById(id);
        } finally {
            databaseManager.closeConnection();
        }
    }
}
