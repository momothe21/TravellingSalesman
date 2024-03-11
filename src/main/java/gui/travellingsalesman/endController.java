package gui.travellingsalesman;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;

public class endController {
    //attributes
    private Stage stage;
    private Parent root;
    private Scene main;

    @FXML
    protected Rectangle winnerPlayer;

    //method to end program by pressing space or escape
    @FXML
    protected void returnSpace(KeyEvent event) throws IOException {
            // Loading fxml file as root
            Parent root = FXMLLoader.load(getClass().getResource("menu-view.fxml"));

            // Getting the stage from the event source
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Creating a new scene and setting it to the stage
            Scene main = new Scene(root);
            stage.setScene(main);
            stage.show();
    }

    //method to go back to menu
    @FXML
    protected void switchSceneToMenu(ActionEvent event) throws IOException {
            //loading fxml file as root
            root = FXMLLoader.load(getClass().getResource("menu-view.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            main = new Scene(root);
            stage.setScene(main);
            stage.show();
    }

    //setters and getters


    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Parent getRoot() {
        return root;
    }

    public void setRoot(Parent root) {
        this.root = root;
    }

    public Scene getMain() {
        return main;
    }

    public void setMain(Scene main) {
        this.main = main;
    }

    public Rectangle getWinnerPlayer() {
        return winnerPlayer;
    }

    public void setWinnerPlayer(Rectangle winnerPlayer) {
        this.winnerPlayer = winnerPlayer;
    }
}
