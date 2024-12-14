module testUI {
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.graphics;

    exports testUI; // Xuất package testUI để module javafx.graphics truy cập
    opens testUI to javafx.fxml; // Mở package testUI cho FXML
}
