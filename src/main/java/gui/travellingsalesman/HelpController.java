//package
package gui.travellingsalesman;

//imports
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelpController {
    //attributes and references
    private Stage stage;
    private Parent root;
    private Scene main;
    @FXML
    private AnchorPane infoWindow;

    //method to change background color(testing)
    @FXML
    protected void menuColor() {
        infoWindow.setStyle("-fx-background-color: white;");
    }

    //method to switch to menu
    @FXML
    protected void switchSceneToMenu(ActionEvent event) throws IOException {
        //loading fxml file as root
        root = FXMLLoader.load(getClass().getResource("menu-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        main = new Scene(root);
        stage.setScene(main);
        stage.show();
    }



}
