package com.example.counter.UI;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LoanApplication extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoanApplication.class.getResource("/com/example/counter/counter.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Mortgage calculator");
        stage.setScene(scene);
        stage.show();
    }
}
