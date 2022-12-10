module com.example.uianalzyer {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.uianalzyer to javafx.fxml;
    exports com.example.uianalzyer;
}