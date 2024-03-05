package gui.travellingsalesman;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.Random;

public class GameController {
    // Attributes and references
    private Stage stage;
    private Parent root;
    private Scene main;
    @FXML
    private GridPane mainMap, mainMap1;
    protected Rectangle[][] cells = new Rectangle[10][10];

    //method to create the tiles
    @FXML
    protected void createMap(){
        //variables for random map creation
        Random r = new Random();
        int color = 0,y,x,z;
        // Create game grid
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                //rectangle creation
                Rectangle cell = new Rectangle(35, 35);

                //random number
                color= r.nextInt(100);

                //random creation
                if((color >=1 && color <=5)){
                    cell.setFill(Color.BLACK);
                }else if (color>=32 && color<=38) {
                    cell.setFill(Color.RED);
                }else {
                    cell.setFill(Color.rgb(242,221,166));
                }
                if(i==5 && j == 5){
                    cell.setFill(Color.GOLD);
                }
                //saving and appending
                cells[i][j] = cell;
                mainMap.add(cell, i, j);
            }
        }
        for(int i = 0; i<5; i++){
            y = r.nextInt(10-1)+1;
            x = r.nextInt(10-1)+1;
            if(y!=5){
                if(cells[y][x].getFill()!=Color.ORANGE){
                    cells[y][x].setFill(Color.ORANGE);
                }else{
                    i--;
                }
            }else{
                i--;
            }
        }
        for(int i = 0; i<13; i++){
            y = r.nextInt(10-1)+1;
            x = r.nextInt(10-1)+1;
            if(y!=5){
                if(cells[y][x].getFill()!=Color.ORANGE){
                    if(cells[y][x].getFill()!=Color.BLUE){
                        cells[y][x].setFill(Color.BLUE);
                    }else{
                        i--;
                    }
                }else{
                    i--;
                }
            }else{
                i--;
            }
        }
        for(int i = 0; i<8; i++){
            z = r.nextInt(2);
            if(z==1){
                y = r.nextInt(10-1)+1;
                x = r.nextInt(8-1)+1;
                if(y!=5){
                    if(cells[y][2+x].getFill()!=Color.ORANGE){
                        if(cells[y][2+x].getFill()!=Color.BLUE){
                            if(cells[y][2+x].getFill()!=Color.GREEN){
                                cells[y][2+x].setFill(Color.GREEN);
                            }else{
                                i--;
                            }
                        }else{
                            i--;
                        }
                    }else{
                        i--;
                    }
                }else{
                    i--;
                }
            }else{
                x = r.nextInt(10-1)+1;
                y = r.nextInt(8-1)+1;
                if(y!=5){
                    if(cells[y][x].getFill()!=Color.ORANGE){
                        if(cells[y][x].getFill()!=Color.BLUE){
                            if(cells[y][x].getFill()!=Color.GREEN){
                                cells[y][x].setFill(Color.GREEN);
                            }else{
                                i--;
                            }
                        }else{
                            i--;
                        }
                    }else{
                        i--;
                    }
                }else{
                    i--;
                }
            }

        }
    }

    //creating minimaps
    @FXML
    protected void createMiniMap(){
        for(int i = 0;i<10;i++){
            for(int j = 0; j<10; j++){
                Rectangle cell = new Rectangle();
                cell.setHeight(10);
                cell.setWidth(10);
                cell.setFill(cells[i][j].getFill());
                mainMap1.add(cell,i,j);
            }
        }
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
}
