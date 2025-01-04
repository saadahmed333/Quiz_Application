package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            // Load Login.fxml using FXMLLoader
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
            Parent root = loader.load();  // Load the FXML file

            // Get the LoginController instance from the loader
            LoginController loginController = loader.getController();
            loginController.setStage(primaryStage); // Pass the primary stage to the controller

            // Set the scene for the login screen
            Scene scene = new Scene(root, 500, 400); // 500px width, 400px height
            primaryStage.setScene(scene);
            primaryStage.setTitle("Login - Quiz Application");

            // Show the stage
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
