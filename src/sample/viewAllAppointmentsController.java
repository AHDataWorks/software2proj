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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
//import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.WeekFields;
import java.util.Locale;
import java.util.ResourceBundle;
import java.time.DayOfWeek;

public class viewAllAppointmentsController implements Initializable {

    Stage stage;

    @FXML
    private TableView<Appointments> appointmentTable;

    @FXML
    private Button doneButton;

    @FXML
    private RadioButton viewAllButton;

    @FXML
    private RadioButton viewByMonthButton;

    @FXML
    private RadioButton viewByWeekButton;

    @FXML
    private ToggleGroup viewGroup;

    @FXML
    private TableColumn<Appointments, Integer> aptIDColumn;

    @FXML
    private TableColumn<Appointments, Integer> contactColumn;

    @FXML
    private TableColumn<Appointments, Integer> customerIDColumn;

    @FXML
    private TableColumn<Appointments, String> descriptionColumn;

    @FXML
    private TableColumn<Appointments, String> endColumn;

    @FXML
    private TableColumn<Appointments, String> locationColumn;

    @FXML
    private TableColumn<Appointments, String> startColumn;

    @FXML
    private TableColumn<Appointments, Integer> userIDColumn;

    @FXML
    private TableColumn<Appointments, String> titleColumn;

    @FXML
    private TableColumn<Appointments, String> typeColumn;

    static public ObservableList<Appointments> allAppointmentsAllUsers = FXCollections.observableArrayList();

    static public ObservableList<Appointments> allAppointmentsAllUsersByWeek = FXCollections.observableArrayList();

    @FXML
    public static void initAllAppointmentsForAllUsers() throws SQLException {
        allAppointmentsAllUsers = AppointmentsQuery.allAppointmentsAllUsers();
    }

    @FXML
    public static void initCurrentWeek(){
        Locale locale = Locale.getDefault();
        LocalDateTime today = LocalDateTime.now();
//        LocalDate thisweek = today.getDayOfWeek();
        LocalDateTime weekToday = today.plusWeeks(1);
        DayOfWeek firstDayOfWeek = WeekFields.of(locale).getFirstDayOfWeek();
//        if(today.compareTo())
        System.out.println(weekToday);
    }

    @FXML
    public void doneButton(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("mainScene.fxml"));
//        AddProductController controller = fxmlLoader.getController();
//        controller.initAddProductData(imsInventory);

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void radioSwitch(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (allAppointmentsAllUsers != null) {

            this.aptIDColumn.setCellValueFactory(new PropertyValueFactory<Appointments, Integer>("apptID"));
            this.customerIDColumn.setCellValueFactory(new PropertyValueFactory<Appointments, Integer>("customerID"));
            this.typeColumn.setCellValueFactory(new PropertyValueFactory<Appointments, String>("type"));
            this.titleColumn.setCellValueFactory(new PropertyValueFactory<Appointments, String>("title"));
            this.descriptionColumn.setCellValueFactory(new PropertyValueFactory<Appointments, String>("description"));
            this.locationColumn.setCellValueFactory(new PropertyValueFactory<Appointments, String>("location"));
            this.contactColumn.setCellValueFactory(new PropertyValueFactory<Appointments, Integer>("contactID"));
            this.startColumn.setCellValueFactory(new PropertyValueFactory<Appointments, String>("start"));
            this.endColumn.setCellValueFactory(new PropertyValueFactory<Appointments, String>("end"));
            this.customerIDColumn.setCellValueFactory(new PropertyValueFactory<Appointments, Integer>("customerID"));
            this.userIDColumn.setCellValueFactory(new PropertyValueFactory<Appointments, Integer>("userID"));







//            this.startTime.setCellValueFactory(new PropertyValueFactory<Appointments, Timestamp>("start"));

            appointmentTable.setItems(allAppointmentsAllUsers);



        }
    }
}