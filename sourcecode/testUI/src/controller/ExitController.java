package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ExitController {

    @FXML
    private Button yesButton;

    @FXML
    private Button noButton;

    private Stage mainStage;

    protected void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    @FXML
    private void yes() {
        if (mainStage != null) {
            mainStage.close(); 
        }
        Stage currentStage = (Stage) yesButton.getScene().getWindow();
        currentStage.close();
    }

    @FXML
    private void no() {
        Stage currentStage = (Stage) noButton.getScene().getWindow();
        currentStage.close();
    }
}

