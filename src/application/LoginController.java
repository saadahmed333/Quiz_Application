package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

import java.io.FileReader;
import java.io.BufferedReader;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label statusLabel;

    @FXML
    private Button loginButton;

    @FXML
    private Button signupButton;

    private Stage stage; // Reference to the current stage

    public void setStage(Stage stage) {
        this.stage = stage;
        System.out.println("Stage is set.");
    }

    @FXML
    public void handleLogin() {
//        String username = usernameField.getText();
//        String password = passwordField.getText();
    	 String username = "saad";
         String password = "123";

        if (username.isEmpty() || password.isEmpty()) {
            statusLabel.setText("Please fill in all fields.");
            return;
        }

        // File handling for user authentication
//        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\DELL\\eclipse-workspace\\QuizApp\\src\\users.txt"))) {
        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            boolean isAuthenticated = false;
            while ((line = reader.readLine()) != null) {
                String[] credentials = line.split(",");
                if (credentials.length == 2 && credentials[0].equals(username) && credentials[1].equals(password)) {
                    isAuthenticated = true;
                    break;
                }
            }

            if (isAuthenticated) {
                statusLabel.setText("Login successful!");
                // Navigate to the quiz screen (replace with your quiz screen logic)
                System.out.println("User logged in: " + username);
                loadQuizScreen(username);
            } else {
                statusLabel.setText("Invalid username or password.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            statusLabel.setText("An error occurred while logging in.");
        }
    }

    @FXML
    public void handleSignup() {
        try {
            // Check if the stage is null
            if (stage == null) {
                System.out.println("Stage is null. Make sure the setStage() method is called.");
                return;  // Exit if the stage is not initialized
            }

            // Load the Signup.fxml (assuming you have a signup FXML file)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Signup.fxml"));
            Parent signupRoot = loader.load();
            SignupController signupController = loader.getController();
            signupController.setStage(stage);
            // Set the new scene for signup
            Scene signupScene = new Scene(signupRoot, 500, 400);  // Same dimensions for signup scene
            stage.setScene(signupScene);  // Switch to the signup scene

            // Optionally, set the title for the signup window
            stage.setTitle("Signup - Quiz Application");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void loadQuizScreen(String username) {
        try {
            // Load Quiz screen (create your own Quiz.fxml)
        	 FXMLLoader loader = new FXMLLoader(getClass().getResource("Quiz.fxml"));
             Parent root = loader.load();

             // Get the controller of the loaded FXML
             QuizController quizController = loader.getController();

             // Pass the username to the QuizController
             quizController.setUserName(username);
            // Switch to the quiz screen within the same window
            stage.setScene(new Scene(root, 500, 400));
            stage.setTitle("Quiz - Quiz Application");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
