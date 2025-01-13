package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ResultController {

    @FXML
    private Text scoreText;

    @FXML
    private Text feedbackText;

    @FXML
    private Button retryButton;

    @FXML
    private Button exitButton;
    
    private Stage stage;
    
    public void setStage(Stage stage) {
        this.stage = stage;
        System.out.println("Stage is set.");
    }

    public void setResults(int score, int totalQuestions) {
        scoreText.setText("Your Score: " + score + " / " + totalQuestions);
        double percentage = ((double) score / totalQuestions) * 100;
        if (percentage == 100) {
            feedbackText.setText("Perfect! You got everything right!");
        } else if (percentage >= 75) {
            feedbackText.setText("Great job! You performed well.");
        } else if (percentage >= 50) {
            feedbackText.setText("Good effort! Keep practicing.");
        } else {
            feedbackText.setText("Don't give up! Try again to improve.");
        }
    }

    @FXML
    private void handleRetry() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Quiz.fxml"));
            Parent quizRoot = loader.load();
            QuizController quizController = loader.getController();
             quizController.setStage(stage);
            Scene quizScene = new Scene(quizRoot, 500, 400);
            stage.setScene(quizScene);
            stage.setTitle("Retry - Quiz Application");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading the Quiz.fxml file.");
        }
    }


    @FXML
    private void handleExit() {
        System.out.println("Exiting the application...");
        System.exit(0);
    }
}
