package com.mvp.java.controllers;

import com.mvp.java.logging.ExceptionWriter;
import com.mvp.java.services.MissionsService;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ConsoleTabController {

    @FXML
    private TextArea missionOverviewText;
    @FXML
    private ListView<String> missionsList;
    private final LoggerTabController loggerController;

    @Autowired
    MissionsService service;

    public void initialize() {
        ObservableList<String> missions = FXCollections.observableArrayList("Apollo", "Shuttle", "Skylab");
        missionsList.setItems(missions);
    }

    @Autowired
    public ConsoleTabController(LoggerTabController loggerController) {
        this.loggerController = loggerController;
    }

    @FXML
    private void onMouseClicked(MouseEvent event) {
        missionOverviewText.clear();
        final String selectedItem = missionsList.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            return;
        }
        missionOverviewText.positionCaret(0);
        missionOverviewText.appendText(getInfo(selectedItem));
    }


    public String getInfo(String selectedItem) {
        String missionInfo = null;

        try {
            missionInfo = service.getMissionInfo(selectedItem);
            loggerController.logMessage("Sucessfully retrieved mission info for " + selectedItem);
        } catch (IOException exception) {
            loggerController.logException(exception);
        }

        return missionInfo;
    }

    public TextArea getMissionOverviewText() {
        return missionOverviewText;
    }

    public ListView<String> getMissionsList() {
        return missionsList;
    }

}
