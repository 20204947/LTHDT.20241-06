package testUI;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class StartGameController {

    @FXML
    private Button startButton;
    
    @FXML
    private Button quitButton;

    private Stage mainStage;      // Tham chiếu đến Stage của màn hình Main

    // Set tham chiếu đến Stage Main và Stage StartGame
    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    @FXML
    private void startGame() {
        try {
            // Load màn hình Match
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Match.fxml"));
            Parent root = loader.load();

            Stage matchStage = new Stage();
            matchStage.setScene(new Scene(root));
            matchStage.setTitle("Tro Choi O An Quan");
            matchStage.show();
            
            if (mainStage != null) {
                mainStage.close();
            }
            
            Stage currentStage = (Stage) startButton.getScene().getWindow();
            currentStage.close();

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
