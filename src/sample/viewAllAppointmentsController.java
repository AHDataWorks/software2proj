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

    @FXML
    private Label debriefingsCount;

    @FXML
    private Label planningSessionCount;

    @FXML
    private Label appointmentCount;

    static public ObservableList<Appointments> allAppointmentsAllUsers = FXCollections.observableArrayList();

    static public ObservableList<Appointments> allAppointmentsAllUsersByWeek = FXCollections.observableArrayList();

    static public ObservableList<Appointments> allAppointmentsAllUsersByMonth = FXCollections.observableArrayList();

    static int allApptsCount;

    static int userID;

    static int pSessionCount = 0;

    static int debrieffingCount = 0;

    @FXML
    public static void initUserID(int currUserID) {
        userID = currUserID;
    }

    @FXML
    public static void initAllAppointmentsForAllUsers() throws SQLException {
        allAppointmentsAllUsers = AppointmentsQuery.allAppointmentsAllUsers();
        allAppointmentsAllUsersByWeek = AppointmentsQuery.allAppointmentsByUserIDByWeek(userID);
        allAppointmentsAllUsersByMonth = AppointmentsQuery.allAppointmentsByUserIDByMonth(userID);
        allApptsCount = allAppointmentsAllUsers.size();
        for (Appointments appointment : allAppointmentsAllUsers) {
            String type = appointment.getType();
            if (type.equals("Planning Session")) {
                pSessionCount = pSessionCount + 1;
            } else if (type.equals("De-Briefing")) {
                debrieffingCount = debrieffingCount + 1;
            }
        }
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
    void switchToReports(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("reports.fxml"));
        ReportsController controller = fxmlLoader.getController();
//        controller.initAddProductData(imsInventory);
        controller.initMonthsForComboBox();

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void radioSwitch(ActionEvent event) {
        if(viewByMonthButton.isSelected()) {
            System.out.println("ping month");

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

            appointmentTable.setItems(allAppointmentsAllUsersByMonth);

        } else if (viewByWeekButton.isSelected()) {
            System.out.println("ping week");

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

            appointmentTable.setItems(allAppointmentsAllUsersByWeek);
        } else if (viewAllButton.isSelected()) {
            System.out.println("ping all");

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

                appointmentTable.setItems(allAppointmentsAllUsers);

            }

        } else {
            viewAllButton.setSelected(true);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        appointmentCount.setText(String.valueOf(allApptsCount));
        debriefingsCount.setText(String.valueOf(debrieffingCount));
        planningSessionCount.setText(String.valueOf(pSessionCount));

        if(viewByMonthButton.isSelected()) {
            System.out.println("ping month");

        } else if (viewByWeekButton.isSelected()) {
            System.out.println("ping week");
        } else if (viewAllButton.isSelected()) {
            System.out.println("ping all");

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

                appointmentTable.setItems(allAppointmentsAllUsers);

            }

        } else {
            viewAllButton.setSelected(true);
        }

    }
}