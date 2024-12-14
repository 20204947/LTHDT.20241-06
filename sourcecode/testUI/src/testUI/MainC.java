package testUI;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainC {
	
	private Stage stage = new Stage();
	
	public Stage getopupStage() {
		try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Main.fxml"));
            Parent root = loader.load();

            
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
		return this.stage;
	}
}
