package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class StartGameController {
	
	@FXML
	private TextField id1;
	
	@FXML
	private TextField id2;
	
	@FXML
	private TextField name1;
	@FXML
	private TextField name2;

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
            
            if (mainStage != null) {
                mainStage.close();
            }
            
            Stage currentStage = (Stage) startButton.getScene().getWindow();
            currentStage.close();
            // Load màn hình Match
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/game_board/GameBoard.fxml"));
            Parent root = loader.load();
            
            GameBoardController gameBoardController = loader.getController();
            
            gameBoardController.setIdAndName(id1.getText(), name1.getText(), id2.getText(), name2.getText());
        	
            Stage matchStage = new Stage();
            
            matchStage.setResizable(false);
            
            matchStage.setScene(new Scene(root));
            matchStage.setTitle("Tro Choi O An Quan");
            matchStage.show();

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
