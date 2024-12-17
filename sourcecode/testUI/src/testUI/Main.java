package testUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml")); // Đảm bảo tệp FXML có tên đúng
        Scene scene = new Scene(root);
        
        primaryStage.setResizable(false);
        
        primaryStage.setOnCloseRequest(event -> {
            // Hủy sự kiện đóng cửa sổ
            event.consume();
            System.out.println("Nút Close đã bị chặn!");
        });

        primaryStage.setTitle("Main");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}