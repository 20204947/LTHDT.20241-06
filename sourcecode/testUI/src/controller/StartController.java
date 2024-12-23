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

public class StartController {
	@FXML
	private Button twoPlayerButton;
	@FXML
	private Button botButton;
	@FXML
	private Button quitButton;
	
    private Stage mainStage;
	
    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }
	
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
            
            
            StartGameController startGameController = loader.getController();
            
            startGameController.setMainStage(mainStage);
            
            startGameController.setStartStage((Stage) twoPlayerButton.getScene().getWindow());
            
            popupStage.showAndWait();
            quit();
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
            
            StartGame2Controller startGame2Controller = loader.getController();
            
            startGame2Controller.setMainStage(mainStage);
            
            startGame2Controller.setStartStage((Stage) botButton.getScene().getWindow());
            
            popupStage.showAndWait();
            quit();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
    @FXML
    private void quit() {
        Stage currentStage = (Stage) quitButton.getScene().getWindow();
        currentStage.close();
    }
}
