package org.example.demo1;



import org.example.demo1.config.AppConfig;

public class Main {
    public static void main(String[] args) {
        var mainUI = AppConfig.getMainMenuConsoleUI();
        mainUI.run();
    }
}
