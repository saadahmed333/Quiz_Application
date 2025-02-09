package application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.collections.FXCollections;

public class QuizController {

    public Label userNameLabel;
    public ComboBox<String> subjectComboBox;
    public Label statusLabel;
    public String userName;
    public Stage stage;
    
    public void setStage(Stage stage) {
        this.stage = stage;
        System.out.println("Stage is set.");
    }
    
    public void setUserName(String loggedInUserName) {
    	this.userName = loggedInUserName;
        userNameLabel.setText("Hello, " + userName);
    }

    public void initialize() {
        subjectComboBox.setItems(FXCollections.observableArrayList("Math", "Science", "Computer", "English"));
    }

    public void handleStartQuiz() {
    	String selectedSubject = subjectComboBox.getValue();
    	System.out.println("selected subjects" + selectedSubject);
    	if (selectedSubject == null) {
            statusLabel.setText("Please select a subject to start the quiz.");
        } else {
            statusLabel.setText("Starting quiz on: " + selectedSubject);
            
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AttemptQuiz.fxml"));
                Parent attemptQuizRoot = loader.load();
                AttemptQuizController attemptQuizController = loader.getController();
                attemptQuizController.initializeQuiz(selectedSubject);
                Scene attemptQuizScene = new Scene(attemptQuizRoot, 500, 400);
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
