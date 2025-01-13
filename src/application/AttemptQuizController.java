package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.ToggleGroup;

import java.io.IOException;
import java.util.List;

public class AttemptQuizController {

    @FXML private VBox questionBox;
    @FXML private Text questionText;
    @FXML private RadioButton optionA;
    @FXML private RadioButton optionB;
    @FXML private RadioButton optionC;
    @FXML private RadioButton optionD;
    @FXML private Button nextButton;
    @FXML private Button finishButton;
    @FXML private Stage stage;

    private List<Question> questions;
    private int currentQuestionIndex = 0;
    private int score = 0;

    // Add question arrays for different subjects
    private List<Question> scienceQuestions = List.of(
            new Question("What is the chemical symbol for water?", "H2O", "O2", "CO2", "N2", "H2O"),
            new Question("What planet is known as the Red Planet?", "Earth", "Mars", "Jupiter", "Saturn", "Mars"),
            new Question("What gas do plants absorb from the atmosphere?", "Oxygen", "Nitrogen", "Carbon Dioxide", "Hydrogen", "Carbon Dioxide"),
            new Question("What is the boiling point of water at sea level?", "90°C", "100°C", "120°C", "80°C", "100°C"),
            new Question("What part of the cell contains genetic material?", "Nucleus", "Cytoplasm", "Mitochondria", "Ribosome", "Nucleus")
    );

    private List<Question> mathQuestions = List.of(
            new Question("What is 5 + 7?", "10", "12", "14", "15", "12"),
            new Question("What is 9 x 3?", "27", "18", "24", "30", "27"),
            new Question("What is the square root of 64?", "6", "7", "8", "9", "8"),
            new Question("What is 15 ÷ 3?", "5", "6", "4", "3", "5"),
            new Question("What is 7 x 8?", "48", "56", "63", "49", "56")
    );
    
    private List<Question> computerQuestions = List.of(
            new Question("What does CPU stand for?", "Central Processing Unit", "Computer Processing Unit", "Central Programming Unit", "Control Processing Unit", "Central Processing Unit"),
            new Question("What does RAM stand for?", "Random Access Memory", "Read Access Memory", "Rapid Access Memory", "Run Access Memory", "Random Access Memory"),
            new Question("Which part of the computer is responsible for graphics rendering?", "CPU", "GPU", "RAM", "Motherboard", "GPU"),
            new Question("Which of the following is not an operating system?", "Linux", "Windows", "Python", "MacOS", "Python"),
            new Question("What does HTML stand for?", "HyperText Markup Language", "HyperText Modern Language", "HighText Markup Language", "Hyper Transfer Markup Language", "HyperText Markup Language")
    );

    private List<Question> englishQuestions = List.of(
            new Question("What is the synonym of 'happy'?", "Sad", "Joyful", "Angry", "Excited", "Joyful"),
            new Question("What is the antonym of 'strong'?", "Weak", "Tough", "Powerful", "Solid", "Weak"),
            new Question("Which word is a noun?", "Quickly", "Beautiful", "Apple", "Run", "Apple"),
            new Question("What is the plural form of 'child'?", "Childs", "Children", "Child", "Childrens", "Children"),
            new Question("Which of the following is a verb?", "Run", "Blue", "Table", "Happy", "Run")
    );

    // Method to initialize and display questions and answers based on the selected subject
    public void initializeQuiz(String subject) {
        // Load questions based on the subject
        if ("Science".equals(subject)) {
            questions = scienceQuestions;
        } else if ("Math".equals(subject)) {
            questions = mathQuestions;
        }
        else if ("Computer".equals(subject)) {
            questions = computerQuestions;
        }
        else if ("English".equals(subject)) {
            questions = englishQuestions;
        }

        displayQuestion(questions.get(currentQuestionIndex));
    }

    private void displayQuestion(Question question) {
        questionText.setText(question.getQuestionText());
        optionA.setText(question.getOptionA());
        optionB.setText(question.getOptionB());
        optionC.setText(question.getOptionC());
        optionD.setText(question.getOptionD());

        // Set the ToggleGroup for the options to group them together
        ToggleGroup group = new ToggleGroup();
        optionA.setToggleGroup(group);
        optionB.setToggleGroup(group);
        optionC.setToggleGroup(group);
        optionD.setToggleGroup(group);

        nextButton.setDisable(false);  // Enable the next button
        finishButton.setVisible(false);  // Hide the finish button initially
    }

    @FXML
    private void handleNextQuestion() {
        // Get the selected option
        ToggleGroup group = (ToggleGroup) optionA.getToggleGroup();
        RadioButton selectedOption = (RadioButton) group.getSelectedToggle();

        // Check if the selected option is correct
        if (selectedOption != null && selectedOption.getText().equals(questions.get(currentQuestionIndex).getCorrectAnswer())) {
            score++;  // Increase score if the answer is correct
        }

        currentQuestionIndex++;  // Move to the next question

        if (currentQuestionIndex < questions.size()) {
            displayQuestion(questions.get(currentQuestionIndex)); // Show next question
        } else {
            showResults(score, questions.size()); // Show the result if the quiz is finished
        }
    }

//    private void showResult() {
//        // Clear questionBox and show the final score
//        questionBox.getChildren().clear();
//        Text resultText = new Text("Your score: " + score + " out of " + questions.size());
//        questionBox.getChildren().add(resultText);
//
//        // Hide the next button and show the finish button
//        nextButton.setDisable(true);
//        finishButton.setVisible(true);
//    }
    
 // After finishing the quiz
    private void showResults(int score, int totalQuestions) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ShowResult.fxml"));
            Parent root = loader.load();
            ResultController controller = loader.getController();
            controller.setStage(stage);
            controller.setResults(score, totalQuestions);
            Stage stage = (Stage) nextButton.getScene().getWindow();
            stage.setScene(new Scene(root, 500, 400));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void finishQuiz() {
    	showResults(score, questions.size());
    }
}
