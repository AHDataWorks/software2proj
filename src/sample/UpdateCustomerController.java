package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class UpdateCustomerController {
    @FXML
    private Label addressError;

    @FXML
    private Label customerNameError;

    @FXML
    private Label phoneNumberError;

    @FXML
    private Label postalCodeError;

    @FXML
    private Button saveSubmit;

    @FXML
    private Button cancelButton;

    @FXML
    private AnchorPane scenePane;

    Stage stage;

    @FXML
    public void saveSubmit(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("mainScene.fxml"));
//        AddProductController controller = fxmlLoader.getController();
//        controller.initAddProductData(imsInventory);

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void cancelButton(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("mainScene.fxml"));
//        AddProductController controller = fxmlLoader.getController();
//        controller.initAddProductData(imsInventory);

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }
}
