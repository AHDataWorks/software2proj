package sample;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
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

    @FXML
    public void loginSubmit(ActionEvent event) throws IOException, SQLException {
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

            int userID = UsersQuery.getUserID(userIDText.getText(),passwordText.getText());

            System.out.print(userID);
            if (userID > 0) {
                allAppts = AppointmentsQuery.allAppointmentsByUserID(userID);

                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("mainScene.fxml"));
                MainSceneController controller = fxmlLoader.getController();
                controller.initAllAppts(allAppts);
                controller.initUserID(userID);
                controller.initAllCustomers();
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(fxmlLoader.load());
                stage.setScene(scene);
                stage.show();
            } else if (userID == 0) {
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
