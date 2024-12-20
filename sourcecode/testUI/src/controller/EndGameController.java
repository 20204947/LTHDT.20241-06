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

public class EndGameController {
	
	Player player1 = new Player(true);
	Player player2 = new Player(false);
	
	@FXML
	private Text id1;
	
	@FXML
	private Text name1;
	
	@FXML
	private Text score1;
	
	@FXML
	private Text id2;
	
	@FXML
	private Text name2;
	
	@FXML
	private Text score2;
	
	@FXML
	private Text winner;
	
	public void setPlayer(String id1, String name1, int score1, String id2, String name2, int score2) {
		this.id1.setText(id1);
		this.name1.setText(name1);
		this.score1.setText(Integer.toString(score1));
		this.id2.setText(id2);
		this.name2.setText(name2);
		this.score2.setText(Integer.toString(score2));
		
		if(score1>score2) {
			this.winner.setText(name1);
		}else {
			this.winner.setText(name2);
		}
		
		this.player1.setId(id1);
		this.player1.setName(name1);
		this.player1.setScore(score1);
		this.player2.setId(id2);
		this.player2.setName(name2);
		this.player2.setScore(score2);
	}
	
	public void setPlayer(String id1, String name1, int score1, String id2, String name2, int score2, boolean surrender) {
		this.id1.setText(id1);
		this.name1.setText(name1);
		this.score1.setText(Integer.toString(score1));
		this.id2.setText(id2);
		this.name2.setText(name2);
		this.score2.setText(Integer.toString(score2));
		
		if(!surrender) {
			this.winner.setText(name1);
		}else {
			this.winner.setText(name2);
		}
		
		this.player1.setId(id1);
		this.player1.setName(name1);
		this.player1.setScore(score1);
		this.player2.setId(id2);
		this.player2.setName(name2);
		this.player2.setScore(score2);
	}

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
            
            if (matchStage != null) {
                matchStage.close(); 
            }
            
            Stage currentStage = (Stage) yesButton.getScene().getWindow();
            currentStage.close();
            
            // Load màn hình Match
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/game_board/GameBoard.fxml"));
            Parent root = loader.load();

            GameBoardController gameBoardController = loader.getController();
            
            gameBoardController.setIdAndName(player1.getId(), player1.getName(), player2.getId(), player2.getName());
            
            Stage matchStage = new Stage();
            
            matchStage.setResizable(false);
            
//            matchStage.setOnCloseRequest(event -> {
//                event.consume();
//            });
            
            matchStage.initModality(Modality.APPLICATION_MODAL);
            matchStage.setScene(new Scene(root));
            matchStage.setTitle("Tro Choi O An Quan");
            matchStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void no() {
        try {
            if (matchStage != null) {
                matchStage.close(); 
            }
            
            Stage currentStage = (Stage) noButton.getScene().getWindow();
            currentStage.close();
            
            // Load màn hình Match
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/main/Main.fxml"));
            Parent root = loader.load();

            Stage matchStage = new Stage();
            matchStage.initModality(Modality.APPLICATION_MODAL);
            matchStage.setScene(new Scene(root));
            matchStage.setTitle("Main");
            matchStage.show();
            


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

