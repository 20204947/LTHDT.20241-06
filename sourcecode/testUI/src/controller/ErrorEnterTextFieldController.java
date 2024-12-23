package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ErrorEnterTextFieldController {
	
	@FXML
	private Text error;
	
	@FXML
	private Button okButton;
	
	public void setError(String err) {
		error.setText(err);
	}
	
    @FXML
    private void ok() {
    	Stage currentStage = (Stage) okButton.getScene().getWindow();
        currentStage.close();
    }
}
