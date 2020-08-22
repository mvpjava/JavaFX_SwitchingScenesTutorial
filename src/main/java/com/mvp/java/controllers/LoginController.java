package com.mvp.java.controllers;

import com.mvp.java.services.AuthenticationService;
import com.mvp.java.view.FxmlView;
import com.mvp.java.view.StageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component 
public class LoginController implements FxmlController {

    @FXML
    private TextField userField;
    @FXML
    private TextField passwordField;
    @FXML
    private Button loginButton;
    @FXML
    private Label statusLabel;

    private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);
    private final AuthenticationService authenticationService;
    private static final String LOGIN_ERROR_MSG = "Invalid username and/or password provided";
    private final StageManager stageManager;

    @Autowired @Lazy //lazy since Stage for StageManager not available yet at initialization time 
    public LoginController(AuthenticationService authenticationService, StageManager stageManager) {
        this.authenticationService = authenticationService;
        this.stageManager = stageManager;
    }

    @Override
    public void initialize() {
    } 

    @FXML
    public void loginButtonPressed(ActionEvent event) {
        final Authentication proposedAuthenticationToken = createAuthenticationToken(getUsername(), getPassword());

        try {
          authenticationService.authenticate(proposedAuthenticationToken);
          stageManager.switchScene(FxmlView.MAIN);
        } catch (AuthenticationException authenticationException) {
            LOG.info(authenticationException.getMessage());
            clearCredentials();
            statusLabel.setText(LOGIN_ERROR_MSG);
        }
    }

    private Authentication createAuthenticationToken(String username, String password) {
        return new UsernamePasswordAuthenticationToken(username, password);
    }

    private void clearCredentials() {
        userField.clear();
        passwordField.clear();
    }

    @FXML
    private void usernameTextFieldMouseClicked(MouseEvent event) {
        clearOutStatusLabel();
    }

    @FXML
    private void passwordTextFieldMouseClicked(MouseEvent event) {
        clearOutStatusLabel();
    }

    private void clearOutStatusLabel() {
        statusLabel.setText("");
    }

    public TextField getUserField() {
        return userField;
    }

    public TextField getPasswordField() {
        return passwordField;
    }

    public String getUsername() {
        return userField.getText();
    }

    public String getPassword() {
        return passwordField.getText();
    }
}
