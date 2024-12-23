package controller;

import java.io.IOException;
import java.util.Random;

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
	private Button twoPlayerButton;
	@FXML
	private Button botButton;
	
	@FXML
	public void start1() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/start_game/StartGame.fxml"));
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
	public void start2() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/start_game/StartGame2.fxml"));
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
}
