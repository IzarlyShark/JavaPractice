package org.example.demo1.ui.console;

import org.example.demo1.reader.service.ReaderService;

import java.util.Scanner;

import static org.example.demo1.utils.PrintUtils.print;
import static org.example.demo1.utils.PrintUtils.println;

public class ReaderManagementConsoleUI {
    private final ReaderService readerService;

    public ReaderManagementConsoleUI(ReaderService readerService) {
        this.readerService = readerService;
    }

    // Метод для получения всех читателей
    public void getAllReaders() {
        var readers = readerService.getAllReaders();
        if (readers.isEmpty()) {
            print("No readers found");
            return;
        }
        println("Found " + readers.size() + " readers");
        for (var reader : readers) {
            println(reader.toString());
        }
    }

    public void findReadersByEmail() {
        Scanner scanner = new Scanner(System.in);
        println("Please enter the email of the reader you wish to search:");
        String email = scanner.nextLine();

        var reader = readerService.findReaderByEmail(email);
        if (reader == null) {
            println("No reader found with this email");
            return;
        }

        println("Found reader:");
        println(reader.toString());
    }

    public void addReader() {
        String name, email;
        println("If you do not want to enter an optional field, then leave it empty");
        var sc = new Scanner(System.in);
        println("(REQUIRED FIELD) Please enter the name of the reader you wish to add:");
        name = sc.nextLine().trim();
        name = name.isEmpty() ? null : name;
        println("Please enter the email of the reader you wish to add:");
        email = sc.nextLine().trim();
        email = email.isEmpty() ? null : email;

        readerService.addReader(name, email);
        println("Reader successfully added");
    }

    public void deleteReader() {
        println("Please enter the id of the reader you wish to delete:");
        Scanner scanner = new Scanner(System.in);
        int id;
        try {
            id = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            print("Id must be an integer");
            return;
        }
        if (readerService.deleteReader(id))
            print("Reader deleted");
        else
            print("Reader not found");
    }

}
