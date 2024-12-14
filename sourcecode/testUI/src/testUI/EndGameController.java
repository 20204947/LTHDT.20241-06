package testUI;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EndGameController {

    @FXML
    private Button yesButton;

    @FXML
    private Button noButton;
    
    @FXML
    private Stage matchStage;
    
    public void setMatchStage(Stage matchStage) {
        this.matchStage = matchStage;
    }

    @FXML
    private void yes() {
        try {
            // Load màn hình Match
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Match.fxml"));
            Parent root = loader.load();

            Stage matchStage = new Stage();
            matchStage.setScene(new Scene(root));
            matchStage.setTitle("Tro Choi O An Quan");
            matchStage.show();
            
            if (matchStage != null) {
                matchStage.close(); 
            }
            
            Stage currentStage = (Stage) yesButton.getScene().getWindow();
            currentStage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void no() {
        try {
            // Load màn hình Match
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Main.fxml"));
            Parent root = loader.load();

            Stage matchStage = new Stage();
            matchStage.setScene(new Scene(root));
            matchStage.setTitle("Main");
            matchStage.show();
            
            if (matchStage != null) {
                matchStage.close(); 
            }
            
            Stage currentStage = (Stage) noButton.getScene().getWindow();
            currentStage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

