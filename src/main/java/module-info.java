module com.example.uianalzyer {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.uianalzyer to javafx.fxml;
    exports com.example.uianalzyer;
}