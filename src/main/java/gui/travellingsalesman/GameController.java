//package
package gui.travellingsalesman;

//imports
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

//game controller
public class GameController {
    // Attributes and references
    private Stage stage;
    private Parent root;
    private Scene main;

    private int moves;

    @FXML
    private Button btnDice;

    @FXML
    private Label Message;
    @FXML
    private ImageView diceImage;
    @FXML
    private GridPane mainMap, mainMap1;
    protected Rectangle[][] cells = new Rectangle[10][10];

    protected ArrayList<Coords> Blackcells = new ArrayList<Coords>();
    protected ArrayList<Coords> Orangecells = new ArrayList<Coords>();
    protected ArrayList<Coords> Greencells = new ArrayList<Coords>();
    protected ArrayList<Coords> Bluecells = new ArrayList<Coords>();

    //method to create the tiles for the frid map
    @FXML
    protected void createMap(){
        //variables for random map creation
        Random r = new Random();
        int color = 0,y,x,bc=0;
        // Create game grid
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                //rectangle creation
                Rectangle cell = new Rectangle(35, 35);

                //random number
                color= r.nextInt(100);

                //random creation
                //for black
                if((color >=1 && color <=10)){
                    //checking distance between blacks
                    if(bc>0&&((new Coords(i,j)).distanceCalc(Blackcells)>1)){
                        cell.setFill(Color.BLACK);
                        Blackcells.add(new Coords(i,j));
                        bc++;
                    }else if(bc == 0){
                        cell.setFill(Color.BLACK);
                        Blackcells.add(new Coords(i,j));
                        bc++;
                    }else{
                        //setting it to default free space
                        cell.setFill(Color.rgb(242,221,166));
                    }
                    //setting traps
                }else if (color>=32 && color<=38) {
                    cell.setFill(Color.RED);
                }else {
                    //default
                    cell.setFill(Color.rgb(242,221,166));
                }
                //setting the castle location
                if(i==5 && j == 5){
                    cell.setFill(Color.GOLD);
                }
                //saving and appending map
                cells[i][j] = cell;
                mainMap.add(cell, i, j);
            }
        }
        //adding unique parameter elements
        //putting orange cells
        for(int i = 0; i<5; i++){
            y = r.nextInt(10-1)+1;
            x = r.nextInt(10-1)+1;

            //checking distance of oranges
            if((new Coords(x,y)).distanceCalc(Orangecells)>2){
                //checking it is not overlapping with black
                if(cells[x][y].getFill() != Color.BLACK){
                    //making sure it is not overlapping the castle
                    if(cells[x][y].getFill() != Color.GOLD){
                        //making sure it is not overlapping the oranges
                        if(cells[x][y].getFill()!=Color.ORANGE){
                            cells[x][y].setFill(Color.ORANGE);
                            Orangecells.add(new Coords(x,y));
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

        //putting Blue cells
        for(int i = 0; i<13; i++){
            y = r.nextInt(10-1)+1;
            x = r.nextInt(10-1)+1;
            //checking distance between blues
            if((new Coords(x,y)).distanceCalc(Bluecells)>1){
                //checking if it is overlapping with black
                if(cells[x][y].getFill() != Color.BLACK){
                    //checking if it is overlapping with the castle
                    if(cells[x][y].getFill() != Color.GOLD){
                        //checking if it overlaps orange
                        if(cells[x][y].getFill()!=Color.ORANGE){
                            //checking if it is overlapping blues
                            if(cells[x][y].getFill()!=Color.BLUE){
                                cells[x][y].setFill(Color.BLUE);
                                Bluecells.add(new Coords(x,y));
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
            }else {
                i--;
            }
        }
        //putting green cells
        for(int i = 0; i<8; i++) {
            y = r.nextInt(10 - 1) + 1;
            x = r.nextInt(10 - 1) + 1;
            //checking the distance of greens
            if((new Coords(x,y)).distanceCalc(Greencells)>2){
                //checking if it is overlapping the blacks
                if(cells[x][y].getFill() != Color.BLACK){
                    //checking if it is overlapping with the castle
                    if (cells[x][y].getFill() != Color.GOLD) {
                        //checking if it overlaps the oranges
                        if (cells[x][y].getFill() != Color.ORANGE) {
                            //checking if it overlaps the blues
                            if (cells[x][y].getFill() != Color.BLUE) {
                                //checking if it overlaps the greens
                                if (cells[x][y].getFill() != Color.GREEN) {
                                    cells[x][y].setFill(Color.GREEN);
                                    Greencells.add(new Coords(x,y));
                                } else {
                                    i--;
                                }
                            } else {
                                i--;
                            }
                        } else {
                            i--;
                        }
                    } else {
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

    @FXML
    //method to roll the dice
    protected void rollDice(ActionEvent event) throws InterruptedException {
        //variables
        Random r = new Random();

        btnDice.setDisable(true);
        int rolledNum;
        Image dice1 = new Image("file:/C:/Users/moham_my0tjcn/IdeaProjects/TravellingSalesman/src/main/resources/gui/travellingsalesman/dice1.png");
        Image dice2 = new Image("file:/C:/Users/moham_my0tjcn/IdeaProjects/TravellingSalesman/src/main/resources/gui/travellingsalesman/dice2.png");
        Image dice3 = new Image("file:/C:/Users/moham_my0tjcn/IdeaProjects/TravellingSalesman/src/main/resources/gui/travellingsalesman/dice3.png");
        Image dice4 = new Image("file:/C:/Users/moham_my0tjcn/IdeaProjects/TravellingSalesman/src/main/resources/gui/travellingsalesman/dice4.png");
        Image dice5 = new Image("file:/C:/Users/moham_my0tjcn/IdeaProjects/TravellingSalesman/src/main/resources/gui/travellingsalesman/dice5.png");
        Image dice6 = new Image("file:/C:/Users/moham_my0tjcn/IdeaProjects/TravellingSalesman/src/main/resources/gui/travellingsalesman/dice6.png");

        //getting a number from 1-6
        rolledNum = r.nextInt(7-1)+1;
        moves = rolledNum;


        //setting the rolled image
        switch (rolledNum){
            case 1:
                diceImage.setImage(dice1);
                break;
            case 2:
                diceImage.setImage(dice2);
                break;
            case 3:
                diceImage.setImage(dice3);
                break;
            case 4:
                diceImage.setImage(dice4);
                break;
            case 5:
                diceImage.setImage(dice5);
                break;
            case 6:
                diceImage.setImage(dice6);
                break;
            default:
                System.out.println("Error picked a number out of range for dice, exiting...");
                System.exit(-1);
                break;
        }
        Message.setText("You rolled a "+rolledNum+".\nPlease move "+moves+" tile(s) to end your turn");
        btnDice.setDisable(false);
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
