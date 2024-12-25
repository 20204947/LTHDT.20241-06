package controller;

import java.io.IOException;

import exception.NegativeNumberException;
import exception.NullStringException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.setting.Setting;

public class SettingController {
	@FXML
	private Button saveButton;
	@FXML
	private Button quitButton;
	@FXML
	private TextField speed;
	@FXML
	private TextField valueSmallGem;
	@FXML
	private TextField valueBigGem;
	
	@FXML
	private void initialize() {
		speed.setText(Integer.toString(Setting.getSpeed()));
		valueSmallGem.setText(Integer.toString(Setting.getValueOfSmallGem()));
		valueBigGem.setText(Integer.toString(Setting.getValueOfBigGem()));
	}
	
	@FXML
	private void save() {
	    try {
	    	if(!validateInputs()) {
	    		return;
	    	}
	        int sp = parsePositiveInteger(speed.getText());
	        int vmg = parsePositiveInteger(valueSmallGem.getText(), "Value of Small Gem");
	        int vbg = parsePositiveInteger(valueBigGem.getText(), "Value of Big Gem");

	        if (vmg > vbg / 5) {
	            throw new NegativeNumberException("Value of Small Gem must be less than or equal to 1/5 of Big Gem !!!");
	        }

	        Setting.setSpeed(sp);
	        Setting.setValueOfSmallGem(vmg);
	        Setting.setValueOfBigGem(vbg);

	        quit();
	    } catch (NegativeNumberException |NumberFormatException e) {
	        showPopupError(e.getMessage());
	    }
	}
	
    private void nullStringException(String text, String fieldName) throws NullStringException {
        if (text == null || text.trim().isEmpty()) {
            throw new NullStringException(fieldName + " cannot be empty !!!");
        }
    }


	private int parsePositiveInteger(String text, String fieldName) throws NegativeNumberException, NumberFormatException {
	    int value = Integer.parseInt(text);
	    if (value <= 0) {
	        throw new NegativeNumberException(fieldName + " must be greater than 0 !!!");
	    }
	    return value;
	}
	
    private boolean validateInputs() {
        try {
            nullStringException(speed.getText(), "Speed");
            nullStringException(valueSmallGem.getText(), "Value of Small Gem");
            nullStringException(valueBigGem.getText(), "Value of Big Gem");
        } catch (NullStringException e) {
            showPopupError(e.getMessage());
            return false;
        }
        return true;
    }
	
	private int parsePositiveInteger(String text) throws NegativeNumberException, NumberFormatException {
	    int value = Integer.parseInt(text);
	    if (value < 100) {
	        throw new NegativeNumberException("Speed must be greater than or equal 100 ms !!!");
	    }
	    if(value > 2000) {
	    	throw new NegativeNumberException("Speed must be less than or equal 2000 ms !!!");
	    }
	    return value;
	}

	
    @FXML
    private void quit() {
    	Stage currentStage = (Stage) quitButton.getScene().getWindow();
        currentStage.close();
    }
    
    private void showPopupError(String err) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/popup/ErrorEnterTextField.fxml"));
            Parent root = loader.load();
            
            ErrorEnterTextFieldController c = loader.getController();
            c.setError(err);

            Stage popupStage = new Stage();
            
            popupStage.setResizable(false);
            
            popupStage.setOnCloseRequest(event -> {
                event.consume();
            });

            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setScene(new Scene(root));
            popupStage.setTitle("Error");
            popupStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
