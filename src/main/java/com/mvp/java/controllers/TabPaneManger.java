package com.mvp.java.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TabPaneManger implements FxmlController{

    private final ConsoleTabController consoleTabController;
    private final LoggerTabController loggerTabController;
    @FXML  private Tab tabConsole; 
    @FXML  private Tab tabLogger;

    @Autowired
    public TabPaneManger(ConsoleTabController consoleTabController, LoggerTabController loggerTabController) {
        this.consoleTabController = consoleTabController;
        this.loggerTabController = loggerTabController;
    }
    
    @Override
    public void initialize() {
    }

    public ConsoleTabController getConsoleTabController() {
        return consoleTabController;
    }

    public LoggerTabController getLoggerTabController() {
        return loggerTabController;
    }
}