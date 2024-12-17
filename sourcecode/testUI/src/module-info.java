module testUI {
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.graphics;

    exports controller; // Xuất package testUI để module javafx.graphics truy cập
    exports app; 
    opens controller to javafx.fxml; // Mở package testUI cho FXML
    opens app to javafx.fxml;
}
