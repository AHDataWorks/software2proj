package sample;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class AddCustomerController implements Initializable {
    @FXML
    private Label addressError;

    @FXML
    private TextField addressText;

    @FXML
    private Button cancelButton;

    @FXML
    private ComboBox<String> countryComboBox;

    @FXML
    private Label countryError;

    @FXML
    private TextField customerIDText;

    @FXML
    private Label customerNameError;

    @FXML
    private TextField customerNameText;

    @FXML
    private Label phoneNumberError;

    @FXML
    private TextField phoneText;

    @FXML
    private Label postalCodeError;

    @FXML
    private TextField postalCodeText;

    @FXML
    private Button saveSubmit;

    @FXML
    private ComboBox<String> stateComboBox;

    @FXML
    private Label stateError;

    @FXML
    private Label errorLabel;

    Stage stage;

    static ObservableList<String> allCountries = FXCollections.observableArrayList();

    static ObservableList<String> allUSStates = FXCollections.observableArrayList();

    static ObservableList<String> allCanadianProvinces = FXCollections.observableArrayList();

    static ObservableList<String> allUKDivisions = FXCollections.observableArrayList();

    ObservableList<Customers> allCustomers = FXCollections.observableArrayList();

    public static int userID;

    public static int divisionID;

    @FXML
    public static void initUserID(int userIDNumber) {
        userID = userIDNumber;
    }

    @FXML
    public static void initCountriesForComboBox() throws SQLException {
        allCountries = CountriesQuery.getAllCountryNames();
        allUSStates = FirstLevelDivisionsQuery.getFirstLevelDivisionsByCountry(1);
        allCanadianProvinces = FirstLevelDivisionsQuery.getFirstLevelDivisionsByCountry(3);
        allUKDivisions = FirstLevelDivisionsQuery.getFirstLevelDivisionsByCountry(2);
    }

    @FXML
    void countrySelected(ActionEvent event) {
        if(countryComboBox.getSelectionModel().getSelectedItem() == null) {
            stateComboBox.setVisible(false);
        }
        if(countryComboBox.getSelectionModel().getSelectedItem() != null) {
            stateComboBox.setVisible(true);
//            System.out.println("ping");
            if(countryComboBox.getSelectionModel().getSelectedItem().equals("U.S")) {
                stateComboBox.setItems(allUSStates);
                stateComboBox.setPromptText("Select State");

            }
            if(countryComboBox.getSelectionModel().getSelectedItem().equals("UK")) {
                stateComboBox.setItems(allUKDivisions);
                stateComboBox.setPromptText("Select Region");
            }
            if(countryComboBox.getSelectionModel().getSelectedItem().equals("Canada")) {
                stateComboBox.setItems(allCanadianProvinces);
                stateComboBox.setPromptText("Select Province");
            }

        }
    }

    @FXML
    public void saveSubmit(ActionEvent event) throws IOException, SQLException {
        boolean passcheck = false;

        String userName = UsersQuery.getUserName(userID);
        divisionID = FirstLevelDivisionsQuery.getFirstLevelDivisionID(stateComboBox.getValue());

        Date date = new Date();
        long time = date.getTime();
        Timestamp ts = new Timestamp(time);

        phoneNumberError.setVisible(false);
        postalCodeError.setVisible(false);
        stateError.setVisible(false);
        addressError.setVisible(false);
        customerNameError.setVisible(false);
        countryError.setVisible(false);

        String customerName = customerNameText.getText();
        String address = addressText.getText();
        String postalCode = postalCodeText.getText();
        String phone = phoneText.getText();
        String division = (String) stateComboBox.getValue();

        if (customerNameText.getText().equals("")) {
            customerNameError.setVisible(true);
            errorLabel.setVisible(true);
            errorLabel.setText("* Required Field");

        }

        if (addressText.getText().equals("")) {
            addressError.setVisible(true);
            errorLabel.setVisible(true);
            errorLabel.setText("* Required Field");

        }

        if (postalCodeText.getText().equals("")) {
            postalCodeError.setVisible(true);
            errorLabel.setVisible(true);
            errorLabel.setText("* Required Field");

        }

        if (phoneText.getText().equals("")) {
            phoneNumberError.setVisible(true);
            errorLabel.setVisible(true);
            errorLabel.setText("* Required Field");

        }

        if (stateComboBox.getValue() == null) {
            stateError.setVisible(true);
            errorLabel.setVisible(true);
            errorLabel.setText("* Required Field");

        }

        if (countryComboBox.getValue() == null) {
            countryError.setVisible(true);
            errorLabel.setVisible(true);
            errorLabel.setText("* Required Field");

        }

        if (!customerNameText.equals("")
            && !addressText.equals("")
            && !postalCodeText.equals("")
            && !phoneText.equals("")
            && stateComboBox.getValue() != null
            && countryComboBox.getValue() != null) {
            passcheck = true;
        }




        if (passcheck == true) {

            CustomersQuery.addCustomer(customerName, address, postalCode, phone, ts, userName, ts, userName, divisionID);

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("mainScene.fxml"));
            MainSceneController controller = fxmlLoader.getController();
            controller.initAllCustomers();

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.show();
        }
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

//    @Override
//    public void handle(ActionEvent event) {
//
//        countryComboBox.valueProperty().addListener(new ChangeListener<String>() {
//            @Override
//            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//                System.out.print("test");
//            }
//        });
//
//    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        countryComboBox.setItems(allCountries);
    }
}
