package sample;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ReportsController implements Initializable {

    Stage stage;

    @FXML
    private ComboBox<?> contactComboBox;

    @FXML
    private TableView<?> contactTable;

    @FXML
    private Button doneButton;

    @FXML
    private ComboBox<String> monthComboBox;

    @FXML
    private Label monthLabel;

    @FXML
    private Label planningSessionCount;

    @FXML
    private ToggleGroup reportGroup;

    @FXML
    private ComboBox<?> userComboBox;

    @FXML
    private TableView<?> userTable;

    @FXML
    private Label planningSessionLabel;

    @FXML
    private Label debriefingsLabel;

    @FXML
    private Label debriefingCount;

    @FXML
    private Button viewAllAppointmentsButton;

    @FXML
    private RadioButton contactButton;



    static public ObservableList<Appointments> allAppointmentsAllUsers = FXCollections.observableArrayList();

    static ObservableList<String> allMonthsForComboBox = FXCollections.observableArrayList();


    static int userID;


    @FXML
    public static void initUserID(int currUserID) {
        userID = currUserID;
    }

    @FXML
    public static void initAllAppointmentsForAllUsers() throws SQLException {
        allAppointmentsAllUsers = AppointmentsQuery.allAppointmentsAllUsers();

    }

    @FXML
    public static void initMonthsForComboBox() {
        allMonthsForComboBox.add("January");
        allMonthsForComboBox.add("February");
        allMonthsForComboBox.add("March");
        allMonthsForComboBox.add("April");
        allMonthsForComboBox.add("May");
        allMonthsForComboBox.add("June");
        allMonthsForComboBox.add("July");
        allMonthsForComboBox.add("August");
        allMonthsForComboBox.add("September");
        allMonthsForComboBox.add("October");
        allMonthsForComboBox.add("November");
        allMonthsForComboBox.add("December");

    }


    @FXML
    void contactRadioSelected(ActionEvent event) {

    }

    @FXML
    void typeMonthRadioSelected(ActionEvent event) {

    }

    @FXML
    void userRadioSelected(ActionEvent event) {

    }




    @FXML
    void switchToMain(ActionEvent event) throws SQLException, IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("mainScene.fxml"));
        MainSceneController controller = fxmlLoader.getController();
//        controller.initAllAppts(allAppointments);
        controller.initUserID(userID);
        controller.initAllCustomers();

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    void switchToViewAllAppointments(ActionEvent event) throws IOException, SQLException {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("viewAllAppointments.fxml"));
        viewAllAppointmentsController controller = fxmlLoader.getController();
        controller.initUserID(userID);
        controller.initAllAppointmentsForAllUsers();
        controller.initCurrentWeek();


        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        monthComboBox.setItems(allMonthsForComboBox);
    }
}
