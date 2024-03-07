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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
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
    protected ArrayList<Coords> Yellowcells = new ArrayList<Coords>();

    //method to roll dice by clicking image
    @FXML
    protected void clickDice(MouseEvent event){
        rollDice();
    }

    //method to create the tiles for the frid map
    @FXML
    protected void createMap(){
        //variables for random map creation
        Random r = new Random();
        int color = 0,y,x,bc=0;

        //colours
        //Two light schemes for user preference(as part of phase 3 maybe)
        //light mode
//        Paint black=Color.BLACK;
//        Paint blue=Color.rgb(30,144,255);
//        Paint green=Color.rgb(63,255,33);
//        Paint free=Color.rgb(242,221,166);
//        Paint orange=Color.rgb(255,142,0);
//        Paint yellow=Color.rgb(231,255,31);
//        Paint red = Color.rgb(255,0,0);
        //dark mode
        Paint black=Color.BLACK;
        Paint blue=Color.BLUE;
        Paint green=Color.GREEN;
        Paint free=Color.rgb(242,221,166);
        Paint orange=Color.ORANGE;
        Paint yellow=Color.GOLD;
        Paint red = Color.RED;

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
                        cell.setFill(black);
                        Blackcells.add(new Coords(i,j));
                        bc++;
                    }else if(bc == 0){
                        cell.setFill(black);
                        Blackcells.add(new Coords(i,j));
                        bc++;
                    }else{
                        //setting it to default free space
                        cell.setFill(free);
                    }
                    //setting traps
                }else if (color>=32 && color<=38) {
                    cell.setFill(red);
                }else {
                    //default
                    cell.setFill(free);
                }
                //setting the castle location
                if(i==5 && j == 5){
                    cell.setFill(yellow);
                    Yellowcells.add(new Coords(i,j));
                }
                //saving and appending map
                cells[i][j] = cell;
                mainMap.add(cell, i, j);
            }
        }
        //adding unique parameter elements
        //putting 13 Blue cells
        for(int i = 0; i<13; i++){
            y = r.nextInt(10-1)+1;
            x = r.nextInt(10-1)+1;
            Paint color1 = cells[x][y].getFill();

            //checking if blues overlap
            if(color1 != blue){
                //checking it is not overlapping with black
                if(color1 != black){
                    //making sure it is not overlapping the castle
                    if(color1 != yellow){
                        //assigning blues
                        cells[x][y].setFill(blue);
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
        }

        //putting 8 green cells
        for(int i = 0; i<8; i++){
            y = r.nextInt(10-1)+1;
            x = r.nextInt(10-1)+1;
            Paint color1 = cells[x][y].getFill();
            //checking distance between greens
            if((new Coords(x,y)).distanceCalc(Greencells)>1){
                //checking if it is overlapping with black
                if(color1 != black){
                    //checking if it is overlapping with the castle
                    if(color1 != yellow){
                        //checking if it overlaps orange
                        if(color1!=blue){
                            if((new Coords(x,y)).distanceCalc(Yellowcells)>2){
                                cells[x][y].setFill(green);
                                Greencells.add(new Coords(x,y));
                            }else {
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
        //putting 5 Orange cells
        for(int i = 0; i<5; i++) {
            y = r.nextInt(10 - 1) + 1;
            x = r.nextInt(10 - 1) + 1;
            Paint color1 = cells[x][y].getFill();
            //checking the distance of oranges
            if((new Coords(x,y)).distanceCalc(Orangecells)>1){
                //checking if it is overlapping the blacks
                if(color1 != black){
                    //checking if it is overlapping with the castle
                    if (color1 != yellow) {
                        //checking if it overlaps the oranges
                        if (color1 != green) {
                            //checking if it overlaps the blues
                            if (color1 != blue) {
                                cells[x][y].setFill(orange);
                                Orangecells.add(new Coords(x,y));
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
    protected void rollDiceBtn(ActionEvent event) throws InterruptedException {
        rollDice();
    }

    protected void rollDice(){
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