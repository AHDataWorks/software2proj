package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Andrew Hobbs Software II Project");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

        primaryStage.setOnCloseRequest(event -> {
            event.consume();
            exitProgram(primaryStage);
        });
    }
    public void exitProgram(Stage stage) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("You're about to exit");
        alert.setContentText("Are you sure you want to exit the program?");

        if(alert.showAndWait().get() == ButtonType.OK) {

            stage.close();
        }

    }

    public static void main(String[] args) {
        JDBC.makeConnection();
        launch(args);
    }
}
