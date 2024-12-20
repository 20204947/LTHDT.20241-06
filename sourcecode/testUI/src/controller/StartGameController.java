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

            // Kiểm tra dữ liệu đầu vào trước khi tải GameBoard
            if (!validateInputs()) {
                return;
            }else {
                // Đóng mainStage nếu đã mở
              if (mainStage != null) {
                  mainStage.close();
              }

              // Đóng cửa sổ hiện tại
              Stage currentStage = (Stage) startButton.getScene().getWindow();
              currentStage.close();
            }

            // Load màn hình GameBoard
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/game_board/GameBoard.fxml"));
            Parent root = loader.load();

            GameBoardController gameBoardController = loader.getController();
            gameBoardController.setIdAndName(id1.getText(), name1.getText(), id2.getText(), name2.getText());

            Stage matchStage = new Stage();
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
            nullStringException(id1.getText(), "ID of Player 1");
            nullStringException(name1.getText(), "Name of Player 1");
            nullStringException(id2.getText(), "ID of Player 2");
            nullStringException(name2.getText(), "Name of Player 2");
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
