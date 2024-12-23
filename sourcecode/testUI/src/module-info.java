module testUI {
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.graphics;

    exports controller; // Xuất package testUI để module javafx.graphics truy cập
    exports app; 
    exports test;
    opens controller to javafx.fxml; // Mở package testUI cho FXML
    opens app to javafx.fxml;
    opens test to javafx.fxml;
}
