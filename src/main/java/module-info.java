module com.example.gabfosol {
    requires javafx.controls;
    requires javafx.fxml;
    requires android.json;
    requires java.desktop;


    opens com.example.gabfosol to javafx.fxml;
    exports com.example.gabfosol;
}