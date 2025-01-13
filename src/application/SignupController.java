package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class SignupController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Label statusLabel;

    @FXML
    private Button signupButton;

    @FXML
    private Button backButton;

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void handleBack() {
        try {
        	  FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
              Parent loginRoot = loader.load();
              LoginController loginController = loader.getController();
              loginController.setStage(stage);
              Scene loginScene = new Scene(loginRoot, 500, 400);
              stage.setScene(loginScene);
              stage.setTitle("Login - Quiz Application");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleSignup() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            statusLabel.setText("Please fill in all fields.");
            return;
        }

        if (!password.equals(confirmPassword)) {
            statusLabel.setText("Passwords do not match.");
            return;
        }

        try {
            String userData = username + "," + password;
//            File file = new File("C:\\Users\\DELL\\eclipse-workspace\\QuizApp\\src\\users.txt");
            File file = new File("users.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            writer.write(userData);
            writer.newLine();
            writer.close();
            System.out.println("User data saved successfully.");
            statusLabel.setText("Signup successful!");
            System.out.println("User signed up: " + username);
        } catch (Exception e) {
            e.printStackTrace();
            statusLabel.setText("An error occurred during signup.");
        }
    }
}
