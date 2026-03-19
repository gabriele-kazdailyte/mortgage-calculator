module com.example.counter {
    requires javafx.controls;
    requires javafx.fxml;

    exports com.example.counter;
    exports com.example.counter.UI;

    opens com.example.counter.UI to javafx.fxml;
}