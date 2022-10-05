package sample;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class updateAppointmentController {
    @FXML
    private TextField appointmentIDText;

    @FXML
    private Button cancelButton;

    @FXML
    private ComboBox<?> contactComboBox;

    @FXML
    private TextField customerIDText;

    @FXML
    private TextArea descriptionFieldText;

    @FXML
    private Label endDateError;

    @FXML
    private DatePicker endDateText;

    @FXML
    private ComboBox<?> endTimeComboBox;

    @FXML
    private Label endTimeError;

    @FXML
    private Label errorTextLabel;

    @FXML
    private TextField locationText;

    @FXML
    private Label startDateError;

    @FXML
    private DatePicker startDateText;

    @FXML
    private ComboBox<?> startTimeComboBox;

    @FXML
    private Label startTimeError;

    @FXML
    private Button submitButton;

    @FXML
    private TextField titleText;

    @FXML
    private TextField userIDText;

    @FXML
    private AnchorPane scenePane;

    Stage stage;

    @FXML
    void cancelButton(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("mainScene.fxml"));
//        AddProductController controller = fxmlLoader.getController();
//        controller.initAddProductData(imsInventory);

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void submitButton(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("mainScene.fxml"));
//        AddProductController controller = fxmlLoader.getController();
//        controller.initAddProductData(imsInventory);

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }
}
