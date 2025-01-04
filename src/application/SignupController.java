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
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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

    private Stage stage; // Reference to the current stage

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void handleBack() {
        try {
        	  FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
              Parent loginRoot = loader.load();

              // Get the LoginController instance
              LoginController loginController = loader.getController();
              loginController.setStage(stage); // Pass the same stage back to the login controller

              // Set the new scene for login
              Scene loginScene = new Scene(loginRoot, 500, 400);  // Same dimensions for login scene
              stage.setScene(loginScene);  // Switch to the login scene

              // Optionally, set the title for the login window
              stage.setTitle("Login - Quiz Application");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleSignup() {
        // Handle signup logic (e.g., validation, saving user data)
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

        // Proceed with saving user data to file or database (example logic)
        try {
            // For simplicity, let's write to a file (or replace with database logic)
            String userData = username + "," + password;
            // Save to a file or database here (e.g., users.txt)
//            File file = new File("C:\\Users\\DELL\\eclipse-workspace\\QuizApp\\src\\users.txt");
            File file = new File("users.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            writer.write(userData);
            writer.newLine(); // Add a new line after each entry
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
