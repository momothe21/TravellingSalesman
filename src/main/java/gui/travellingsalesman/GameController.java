//package
package gui.travellingsalesman;

//imports
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
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

    private int moves, rolledNum;

    private int turns;

    @FXML
    private Button ReturntoMenu;

    @FXML
    private Circle playerOne;
    @FXML
    private Circle playerTwo;

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
    protected ArrayList<Coords> Redcells = new ArrayList<Coords>();
    protected ArrayList<Coords> Playerspawn = new ArrayList<Coords>();
    protected ArrayList<Treasures> valuables = new ArrayList<Treasures>();
    @FXML
    protected Label turn;
    @FXML
    protected Player player1;
    @FXML
    protected Player player2;

    //method to create the valuable treasures
    protected void assignTreasures(int type){
        int num = 0;
        switch (type){
            case 1:
                num = 2000;
                break;
            case 2:
                num = 5000;
                break;
            case 3:
                num = 4000;
                break;
            case 4:
                num = 6000;
                break;
            case 5:
                num = 7000;
                break;
            case 6:
                num = 1000;
                break;
            case 7:
                num = 8000;
                break;
            case 8:
                num = 3000;
                break;
            default:
                System.out.println("Random number fo score out of range.");
                break;
        }
        valuables.add(new Treasures(num));
    }

    //method to create the tiles for the frid map
    @FXML
    protected void createMap(){
        //variables for random map creation
        Random r = new Random();
        int color = 0,y,x,bc=0;
        int count = 1;

        //getting the treasures ready
        //assign scores randomly
        for (int i = 1; i <9;i++){
            assignTreasures(i);
        }
        //assign treasure randomly
        for(Treasures treasure: valuables){
            treasure.setName(Treasures.treasure.WoodenBow);
            switch (count){
                case 1:
                    treasure.setName(Treasures.treasure.DiamondRing);
                    break;
                case 2:
                    treasure.setName(Treasures.treasure.CrystalGoblets);
                    break;
                case 3:
                    treasure.setName(Treasures.treasure.DragonsScroll);
                    break;
                case 4:
                    treasure.setName(Treasures.treasure.GoldenGoblet);
                    break;
                case 5:
                    treasure.setName(Treasures.treasure.JewelEncrustedSword);
                    break;
                case 6:
                    treasure.setName(Treasures.treasure.PaladinsShield);
                    break;
                case 7:
                    treasure.setName(Treasures.treasure.GoldenKey);
                    break;
                case 8:
                    treasure.setName(Treasures.treasure.WoodenBow);
                    break;
                default:
                    System.out.println("type of weapon out of range");
                    break;
            }
            count++;
        }

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

        //player spawns
        Playerspawn.add(new Coords(0,0));
        Playerspawn.add(new Coords(9,9));

        // Create game grid
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                //rectangle creation
                Rectangle cell = new Rectangle(35, 35);

                //random number
                color= r.nextInt(100);

                //making sure to leave spawn points alone
                if((new Coords(i,j)).distanceCalc(Playerspawn)>1){
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
                    }else if (color>=32 && color<=42) {
                        cell.setFill(red);
                        Redcells.add(new Coords(i,j));
                    }else {
                        //default
                        cell.setFill(free);
                    }
                    //setting the castle location
                    if(i==5 && j == 5){
                        cell.setFill(yellow);
                        Yellowcells.add(new Coords(i,j));
                    }
                }else{
                    cell.setFill(free);
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
                        if((new Coords(x,y)).distanceCalc(Yellowcells)>1){
                            if((new Coords(x,y)).distanceCalc(Playerspawn)>0){
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
                                if((new Coords(x,y)).distanceCalc(Playerspawn)>0){
                                    cells[x][y].setFill(green);
                                    Greencells.add(new Coords(x,y));
                                    valuables.get(i).setLocation(new Coords(x,y));
                                }else{
                                    i--;
                                }
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
                                if((new Coords(x,y)).distanceCalc(Playerspawn)>0){
                                    cells[x][y].setFill(orange);
                                    Orangecells.add(new Coords(x,y));
                                }else{
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
        //getting the event handler ready
        setEvents();
    }
    //method to roll dice using btn
    @FXML
    //method to roll the dice
    protected void rollDiceBtn(ActionEvent event) throws InterruptedException {
        if(moves == 0){
            rollDice();
        }
    }

    //method to roll dice by clicking image
    @FXML
    protected void clickDice(MouseEvent event){
        if(moves == 0){
            rollDice();
        }
    }

    protected void rollDice(){
        //variables
        Random r = new Random();

        btnDice.setDisable(true);

        //File is loaded absolute needs to be relative
        Image dice1 = new Image("file:/C:/Users/moham_my0tjcn/IdeaProjects/TravellingSalesman/src/main/resources/gui/travellingsalesman/dice1.png");
        Image dice2 = new Image("file:/C:/Users/moham_my0tjcn/IdeaProjects/TravellingSalesman/src/main/resources/gui/travellingsalesman/dice2.png");
        Image dice3 = new Image("file:/C:/Users/moham_my0tjcn/IdeaProjects/TravellingSalesman/src/main/resources/gui/travellingsalesman/dice3.png");
        Image dice4 = new Image("file:/C:/Users/moham_my0tjcn/IdeaProjects/TravellingSalesman/src/main/resources/gui/travellingsalesman/dice4.png");
        Image dice5 = new Image("file:/C:/Users/moham_my0tjcn/IdeaProjects/TravellingSalesman/src/main/resources/gui/travellingsalesman/dice5.png");
        Image dice6 = new Image("file:/C:/Users/moham_my0tjcn/IdeaProjects/TravellingSalesman/src/main/resources/gui/travellingsalesman/dice6.png");

        //getting a number from 1-6
        rolledNum = r.nextInt(7-1)+1;
        moves = rolledNum;
        turns++;
        if(turns%2==0){
            turn.setText("Player 2");
        }else{
            turn.setText("Player 1");
        }


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

    //event handler initialize
    protected void setEvents(){
        //event handler setting
        EventController.setTraps(Redcells);
        EventController.setWalls(Blackcells);
        EventController.setCastle(Yellowcells);
        EventController.setItems(Bluecells);
        EventController.setMarkets(Orangecells);
        EventController.setTreasure(Greencells);
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

    //method to assign player to a circle to highlight the two players
    @FXML
    protected void setCircleParent(){
        //making player icons
        ImagePattern temp1 = new ImagePattern(new Image("file:/C:/Users/moham_my0tjcn/IdeaProjects/TravellingSalesman/src/main/resources/icon.png"));
        ImagePattern temp2 = new ImagePattern(new Image("file:/C:/Users/moham_my0tjcn/IdeaProjects/TravellingSalesman/src/main/resources/Player2.png"));

        //putting background for them
        playerOne.setFill(temp1);
        playerTwo.setFill(temp2);

        //creating the players as well
        player1 = new Player(playerOne,new Coords(-1,-1));
        player2 = new Player(playerTwo,new Coords(-2,-2));
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

    //method to spawn players using a click
    @FXML
    protected void clickSpawn(MouseEvent event){
        spawning();
    }

    //to spawn player into map
    protected void spawning(){
        Player currPlayer;
        if (moves > 0) {
            if(turns %2 ==0){
                currPlayer=player2;
            }else{
                currPlayer=player1;
            }
            if(!mainMap.getChildren().contains(currPlayer.getSelf())){
                currPlayer.spawn(mainMap);
                moves--;
                Message.setText("You rolled a "+rolledNum+".\nPlease move "+moves+" tile(s) to end your turn");
            }
        }
    }

    //method to take keyboard input
    @FXML
    public void inp(MouseEvent event){
        mainMap.requestFocus();
        mainMap.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                movingPlayer(keyEvent.getCode());
            }
        });
    }

    //method to filter the input
    public void movingPlayer(KeyCode direction){
        //variables and classes
        Player currPlayer;
        if(turns %2 ==0){
            currPlayer = player2;
        }else {
            currPlayer = player1;
        }
        Coords currcoords = currPlayer.getPlayerCoords();
        Coords newcoords;

        if(mainMap.getChildren().contains(currPlayer.getSelf())){
            if(moves != 0){
                if(EventController.canMove(currcoords,direction)){
                    switch (direction){
                        case UP, W:
                            currPlayer.move(0,-1);
                            break;
                        case DOWN, S:
                            currPlayer.move(0,1);
                            break;
                        case LEFT, A:

                            currPlayer.move(-1,0);
                            break;
                        case RIGHT, D:
                            currPlayer.move(1,0);
                            break;
                        default:
                            break;
                    }
                }
                newcoords = currPlayer.getPlayerCoords();
                if(newcoords != currcoords){
                    moves--;
                    currPlayer.getPlayerPath().add(newcoords);

                    //checking if the current location is special
                    if(EventController.isItem(newcoords)){
                        System.out.println("Encountered Item");
                    }else if(EventController.isCastle(newcoords)){
                        System.out.println("Encountered Castle");
                    } else if (EventController.isMarket(newcoords)) {
                        System.out.println("Encountered Market");
                    } else if (EventController.isTreasure(newcoords)) {
                        System.out.println("Encountered Treasure");
                    } else if (EventController.isTrap(newcoords)) {
                        System.out.println("Encountered Trap");
                    }

                }
                Message.setText("You rolled a "+rolledNum+".\nPlease move "+moves+" tile(s) to end your turn");
            }
        }
    }


    //setters and getters
    public Label getMessage() {
        return Message;
    }

    public int getMoves() {
        return moves;
    }

    public void setMoves(int moves) {
        this.moves = moves;
    }

    public Circle getPlayerOne() {
        return playerOne;
    }

    public void setPlayerOne(Circle playerOne) {
        this.playerOne = playerOne;
    }

    public Circle getPlayerTwo() {
        return playerTwo;
    }

    public void setPlayerTwo(Circle playerTwo) {
        this.playerTwo = playerTwo;
    }

    public Rectangle[][] getCells() {
        return cells;
    }

    public ArrayList<Coords> getBlackcells() {
        return Blackcells;
    }

    public ArrayList<Coords> getOrangecells() {
        return Orangecells;
    }

    public ArrayList<Coords> getGreencells() {
        return Greencells;
    }

    public ArrayList<Coords> getBluecells() {
        return Bluecells;
    }

    public ArrayList<Coords> getYellowcells() {
        return Yellowcells;
    }

    public ArrayList<Coords> getRedcells() {
        return Redcells;
    }

    public ArrayList<Coords> getPlayerspawn() {
        return Playerspawn;
    }

    public GridPane getMainMap() {
        return mainMap;
    }
}