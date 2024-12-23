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

public class StartGame2Controller {
	
	@FXML
	private TextField id;
	
	@FXML
	private TextField name;
	
    @FXML
    private Button startButton;
    
    @FXML
    private Button quitButton;

    private Stage mainStage;      // Tham chiếu đến Stage của màn hình Main
    
	@FXML
    private Stage startStage;

    // Set tham chiếu đến Stage Main và Stage StartGame
    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }
    
    public void setStartStage(Stage startStage) {
        this.startStage = startStage;
    }

    @FXML
    private void startGame() {
        try {

            // Kiểm tra dữ liệu đầu vào trước khi tải GameBoard
            if (!validateInputs()) {
                return;
            }else {
                // Đóng mainStage nếu đã mở
              if (mainStage != null) {
                  mainStage.close();
              }
              
              if (startStage != null) {
                  startStage.close();
              }

              // Đóng cửa sổ hiện tại
              Stage currentStage = (Stage) startButton.getScene().getWindow();
              currentStage.close();
            }

            // Load màn hình GameBoard
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/game_board/GameBoard2.fxml"));
            Parent root = loader.load();

            GameBoard2Controller gameBoard2Controller = loader.getController();
            gameBoard2Controller.setIdAndName(id.getText(), name.getText(), "VIPPROMAX", "BOT");

            Stage matchStage = new Stage();
            
            matchStage.setResizable(false);
            
            matchStage.setOnCloseRequest(event -> {
                event.consume();
            });
            
            matchStage.setResizable(false);
            matchStage.setScene(new Scene(root));
            matchStage.setTitle("Trò chơi Ô Ăn Quan");
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

    private boolean validateInputs() {
        try {
            nullStringException(id.getText(), "ID of Player");
            nullStringException(name.getText(), "Name of Player");
        } catch (NullStringException e) {
            showPopupError(e.getMessage());
            return false;
        }
        return true;
    }

    private void nullStringException(String text, String fieldName) throws NullStringException {
        if (text == null || text.trim().isEmpty()) {
            throw new NullStringException(fieldName + " cannot be empty !!!");
        }
    }

    public void showPopupError(String errorMessage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/popup/ErrorEnterTextField.fxml"));
            Parent root = loader.load();

            ErrorEnterTextFieldController controller = loader.getController();
            controller.setError(errorMessage);

            Stage popupStage = new Stage();
            popupStage.setResizable(false);
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setScene(new Scene(root));
            popupStage.setTitle("Error");
            popupStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
