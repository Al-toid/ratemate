package ratemate;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.InputStream;
import java.io.IOException;

/**
 * 
 * JavaFX App
 * 	1.	main method starts the JavaFX application.
	2.	start method creates the main window (Stage) and sets the initial UI from primary.fxml.
	3.	FXML defines the structure of the UI (buttons, layouts, etc.).
	4.	Controller defines behavior (button clicks, submitions).
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        // Load the primary FXML layout
        Parent root = loadFXML("primary");

        // Programmatically load the image
        InputStream imageStream = App.class.getResourceAsStream("/images/RateMateLogo.png");
        Image image = new Image(imageStream);
        ImageView imageView = new ImageView(image);

        // Configure the ImageView
        imageView.setFitWidth(500);
        imageView.setPreserveRatio(true);

        // Center the ImageView using a StackPane
        StackPane imagePane = new StackPane(imageView);
        imagePane.setPrefHeight(500); //Set height for better layout control

         // Combine the image and primary.fxml layout in a VBox
        VBox layout = new VBox(imagePane, root);
        layout.setSpacing(10); // Add spacing between image and FXML content

        //change background color
        layout.setStyle("-fx-background-color: linear-gradient(to bottom, gray, lightblue);");

        // Set up the scene
        scene = new Scene(layout, 1000, 1000);
        stage.setScene(scene);
        stage.setTitle("RateMate Application");
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}