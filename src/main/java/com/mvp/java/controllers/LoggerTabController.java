package com.mvp.java.controllers;

import com.mvp.java.logging.ExceptionWriter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoggerTabController implements FxmlController {
    private static final String   NEWLINE = "\n";
    private static final Logger   LOG     = LoggerFactory.getLogger(LoggerTabController.class);
    @FXML
    private TextArea              loggerTxtArea;
    @FXML
    private Button                clearLogButton;
    @FXML
    private Button                printLogButton;
    private final ExceptionWriter exceptionsWriter;

    @Autowired
    public LoggerTabController(ExceptionWriter exceptionsWriter) {
        this.exceptionsWriter = exceptionsWriter;
    }

    /** **************************** PRIVATE ********************************* */
    private void appendExceptionToLogFile(String message) {
        LOG.error(message);
    }

    /*
     *  log Exception to logger tab text area which can be viewed by end user to
     * show FSR on site or print for later proof of problem
     */
    private void appendToTab(String logString) {
        loggerTxtArea.appendText(logString + NEWLINE);
    }

    @FXML
    private void clearLogAction(ActionEvent event) {
        loggerTxtArea.clear();
    }

    @Override
    public void initialize() {}

    // log to tab and file; convienience method
    public void logException(Throwable throwable) {
        String exceptionAsString = exceptionsWriter.getExceptionAsString(throwable);

        appendToTab(exceptionAsString);
        appendExceptionToLogFile(exceptionAsString);
    }

    public void logMessage(String message) {
        appendToTab(message);
    }
}
