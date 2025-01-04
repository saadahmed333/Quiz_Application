package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.collections.FXCollections;

public class QuizController {

    @FXML
    private Label userNameLabel; // Label to display user name
    @FXML
    private ComboBox<String> subjectComboBox; // ComboBox for subject selection
    @FXML
    private Label statusLabel; // Label for status messages

    private String userName; // Sample user name, replace with actual logged-in user
    
    public void setUserName(String loggedInUserName) {
    	this.userName = loggedInUserName;
        userNameLabel.setText("Hello, " + userName);  // Update the label with the username
    }

    @FXML
    public void initialize() {
        // Set the user's name dynamically
//    	setUserName("");

        // Add subjects to ComboBox using FXCollections
        subjectComboBox.setItems(FXCollections.observableArrayList("Math", "Science", "Computer", "English"));
    }

    // Handle the "Start Quiz" button action
    @FXML
    private void handleStartQuiz() {
    	String selectedSubject = subjectComboBox.getValue();
    	System.out.println("selected subjects" + selectedSubject);
    	if (selectedSubject == null) {
            statusLabel.setText("Please select a subject to start the quiz.");
        } else {
            statusLabel.setText("Starting quiz on: " + selectedSubject);
            
            // Load the AttemptQuiz FXML file and set the new scene
            try {
                // Load the FXML file
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AttemptQuiz.fxml"));
                Parent attemptQuizRoot = loader.load();
                
                // Get the controller from the FXML file
                AttemptQuizController attemptQuizController = loader.getController();
                
                // Pass the selected subject to the controller to initialize the quiz
                attemptQuizController.initializeQuiz(selectedSubject); // Assuming this method takes subject
                
                // Create a new scene with the loaded FXML
                Scene attemptQuizScene = new Scene(attemptQuizRoot, 500, 400);
                
                // Set the new scene to the current stage (window)
                Stage stage = (Stage) statusLabel.getScene().getWindow();
                stage.setScene(attemptQuizScene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
                statusLabel.setText("Error loading the quiz screen.");
            }
        }
    }
}
