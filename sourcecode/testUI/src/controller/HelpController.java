package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HelpController {
	@FXML
	private Button quitButton;
	
    @FXML
    private void quit() {
    	Stage currentStage = (Stage) quitButton.getScene().getWindow();
        currentStage.close();
    }
}
