//package
package gui.travellingsalesman;

//imports
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

//main class
public class GameApplication extends Application {
    //start class from application
    @Override
    public void start(Stage stage) throws IOException {
        //loading the start menu fxml file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("menu-view.fxml"));

        //setting the controller and creating controllers
        MenuController controller = new MenuController();

        //connecting the fxml file and scene
        Parent root = loader.load();
        Scene scene = new Scene(root);

        //creating application icon
        Image icon = new Image("file:/C:/Users/moham_my0tjcn/IdeaProjects/TravellingSalesman/src/main/resources/icon.png");
        stage.getIcons().add(icon);

        //creating stage
        stage.setTitle("Travelling Salesman");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    //launching the code through the application launch method
    public static void main(String[] args) {
        launch();
    }
}