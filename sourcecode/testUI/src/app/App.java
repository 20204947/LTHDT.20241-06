package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/main/Main.fxml")); // Đảm bảo tệp FXML có tên đúng
        Scene scene = new Scene(root);
        
        primaryStage.setResizable(false);
        
        primaryStage.setOnCloseRequest(event -> {
            event.consume();
        });

        primaryStage.setTitle("Main");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
