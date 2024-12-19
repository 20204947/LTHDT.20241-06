package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.setting.Setting;

public class TestController {
	@FXML
	private Button settingButton;
	@FXML
	private Button testButton;
	
	@FXML
	public void setting() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/setting/Setting.fxml"));
            Parent root = loader.load();

            Stage popupStage = new Stage();
            
            popupStage.setResizable(false);
            
            popupStage.setOnCloseRequest(event -> {
                event.consume();
            });
            
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setScene(new Scene(root));
            popupStage.setTitle("Setting");
            popupStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	@FXML
	public void test() {
		System.out.println(Setting.getSpeed());
		System.out.println(Setting.getValueOfSmallGem());
		System.out.println(Setting.getValueOfBigGem());
	}
}
