package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Locale;
import java.util.ResourceBundle;

public class Main extends Application {
    public BufferedWriter buffWriter = null;

    public void initFilewriter(String record) {
        try {
            buffWriter = new BufferedWriter(new FileWriter(new File(" login_activity.txt"),true));
            buffWriter.write(record);
            buffWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
        Timestamp timeStampFinal = Timestamp.from(ZonedDateTime.now().toInstant());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("You're about to exit");
        alert.setContentText("Are you sure you want to exit the program?");

        if(alert.showAndWait().get() == ButtonType.OK) {
            String record = "testing";
            record = "\n" + timeStampFinal + " ||| logged out. " ;
            initFilewriter(record);

            stage.close();
        }

    }

    public static void main(String[] args) {
        JDBC.makeConnection();
        launch(args);
    }
}
