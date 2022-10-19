package sample;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.TimeZone;



public class LoginController implements Initializable {

    Stage stage;

    @FXML
    private Label regionLabel;

    @FXML
    private Label regionResult;

    @FXML
    private Button loginButton;

    @FXML
    private Label loginLabel;

    @FXML
    private Label passwordLabel;

    @FXML
    private Label userIDLabel;

    @FXML
    private Label errorMessageLabel;

    @FXML
    private TextField userIDText;

    @FXML
    private TextField passwordText;


    ObservableList<Appointments> allAppts;

    public BufferedWriter buffWriter = null;

    String record = "testing";

    public void initFilewriter(String record) {
        try {
            buffWriter = new BufferedWriter(new FileWriter(new File(" login_activity.txt"),true));
            buffWriter.write(record);
            buffWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void loginSubmit(ActionEvent event) throws IOException, SQLException {
        Timestamp timeStampFinal = Timestamp.from(ZonedDateTime.now().toInstant());
        errorMessageLabel.setText("");
        errorMessageLabel.setVisible(false);
        Boolean passCheck = false;

        if (userIDText.getText() == null || userIDText.getText().equals("")) {
            errorMessageLabel.setVisible(true);
            errorMessageLabel.setText("User ID is Required.");
        }

        if (passwordText.getText() == null || passwordText.getText().equals("")) {
            errorMessageLabel.setVisible(true);
            errorMessageLabel.setText("Password is Required");
        }

        if (userIDText.getText().equals("") && passwordText.getText().equals("")) {
            errorMessageLabel.setVisible(true);
            errorMessageLabel.setText("User ID and Password are Required.");
        }

        if (!userIDText.getText().equals("") && !passwordText.getText().equals("")) {
            passCheck = true;
        }

        if (passCheck == true) {
            try {
                int userID = UsersQuery.getUserID(userIDText.getText(), passwordText.getText());

                System.out.print(userID);
                if (userID > 0) {
                    record = "\n" + timeStampFinal + " ||| Successfully logged into user account: " + userIDText.getText() + ", UserID: " + userID + " at " + ZoneId.systemDefault();
                    initFilewriter(record);
                    allAppts = AppointmentsQuery.allAppointmentsByUserID(userID);

                    FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("mainScene.fxml"));
                    MainSceneController controller = fxmlLoader.getController();
                    controller.initAllAppts(allAppts);
                    controller.initUserID(userID);
                    controller.initAllCustomers();
                    controller.initUpcomingApptCheck();
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Scene scene = new Scene(fxmlLoader.load());
                    stage.setScene(scene);
                    stage.show();
                } else if (userID == 0) {
                    record = "\n" + timeStampFinal + " ||| Failed attempt to login to user account: " + userIDText.getText() + " at " + ZoneId.systemDefault();
                    initFilewriter(record);
                    userIDText.setText("");
                    passwordText.setText("");
                    errorMessageLabel.setVisible(true);
                    errorMessageLabel.setText("Incorrect User ID or Password.");
                }
            } catch (SQLException ex) {
                record = "\n" + timeStampFinal + " ||| Failed attempt to login to user account: " + userIDText.getText() + " at " + ZoneId.systemDefault();
                initFilewriter(record);
                userIDText.setText("");
                passwordText.setText("");
                errorMessageLabel.setVisible(true);
                errorMessageLabel.setText("Incorrect User ID or Password.");
            }
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String zoneID = TimeZone.getDefault().getID();
        regionResult.setText(zoneID);
        if (Locale.getDefault().getLanguage().equals("fr")) {
            loginLabel.setText("connectez-vous");
            userIDLabel.setText("Identifiant");
            passwordLabel.setText("mot de passe");
            loginButton.setText("Envoyer");
            errorMessageLabel.setText("Id utilisateur ou mot de passe incorrect");
            regionLabel.setText("Région:");
            

        }
        else {
//            System.out.print(Locale.getDefault().getLanguage());
        }
    }
}
