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
import javafx.util.Callback;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.IOException;
import java.net.URL;
//import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.*;

public class AddAppointmentController implements Initializable {
    @FXML
    private TextField appointmentIDText;

    @FXML
    private Button cancelButton;

    @FXML
    private ComboBox<String> contactComboBox;

    @FXML
    private Label contactError;

    @FXML
    private Label customerIDError;

    @FXML
    private TextField customerIDText;

    @FXML
    private Label descriptionError;

    @FXML
    private TextArea descriptionFieldText;

    @FXML
    private Label endDateError;

    @FXML
    private DatePicker endDateText;

    @FXML
    private ComboBox<String> endTimeComboBox;

    @FXML
    private Label endTimeError;

    @FXML
    private Label errorTextLabel;

    @FXML
    private Label locationError;

    @FXML
    private TextField locationText;

    @FXML
    private Label startDateError;

    @FXML
    private DatePicker startDateText;

    @FXML
    private ComboBox<String> startTimeComboBox;

    @FXML
    private Label startTimeError;

    @FXML
    private Button submitButton;

    @FXML
    private Label titleError;

    @FXML
    private TextField titleText;

    @FXML
    private Label userIDError;

    @FXML
    private TextField userIDText;

    @FXML
    private ComboBox<String> typeComboBox;

    @FXML
    private Label typeError;

    Stage stage;

    static ObservableList<Appointments> allAppointments;

    static ObservableList<Contacts> allContacts = FXCollections.observableArrayList();

    static ObservableList<String> allContactNames = FXCollections.observableArrayList();

    static ObservableList<String> allTimesForComboBox = FXCollections.observableArrayList();

    static ObservableList<String> allTypesForComboBox = FXCollections.observableArrayList();



    public static int userID;

    @FXML
    public static void initUserID(int userIDNumber) {
        userID = userIDNumber;
    }

    @FXML
    public static void initAllAppts(ObservableList allApts) {
        allAppointments = allApts;
    }

    @FXML
    public static void initAllTypes() throws SQLException {
        allTypesForComboBox = AppointmentsQuery.allTypes();
    }

    @FXML
    public static void initApptTimes() {
        allTimesForComboBox.add("12:00 AM");
        allTimesForComboBox.add("1:00 AM");
        allTimesForComboBox.add("2:00 AM");
        allTimesForComboBox.add("3:00 AM");
        allTimesForComboBox.add("4:00 AM");
        allTimesForComboBox.add("5:00 AM");
        allTimesForComboBox.add("6:00 AM");
        allTimesForComboBox.add("7:00 AM");
        allTimesForComboBox.add("8:00 AM");
        allTimesForComboBox.add("9:00 AM");
        allTimesForComboBox.add("10:00 AM");
        allTimesForComboBox.add("11:00 AM");
        allTimesForComboBox.add("12:00 PM");
        allTimesForComboBox.add("1:00 PM");
        allTimesForComboBox.add("2:00 PM");
        allTimesForComboBox.add("3:00 PM");
        allTimesForComboBox.add("4:00 PM");
        allTimesForComboBox.add("5:00 PM");
        allTimesForComboBox.add("6:00 PM");
        allTimesForComboBox.add("7:00 PM");
        allTimesForComboBox.add("8:00 PM");
        allTimesForComboBox.add("9:00 PM");
        allTimesForComboBox.add("10:00 PM");
        allTimesForComboBox.add("11:00 PM");
    }

    @FXML
    public static String timeConverter(String comboBoxTime) {
        String result = null;
        if (comboBoxTime.equals("12:00 AM")){
            result = "00:00:00";
        }
        if (comboBoxTime.equals("1:00 AM")){
            result = "01:00:00";
        }
        if (comboBoxTime.equals("2:00 AM")){
            result = "02:00:00";
        }
        if (comboBoxTime.equals("3:00 AM")){
            result = "03:00:00";
        }
        if (comboBoxTime.equals("4:00 AM")){
            result = "04:00:00";
        }
        if (comboBoxTime.equals("5:00 AM")){
            result = "05:00:00";
        }
        if (comboBoxTime.equals("6:00 AM")){
            result = "06:00:00";
        }
        if (comboBoxTime.equals("7:00 AM")){
            result = "07:00:00";
        }
        if (comboBoxTime.equals("8:00 AM")){
            result = "08:00:00";
        }
        if (comboBoxTime.equals("9:00 AM")){
            result = "09:00:00";
        }
        if (comboBoxTime.equals("10:00 AM")){
            result = "10:00:00";
        }
        if (comboBoxTime.equals("11:00 AM")){
            result = "11:00:00";
        }
        if (comboBoxTime.equals("12:00 PM")){
            result = "12:00:00";
        }
        if (comboBoxTime.equals("1:00 PM")){
            result = "13:00:00";
        }
        if (comboBoxTime.equals("2:00 PM")){
            result = "14:00:00";
        }
        if (comboBoxTime.equals("3:00 PM")){
            result = "15:00:00";
        }
        if (comboBoxTime.equals("4:00 PM")){
            result = "16:00:00";
        }
        if (comboBoxTime.equals("5:00 PM")){
            result = "17:00:00";
        }
        if (comboBoxTime.equals("6:00 PM")){
            result = "18:00:00";
        }
        if (comboBoxTime.equals("7:00 PM")){
            result = "19:00:00";
        }
        if (comboBoxTime.equals("8:00 PM")){
            result = "20:00:00";
        }
        if (comboBoxTime.equals("9:00 PM")){
            result = "21:00:00";
        }
        if (comboBoxTime.equals("10:00 PM")){
            result = "22:00:00";
        }
        if (comboBoxTime.equals("11:00 PM")){
            result = "23:00:00";
        }
        return result;
    }

    @FXML
    public static Timestamp dateTimeFormatter(LocalTime lt,LocalDate ld) {
        String formattedString = null;
        ZoneId setZoneID = ZoneId.of("UTC");
        LocalDateTime mldt = LocalDateTime.of(ld,lt);
        ZoneId mzid = ZoneId.systemDefault();
        ZoneId utcZoneID = ZoneId.of("UTC");
        ZonedDateTime myZDT = ZonedDateTime.of(mldt,utcZoneID);


        formattedString = myZDT.toLocalDate().toString() + " " + lt +":00";
//        System.out.println(formattedString);
        return Timestamp.valueOf(formattedString);
    }

    @FXML
    public static void initAllContacts() throws SQLException {
        allContacts = ContactsQuery.allContacts();
        allContactNames = ContactsQuery.allContactNames();
    }

    @FXML
    void cancelButton(ActionEvent event) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("mainScene.fxml"));
        MainSceneController controller = fxmlLoader.getController();
        controller.initAllAppts(allAppointments);
        controller.initUserID(userID);
        controller.initAllCustomers();

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    public static Date convertToDate(LocalDate date, ZoneId zoneId) {
        return Date.from(date
                .atStartOfDay().atZone(
                        zoneId)
                .toInstant());
    }

    public static boolean timeCheck(Timestamp startDateTime, Timestamp endDateTime, Timestamp ts) throws ParseException {
        boolean result = true;

        Calendar calendar = Calendar.getInstance();
        Calendar easternTime = new GregorianCalendar(TimeZone.getTimeZone("EST"));
        DayOfWeek sunday = DayOfWeek.SUNDAY;
        DayOfWeek saturday = DayOfWeek.SATURDAY;

        if (endDateTime.before(startDateTime)) {
            result = false;
        }

        if (startDateTime.after(ts)) {
            result = false;
        }

        DateFormat tzFormat = new SimpleDateFormat("yyyy-MM-dd");
        tzFormat.setTimeZone(TimeZone.getTimeZone("EST"));

        tzFormat.parse(startDateTime.toString());

//        Date
//
//
//        if (tzFormat. == sunday)


        return result;
    }

    @FXML
    void submitButton(ActionEvent event) throws IOException, SQLException, ParseException {
        boolean passCheck = false;

        errorTextLabel.setVisible(false);
        titleError.setVisible(false);
        locationError.setVisible(false);
        contactError.setVisible(false);
        startDateError.setVisible(false);
        startTimeError.setVisible(false);
        endDateError.setVisible(false);
        endTimeError.setVisible(false);
        customerIDError.setVisible(false);
        userIDError.setVisible(false);
        descriptionError.setVisible(false);
        typeError.setVisible(false);

        String title = titleText.getText();
        String location = locationText.getText();
//        String contact = contactComboBox.getValue().toString();
//        LocalDate startDate = startDateText.getValue();
//        String startTime = startTimeComboBox.getValue().toString();
//        LocalDate endDate = endDateText.getValue();
//        String endTime = endTimeComboBox.getValue().toString();
        String type = typeComboBox.getValue();
        String customerID = customerIDText.getText();
        String userIDNew = userIDText.getText();
        String descriptionText = descriptionFieldText.getText();

        if (title.equals("")) {
            titleError.setVisible(true);
            errorTextLabel.setVisible(true);
            errorTextLabel.setText("* Required Field");
        }

        if (location.equals("")) {
            locationError.setVisible(true);
            errorTextLabel.setVisible(true);
            errorTextLabel.setText("* Required Field");
        }

        if (contactComboBox.getValue() == null) {
            contactError.setVisible(true);
            errorTextLabel.setVisible(true);
            errorTextLabel.setText("* Required Field");
        }

        if (startDateText.getValue() == null) {
            startDateError.setVisible(true);
            errorTextLabel.setVisible(true);
            errorTextLabel.setText("* Required Field");
        }

        if (startTimeComboBox.getValue() == null) {
            startTimeError.setVisible(true);
            errorTextLabel.setVisible(true);
            errorTextLabel.setText("* Required Field");
        }

        if (endDateText.getValue() == null) {
            endDateError.setVisible(true);
            errorTextLabel.setVisible(true);
            errorTextLabel.setText("* Required Field");
        }

        if (endTimeComboBox.getValue() == null) {
            endTimeError.setVisible(true);
            errorTextLabel.setVisible(true);
            errorTextLabel.setText("* Required Field");
        }

        if (customerID.equals("")) {
            customerIDError.setVisible(true);
            errorTextLabel.setVisible(true);
            errorTextLabel.setText("* Required Field");
        }

        if (userIDNew.equals("")) {
            userIDError.setVisible(true);
            errorTextLabel.setVisible(true);
            errorTextLabel.setText("* Required Field");
        }

        if (descriptionText.equals("")) {
            descriptionError.setVisible(true);
            errorTextLabel.setVisible(true);
            errorTextLabel.setText("* Required Field");
        }

        if (typeComboBox.getValue() == null){
            typeError.setVisible(true);
            errorTextLabel.setVisible(true);
            errorTextLabel.setText("* Required Field");
        }

//        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

        Date date = new Date();
        long time = date.getTime();
        Timestamp ts = new Timestamp(time);

//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

        String testTime = timeConverter(startTimeComboBox.getValue());
        String endTime = timeConverter(endTimeComboBox.getValue());
        LocalTime ltStart = LocalTime.parse(testTime);
        LocalTime ltEnd = LocalTime.parse(endTime);
        LocalDate ldStart = startDateText.getValue();
        LocalDate ldEnd = endDateText.getValue();
        OffsetDateTime dateTime = OffsetDateTime.now();

        ZoneId mzid = ZoneId.systemDefault();

//        LocalDateTime startLocalDateTime = LocalDateTime.of(ldStart,ltStart);
////        ZonedDateTime startZDT = ZonedDateTime.of(startLocalDateTime,mzid);
////        ZoneId utcZoneID = ZoneId.of("UTC");
////        ZonedDateTime startUtcZDT = ZonedDateTime.ofInstant(startZDT.toInstant(), utcZoneID);
////
////        LocalDateTime endLocalDateTime = LocalDateTime.of(ldEnd,ltEnd);
////        ZonedDateTime endZDT = ZonedDateTime.of(endLocalDateTime,mzid);
////        ZonedDateTime endUtcZDT = ZonedDateTime.ofInstant(endZDT.toInstant(), utcZoneID);
////
////        System.out.println(startUtcZDT);
////        System.out.println(endUtcZDT);

        Timestamp startDateTime = dateTimeFormatter(ltStart,ldStart);
        Timestamp endDateTime = dateTimeFormatter(ltEnd,ldEnd);

//        boolean timeCheckPassCheck = timeCheck(startDateTime,endDateTime,ts);
//        System.out.println(startDateTime);






        String userName = UsersQuery.getUserName(userID);

        String contactName = contactComboBox.getValue();
        int contactID = ContactsQuery.getContactID(contactName);

        int realCustomerID = Integer.parseInt(customerID);


        if (!title.equals("")
                && !location.equals("")
                && contactComboBox.getValue() != null
                && startDateText.getValue() != null
                && startTimeComboBox.getValue() != null
                && endDateText.getValue() != null
                && endTimeComboBox.getValue() != null
                && !customerID.equals("")
                && !userIDNew.equals("")
                && !descriptionText.equals("")
                && typeComboBox.getValue() != null) {
            passCheck = true;
        }

        if (passCheck == true) {
            System.out.println(contactID);
            AppointmentsQuery.addAppointment(title,descriptionText,location,type,startDateTime,endDateTime,ts,userName,ts,userName,realCustomerID,userID,contactID);
            allAppointments = AppointmentsQuery.allAppointmentsByUserID(userID);
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("mainScene.fxml"));
            MainSceneController controller = fxmlLoader.getController();
            controller.initAllAppts(allAppointments);
            controller.initUserID(userID);
            controller.initAllCustomers();

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        contactComboBox.setItems(allContactNames);
        startTimeComboBox.setItems(allTimesForComboBox);
        endTimeComboBox.setItems(allTimesForComboBox);
        typeComboBox.setItems(allTypesForComboBox);

    }
}
