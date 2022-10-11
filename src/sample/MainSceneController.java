package sample;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
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
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.Locale;
import java.util.ResourceBundle;



public class MainSceneController implements Initializable {

    @FXML
    private AnchorPane scenePane;

    Stage stage;

    @FXML
    private Button exitButton;

    @FXML
    private Button updateCustomer;

    @FXML
    private Button addCustomerButton;

    @FXML
    private Button addAppointmentButton;

    @FXML
    private Button updateAppointmentButton;

    @FXML
    private Button viewAllAppointmentsButton;

    @FXML
    private TableView<Appointments> appointmentsTable;

    @FXML
    private TableColumn<Appointments, java.sql.Timestamp> startTime;

    @FXML
    private TableColumn<Appointments, Integer> apptID;

    @FXML
    private Button deleteAppointmentButton;

    Locale currentLocale = Locale.getDefault();

    public static ObservableList<Appointments> allAppointments;

    public static int userID;

    public static String userName;

    @FXML
    private TableColumn<Customers, String> customersAddressColumn;

    @FXML
    private TableColumn<Customers, String> customersNameColumn;

    @FXML
    private TableColumn<Customers, String> customersPhoneColumn;

    @FXML
    private TableView<Customers> customersTable;

    @FXML
    public static void initUserID(int userIDNumber) throws SQLException {

        userID = userIDNumber;
        userName = UsersQuery.getUserName(userID);
    }

    @FXML
    public static void initAllAppts(ObservableList allApts) {
        allAppointments = allApts;
    }

    @FXML
    public static ObservableList<Customers> allCustomers;

    @FXML static void initAllCustomers() throws SQLException {
        allCustomers = CustomersQuery.getAllCustomers();
    }

    @FXML
    private Label userNameLabel;



    @FXML
    void exitProgram(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("You're about to exit");
        alert.setContentText("Are you sure you want to exit the program?");

        if(alert.showAndWait().get() == ButtonType.OK) {
            stage = (Stage) scenePane.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    void switchToAddCustomer(ActionEvent event) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("addCustomer.fxml"));
        AddCustomerController controller = fxmlLoader.getController();
        controller.initCountriesForComboBox();
        controller.initUserID(userID);

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void switchToUpdateCustomer(ActionEvent event) throws IOException, SQLException {
        Customers selectedCustomer = customersTable.getSelectionModel().getSelectedItem();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("updateCustomer.fxml"));
        UpdateCustomerController controller = fxmlLoader.getController();
        controller.initSelectedCustomer(selectedCustomer);
        controller.initCountriesForComboBox();
        controller.initUserID(userID);

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void switchToAddAppointment(ActionEvent event) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("addAppointment.fxml"));
        AddAppointmentController controller = fxmlLoader.getController();
        controller.initAllAppts(allAppointments);
        controller.initUserID(userID);
        controller.initAllContacts();
        controller.initApptTimes();
        controller.initAllTypes();


        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void switchToUpdateAppointment(ActionEvent event) throws IOException, SQLException {
        Appointments selectedAppointment = appointmentsTable.getSelectionModel().getSelectedItem();
        if (selectedAppointment != null) {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("updateAppointment.fxml"));
            UpdateAppointmentController controller = fxmlLoader.getController();

            controller.initSelectedAppointment(selectedAppointment);
            controller.initAllAppts(allAppointments);
            controller.initUserID(userID);
            controller.initAllContacts();
            controller.initApptTimes();
            controller.initAllTypes();
            controller.initFormattedTimes(selectedAppointment);
            controller.initSelectedContact(selectedAppointment);
            controller.initSelectedType(selectedAppointment);

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    void switchToViewAllAppointments(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("viewAllAppointments.fxml"));
//        AddProductController controller = fxmlLoader.getController();
//        controller.initAddProductData(imsInventory);

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void deleteAppointment(MouseEvent event) throws IOException, SQLException {
        Appointments appointment = appointmentsTable.getSelectionModel().getSelectedItem();
        String appointmentType = appointment.getType();
        int appointmentID = appointment.getApptID();
        if (appointment != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Appointment Deletion Confirmation");
            alert.setHeaderText("You are about to delete appointment " + appointmentID);
            alert.setContentText("Are you sure you want to delete this " + appointmentType + " appointment?");

            if(alert.showAndWait().get() == ButtonType.OK) {

                allAppointments.remove(appointment);
                AppointmentsQuery.deleteAppointment(appointment.getApptID());

            }

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (allAppointments == null) {

        }

        if (allAppointments != null) {
            userNameLabel.setText(userName);
            userNameLabel.setVisible(true);
            this.apptID.setCellValueFactory(new PropertyValueFactory<Appointments, Integer>("apptID"));
            this.startTime.setCellValueFactory(new PropertyValueFactory<Appointments, Timestamp>("start"));

            appointmentsTable.setItems(allAppointments);

            this.customersNameColumn.setCellValueFactory(new PropertyValueFactory<Customers, String>("customerName"));
            this.customersAddressColumn.setCellValueFactory(new PropertyValueFactory<Customers, String>("address"));
            this.customersPhoneColumn.setCellValueFactory(new PropertyValueFactory<Customers, String>("phoneNumber"));

            customersTable.setItems(allCustomers);

        }

    }
}
