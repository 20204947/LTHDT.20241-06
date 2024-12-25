package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.player.Player;

public class SurrenderController {
	
	Player player1 = new Player(true);
	Player player2 = new Player(false);

    @FXML
    private Button yesButton;

    @FXML
    private Button noButton;

    private Stage gameBoardStage;
    
    protected void setPlayer(String id1, String name1, int score1, String id2, String name2, int score2, boolean surrender) {
		this.player1.setId(id1);
		this.player1.setName(name1);
		this.player1.setScore(score1);
		this.player2.setId(id2);
		this.player2.setName(name2);
		this.player2.setScore(score2);
		
		this.player1.setTurn(surrender);
	}

    protected void setGameBoardStage(Stage gameBoardStage) {
        this.gameBoardStage = gameBoardStage;
    }

    @FXML
    private void yes() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/end_game/EndGame.fxml"));
            Parent root = loader.load();
            
            Stage popupStage = new Stage();
            
            popupStage.setResizable(false);
            
            popupStage.setOnCloseRequest(event -> {
                event.consume();
            });
            
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setScene(new Scene(root));
            popupStage.setTitle("Game Over");
            
            EndGameController endGameController = loader.getController();
            
            endGameController.setPlayer(player1.getId(), player1.getName(), player1.getScore(), player2.getId(), player2.getName(), player2.getScore(), player1.isTurn());
            
            endGameController.setMatchStage(gameBoardStage);
            
            popupStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        no();
       
    }

    @FXML
    private void no() {
        Stage currentStage = (Stage) noButton.getScene().getWindow();
        currentStage.close();
    }
}

