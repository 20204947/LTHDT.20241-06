package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainController {
	
	@FXML
	private Button startButton;
	
	@FXML
	private Button exitButton;
	
	@FXML
	private Button helpButton;
	
	@FXML
	private Button settingButton;
	
	  @FXML
	    private void start() {
	        try {
	            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/start_game/Start.fxml"));
	            Parent root = loader.load();

	            Stage popupStage = new Stage();
	            
	            popupStage.setResizable(false);
	            
	            popupStage.setOnCloseRequest(event -> {
	                event.consume();
	            });
	            
	            popupStage.initModality(Modality.APPLICATION_MODAL);
	            popupStage.setScene(new Scene(root));
	            popupStage.setTitle("Start");
	            
	            StartController startController = loader.getController();
	            
	            startController.setMainStage(((Stage) startButton.getScene().getWindow()));
	            
	            popupStage.showAndWait();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	  
	  @FXML
	  private void exit() {
	      try {
	          FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/exit/Exit.fxml"));
	          Parent root = loader.load();

	          Stage popupStage = new Stage();
	          
	          popupStage.setResizable(false);
	          
	          popupStage.setOnCloseRequest(event -> {
	              event.consume();
	          });
	          
	          popupStage.initModality(Modality.APPLICATION_MODAL);
	          popupStage.setScene(new Scene(root));
	          popupStage.setTitle("Exit Confirmation");

	          ExitController exitController = loader.getController();
	          
	          exitController.setMainStage((Stage) exitButton.getScene().getWindow());

	          popupStage.showAndWait();
	      } catch (IOException e) {
	          e.printStackTrace();
	      }
	  }


	
	@FXML
	private void help() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/help/Help.fxml"));
            Parent root = loader.load();

            Stage popupStage = new Stage();
            
            popupStage.setResizable(false);
            
            popupStage.setOnCloseRequest(event -> {
                event.consume();
            });
            
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setScene(new Scene(root));
            popupStage.setTitle("Help");
            popupStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	@FXML
	private void setting() {
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
            popupStage.setTitle("Help");
            popupStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
