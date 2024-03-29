//package
package gui.travellingsalesman;

//imports
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {
    // Attributes and references
    private Stage stage;
    private Parent root;
    private Scene main;
    @FXML
    private VBox MenuWindow;

    // Method to change background color (testing)
    @FXML
    protected void menuColor() {
        MenuWindow.setStyle("-fx-background-color: white;");
    }

    // Method to end the program
    @FXML
    protected void endGame(ActionEvent event) {
        //finding current stage
        stage = (Stage) MenuWindow.getScene().getWindow();

        // Exit the program
        stage.close();

        //to get other windows
        Platform.exit();
    }

    // Method to switch the window to the help window
    @FXML
    protected void switchSceneToHelp(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("help-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        main = new Scene(root);
        stage.setScene(main);
        stage.show();
    }

    // Method to switch the window to the game window
    @FXML
    protected void switchSceneToGame(ActionEvent event) throws IOException {
        //root = FXMLLoader.load(getClass().getResource("game-view.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("game-view.fxml"));
        //setting controller
        GameController gameController = new GameController();
        loader.setController(gameController);
        root = loader.load();


        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        main = new Scene(root);
        stage.setScene(main);
        stage.show();

        //making map
        gameController.createMap();
        gameController.createMiniMap();
        gameController.setCircleParent();
        gameController.getMessage().setText("Roll the dice to get moves for your player.\nClick on the map to enter the players.");

    }

}