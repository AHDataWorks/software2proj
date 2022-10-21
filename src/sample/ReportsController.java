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
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ResourceBundle;

/**
 * <Code>ReportsController</Code> actas as the controller for reports.fxml
 * @author Andrew Hobbs
 */

public class ReportsController implements Initializable {

    Stage stage;

    @FXML
    private ComboBox<String> contactComboBox;

    @FXML
    private TableView<Appointments> contactTable;

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
    private ComboBox<String> userComboBox;

    @FXML
    private TableView<Appointments> userTable;

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

    @FXML
    private RadioButton typeMonthButton;

    @FXML
    private RadioButton userButton;

    @FXML
    private TableColumn<Appointments, String> apptIDColumnContact;

    @FXML
    private TableColumn<Appointments, Integer> customerIDColumnContact;

    @FXML
    private TableColumn<Appointments, String> descriptionColumnContact;

    @FXML
    private TableColumn<Appointments, Timestamp> endColumnContact;

    @FXML
    private TableColumn<Appointments, Timestamp> startColumnContact;

    @FXML
    private TableColumn<Appointments, String> titleColumnContact;

    @FXML
    private TableColumn<Appointments, String> typeColumnContact;

    @FXML
    private TableColumn<Appointments, String> apptIDColumnUser;

    @FXML
    private TableColumn<Appointments, Integer> customerIDColumnUser;

    @FXML
    private TableColumn<Appointments, String> descriptionColumnUser;

    @FXML
    private TableColumn<Appointments, Timestamp> endColumnUser;

    @FXML
    private TableColumn<Appointments, Timestamp> startColumnUser;

    @FXML
    private TableColumn<Appointments, String> titleColumnUser;

    @FXML
    private TableColumn<Appointments, String> typeColumnUser;

    static public ObservableList<Appointments> allAppointmentsAllUsers = FXCollections.observableArrayList();

    static ObservableList<String> allMonthsForComboBox = FXCollections.observableArrayList();

    static ObservableList<String> allContactsForComboBox = FXCollections.observableArrayList();

    static ObservableList<String> allUsersForComboBox = FXCollections.observableArrayList();

    static public ObservableList<Appointments> allAppointmentsAllUsersByMonth = FXCollections.observableArrayList();

    static public ObservableList<Appointments> allAppointmentsAllUsersByContact = FXCollections.observableArrayList();

    static public ObservableList<Appointments> allAppointmentsAllUsersByUser = FXCollections.observableArrayList();

    static int userID;

    static int runningDebriefingcount = 0;

    static int runningPlanningSessionCount = 0;

    static int selectedContactID = 0;

    static int selectedUserID = 0;

    /**
     * initAllAppointmentsAllUsersByUser initiates an observable list that will be used to store appointments that correspond to the selected user in the combobox.
     */
    @FXML
    public static void initAllAppointmentsAllUsersByUser() {
        allAppointmentsAllUsersByUser.clear();
        for (Appointments appointment : allAppointmentsAllUsers) {
            Integer currentContactID = appointment.getUserID();
            if(currentContactID.equals(selectedUserID)) {
                allAppointmentsAllUsersByUser.add(appointment);
            }
        }
    }

    /**
     * initSelectedUserID accepts the string selected in the combo box and converts it to a corresponding int.
     * @param selectedUser String value of the option selected in the combo box.
     */
    @FXML
    public static void initSelectedUserID(String selectedUser) {

        if(selectedUser.equals("test")) {
            selectedUserID = 1;
        } else if (selectedUser.equals("admin")) {
            selectedUserID = 2;
        }
    }

    /**
     * initAllUsers adds the known list of users to the combobox to be selected. *NOTE this could be more dynamic by querying the server for a known list of users in the future.
     */
    @FXML
    public static void initAllUsers() {
        allUsersForComboBox.add("test");
        allUsersForComboBox.add("admin");

    }

    /**
     * initAllAppointmentsAllUsersByContact initiates an observable list that will be used to store appointments that correspond to the selected contact in the combobox.
     */
    @FXML
    public static void initAllAppointmentsAllUsersByContact() {
        allAppointmentsAllUsersByContact.clear();
        for (Appointments appointment : allAppointmentsAllUsers) {
            Integer currentContactID = appointment.getContactID();
            if(currentContactID.equals(selectedContactID)) {
                allAppointmentsAllUsersByContact.add(appointment);
                System.out.println("This  contact is: " + appointment.getContactID());
            }
        }
    }

    /**
     * initSelectedContactID accepts the string selected in the combo box and converts it to a corresponding int.
     * @param selectedContact - String value of the selected contact in the combo box.
     */
    @FXML
    public static void initSelectedContactID(String selectedContact) {

        if(selectedContact.equals("Anika Costa")) {
            selectedContactID = 1;
        } else if (selectedContact.equals("Daniel Garcia")) {
            selectedContactID = 2;
        } else if (selectedContact.equals("Li Lee")) {
            selectedContactID = 3;
        }
    }

    /**
     * initAllContacts adds the stored contacts into the observable list used for the combo box.
     */
    @FXML
    public static void initAllContacts() {
        allContactsForComboBox.add("Anika Costa");
        allContactsForComboBox.add("Daniel Garcia");
        allContactsForComboBox.add("Li Lee");

    }

    /**
     * countTypesByMonth performs a count on the ObservableList of appointments and keeps a running count of the types of each.
     */
    @FXML
    public static void countTypesByMonth() {
        runningDebriefingcount = 0;
        runningPlanningSessionCount = 0;
        for (Appointments appointment : allAppointmentsAllUsersByMonth) {
            String currentType = appointment.getType();
            if (currentType.equals("Planning Session")) {
                runningPlanningSessionCount += 1;
            } else if (currentType.equals("De-Briefing")) {
                runningDebriefingcount += 1;
            }
        }
    }

    /**
     * initUserID accepts an int and sets it as the current user ID.
     * @param currUserID an int corresponding to the current user's id, passed in from the previous scene controller.
     */
    @FXML
    public static void initUserID(int currUserID) {
        userID = currUserID;
    }

    /**
     * comboBoxMonthToNumber accepts the selected month as a String and returns a numerical string that can be parsed into our sql command.
     * @param monthSelected String value of the month selected in the combo box.
     * @return
     */
    public static String comboBoxMonthToNumber(String monthSelected) {
        String monthNumber = null;
        if (monthSelected.equals("January")) {
            monthNumber = "1";
        } else if (monthSelected.equals("February")) {
            monthNumber = "2";
        } else if (monthSelected.equals("March")) {
            monthNumber = "3";
        } else if (monthSelected.equals("April")) {
            monthNumber = "4";
        } else if (monthSelected.equals("May")) {
            monthNumber = "5";
        } else if (monthSelected.equals("June")) {
            monthNumber = "6";
        } else if (monthSelected.equals("July")) {
            monthNumber = "7";
        } else if (monthSelected.equals("August")) {
            monthNumber = "8";
        } else if (monthSelected.equals("September")) {
            monthNumber = "9";
        } else if (monthSelected.equals("October")) {
            monthNumber = "10";
        } else if (monthSelected.equals("November")) {
            monthNumber = "11";
        } else if (monthSelected.equals("December")) {
            monthNumber = "12";
        }

        return monthNumber;
    }

    /**
     * initAllAppointmentsForAllUsers queries the database and saves all of the appointments for all users in an observableList.
     * @throws SQLException
     */
    @FXML
    public static void initAllAppointmentsForAllUsers() throws SQLException {
        allAppointmentsAllUsers = AppointmentsQuery.allAppointmentsAllUsers();
    }

    /**
     * allAppointmentsAllUsersByMonthQuery accepts a String value of the month selected in the combo box and queries the database for appointments corresponding to this month.
     * @param monthSelected String value of the month selected in the combo box.
     * @throws SQLException
     */
    @FXML
    public static void allAppointmentsAllUsersByMonthQuery(String monthSelected) throws SQLException{
        allAppointmentsAllUsersByMonth = AppointmentsQuery.allAppointmentsAllUsersByMonth(comboBoxMonthToNumber(monthSelected));
    }

    /**
     * initMonthsForComboBox adds values to the observableList used in the combo box of months.
     */
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


    /**
     * switchToMain switches the user to mainScene.fxml
     * @param event user click.
     * @throws SQLException
     * @throws IOException
     */
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

    /**
     * switchToViewAllAppointments switches the user to viewAllAppointments.fxml
     * @param event - user click.
     * @throws IOException
     * @throws SQLException
     */
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

    /**
     * Initialize contains the Lambda functions used to populate the tables based on the user selection.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        monthComboBox.setItems(allMonthsForComboBox);
        contactComboBox.setItems(allContactsForComboBox);
        userComboBox.setItems(allUsersForComboBox);


        typeMonthButton.setOnAction(e -> {
            userTable.setVisible(false);
            userComboBox.setVisible(false);
            contactTable.setVisible(false);
            contactComboBox.setVisible(false);

            contactTable.setVisible(false);
            contactComboBox.setVisible(false);

            monthLabel.setVisible(true);
            monthComboBox.setVisible(true);
            debriefingsLabel.setVisible(true);
            planningSessionLabel.setVisible(true);
            debriefingCount.setVisible(true);
            planningSessionCount.setVisible(true);

            System.out.println("ping typemonth");
        });

        monthComboBox.setOnAction(p -> {
            String selectedMonth = monthComboBox.getValue();
            try {
                allAppointmentsAllUsersByMonthQuery(selectedMonth);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            monthLabel.setText(selectedMonth + "'s Appointments:");
            debriefingCount.setVisible(true);
            planningSessionCount.setVisible(true);
            countTypesByMonth();
            debriefingCount.setText(String.valueOf(runningDebriefingcount));
            planningSessionCount.setText(String.valueOf(runningPlanningSessionCount));

        });

        contactButton.setOnAction(e -> {
            monthLabel.setVisible(false);
            monthComboBox.setVisible(false);
            debriefingCount.setVisible(false);
            debriefingsLabel.setVisible(false);
            planningSessionCount.setVisible(false);
            planningSessionLabel.setVisible(false);

            userTable.setVisible(false);
            userComboBox.setVisible(false);

            contactTable.setVisible(true);
            contactComboBox.setVisible(true);

            System.out.println("ping");

        });

        contactComboBox.setOnAction(p -> {
            String selectedContact = contactComboBox.getValue();
            initSelectedContactID(selectedContact);
            initAllAppointmentsAllUsersByContact();
            System.out.println(selectedContact);
            apptIDColumnContact.setCellValueFactory(new PropertyValueFactory<Appointments, String>("apptID"));
            titleColumnContact.setCellValueFactory(new PropertyValueFactory<Appointments, String>("title"));
            typeColumnContact.setCellValueFactory(new PropertyValueFactory<Appointments, String>("type"));
            descriptionColumnContact.setCellValueFactory(new PropertyValueFactory<Appointments, String>("description"));
            startColumnContact.setCellValueFactory(new PropertyValueFactory<Appointments, Timestamp>("start"));
            endColumnContact.setCellValueFactory(new PropertyValueFactory<Appointments, Timestamp>("end"));
            customerIDColumnContact.setCellValueFactory(new PropertyValueFactory<Appointments, Integer>("customerID"));

            contactTable.setItems(allAppointmentsAllUsersByContact);

        });

        userButton.setOnAction(e -> {
            monthLabel.setVisible(false);
            monthComboBox.setVisible(false);
            debriefingCount.setVisible(false);
            debriefingsLabel.setVisible(false);
            planningSessionCount.setVisible(false);
            planningSessionLabel.setVisible(false);

            contactTable.setVisible(false);
            contactComboBox.setVisible(false);

            userTable.setVisible(true);
            userComboBox.setVisible(true);
            System.out.println("ping user");
        });

        userComboBox.setOnAction(p -> {
            String selectedUser = userComboBox.getValue();
            initSelectedUserID(selectedUser);
            initAllAppointmentsAllUsersByUser();


            apptIDColumnUser.setCellValueFactory(new PropertyValueFactory<Appointments, String>("apptID"));
            titleColumnUser.setCellValueFactory(new PropertyValueFactory<Appointments, String>("title"));
            typeColumnUser.setCellValueFactory(new PropertyValueFactory<Appointments, String>("type"));
            descriptionColumnUser.setCellValueFactory(new PropertyValueFactory<Appointments, String>("description"));
            startColumnUser.setCellValueFactory(new PropertyValueFactory<Appointments, Timestamp>("start"));
            endColumnUser.setCellValueFactory(new PropertyValueFactory<Appointments, Timestamp>("end"));
            customerIDColumnUser.setCellValueFactory(new PropertyValueFactory<Appointments, Integer>("customerID"));

            userTable.setItems(allAppointmentsAllUsersByUser);

        });

    }
}
