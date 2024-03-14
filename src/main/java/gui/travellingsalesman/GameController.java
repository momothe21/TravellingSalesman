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
import javafx.scene.layout.AnchorPane;
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
    private static Stage stage;
    private Parent root;
    private Scene main;

    private Treasures quest;

    private int moves, rolledNum;

    private int turns;
    private long startTimeMillis;

    @FXML
    private AnchorPane gameWindow;

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
    protected Rectangle[][] cellsMini = new Rectangle[10][10];
    @FXML
    protected Rectangle winnerPlayer;

    protected ArrayList<Coords> blackCells = new ArrayList<Coords>();
    protected ArrayList<Coords> orangeCells = new ArrayList<Coords>();
    protected ArrayList<Coords> greenCells = new ArrayList<Coords>();
    protected ArrayList<Coords> greenCellsPath = new ArrayList<Coords>();
    protected ArrayList<Coords> blueCells = new ArrayList<Coords>();
    protected ArrayList<Coords> yellowCells = new ArrayList<Coords>();
    protected ArrayList<Coords> redCells = new ArrayList<Coords>();
    protected ArrayList<Coords> playerSpawn = new ArrayList<Coords>();
    protected ArrayList<Treasures> valuables = new ArrayList<Treasures>();
    @FXML
    protected Label turn;
    @FXML
    protected Player player1;
    @FXML
    protected Player player2;
    @FXML
    protected Label questLabel,powerLabel,wealthLabel,scoreLabel,winMessage;
    public static Player winner;

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


    //method to create the valuable treasures
    protected void assignTreasures(int type){
        int num = 0;
        switch (type){
            case 1:
                num = 200;
                break;
            case 2:
                num = 500;
                break;
            case 3:
                num = 400;
                break;
            case 4:
                num = 600;
                break;
            case 5:
                num = 700;
                break;
            case 6:
                num = 100;
                break;
            case 7:
                num = 800;
                break;
            case 8:
                num = 300;
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
        //setting values
        stage = (Stage) gameWindow.getScene().getWindow();


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

        //player spawns
        playerSpawn.add(new Coords(0,0));
        playerSpawn.add(new Coords(9,9));

        // Create game grid
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                //rectangle creation
                Rectangle cell = new Rectangle(35, 35);

                //random number
                color= r.nextInt(100);

                //making sure to leave spawn points alone
                if((new Coords(i,j)).distanceCalc(playerSpawn)>1){
                    //random creation
                    //for black
                    if((color >=1 && color <=10)){
                        //checking distance between blacks
                        if(bc>0&&((new Coords(i,j)).distanceCalc(blackCells)>1)){
                            cell.setFill(black);
                            blackCells.add(new Coords(i,j));
                            bc++;
                        }else if(bc == 0){
                            cell.setFill(black);
                            blackCells.add(new Coords(i,j));
                            bc++;
                        }else{
                            //setting it to default free space
                            cell.setFill(free);
                        }
                        //setting traps
                    }else if (color>=32 && color<=42) {
                        cell.setFill(red);
                        redCells.add(new Coords(i,j));
                    }else {
                        //default
                        cell.setFill(free);
                    }
                    //setting the castle location
                    if(i==5 && j == 5){
                        cell.setFill(yellow);
                        yellowCells.add(new Coords(i,j));
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
                        if((new Coords(x,y)).distanceCalc(yellowCells)>1){
                            if((new Coords(x,y)).distanceCalc(playerSpawn)>0){
                                //assigning blues
                                cells[x][y].setFill(blue);
                                blueCells.add(new Coords(x,y));
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
            if((new Coords(x,y)).distanceCalc(greenCells)>1){
                //checking if it is overlapping with black
                if(color1 != black){
                    //checking if it is overlapping with the castle
                    if(color1 != yellow){
                        //checking if it overlaps orange
                        if(color1!=blue){
                            if((new Coords(x,y)).distanceCalc(yellowCells)>1){
                                if((new Coords(x,y)).distanceCalc(playerSpawn)>2){
                                    cells[x][y].setFill(green);
                                    greenCells.add(new Coords(x,y));
                                    greenCellsPath.add(new Coords(x,y));
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
            if((new Coords(x,y)).distanceCalc(orangeCells)>1){
                //checking if it is overlapping the blacks
                if(color1 != black){
                    //checking if it is overlapping with the castle
                    if (color1 != yellow) {
                        //checking if it overlaps the oranges
                        if (color1 != green) {
                            //checking if it overlaps the blues
                            if (color1 != blue) {
                                if((new Coords(x,y)).distanceCalc(playerSpawn)>0){
                                    cells[x][y].setFill(orange);
                                    orangeCells.add(new Coords(x,y));
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

        //assigning quest
        setQuest();
    }

    //method to roll dice using key
    @FXML
    protected void rollDiceKey(KeyEvent event) throws InterruptedException {
        if(event.getCode() == KeyCode.SPACE){
            if(moves == 0){
                rollDice();
            }
        }
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

        //saving start time
        if(startTimeMillis == 0){
            startTimeMillis =System.currentTimeMillis();;
        }

        //dummy proofing
        btnDice.setDisable(true);

        //File is loaded absolute needs to be relative
        Image dice1 = new Image("dice1.png");
        Image dice2 = new Image("dice2.png");
        Image dice3 = new Image("dice3.png");
        Image dice4 = new Image("dice4.png");
        Image dice5 = new Image("dice5.png");
        Image dice6 = new Image("dice6.png");

        //getting a number from 1-6
        rolledNum = r.nextInt(7-1)+1;
        moves = rolledNum;
        turns++;
        if(turns%2==0){
            turn.setText("Player 2");
            displayStats(player2);
            upadateMiniMap(player2);
        }else{
            turn.setText("Player 1");
            displayStats(player1);
            upadateMiniMap(player1);
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
        EventController.setTraps(redCells);
        EventController.setWalls(blackCells);
        EventController.setCastle(yellowCells);
        EventController.setItems(blueCells);
        EventController.setMarkets(orangeCells);
        EventController.setTreasure(greenCells);
    }

    //creating minimaps
    @FXML
    protected void createMiniMap(){
        for(int i = 0;i<10;i++){
            for(int j = 0; j<10; j++){
                Rectangle cell = new Rectangle();
                cell.setHeight(10);
                cell.setWidth(10);
                cell.setFill(free);
                cellsMini[i][j] = cell;
                mainMap1.add(cell,i,j);
            }
        }
    }

    //method to update map with players current path
    @FXML
    protected void upadateMiniMap(Player player){
        //variables
        int x,y;
        Paint curcolor;
        //resetting the map from previous player
        for(int i = 0;i<10;i++){
            for(int j = 0; j<10; j++){
                cellsMini[i][j].setFill(free);
            }
        }
        //colouring in the players path
        for(int i = 0; i < player.getPlayerPath().size();i++){
            x= player.getPlayerPath().get(i).getX();
            y= player.getPlayerPath().get(i).getY();
            curcolor = cells[x][y].getFill();
            cellsMini[x][y].setFill(curcolor);
        }
    }

    //method to assign player to a circle to highlight the two players
    @FXML
    protected void setCircleParent(){
        //making player icons
        ImagePattern temp1 = new ImagePattern(new Image("icon.png"));
        ImagePattern temp2 = new ImagePattern(new Image("Player2.png"));

        //putting background for them
        playerOne.setFill(temp1);
        playerTwo.setFill(temp2);

        //creating the players as well
        player1 = new Player(playerOne,new Coords(-1,-1),1);
        player2 = new Player(playerTwo,new Coords(-2,-2),2);
    }

    //method to go back to menu
    @FXML
    protected void switchSceneToMenu(ActionEvent event) throws IOException {
        if (winner == null) {
            //loading fxml file as root
            root = FXMLLoader.load(getClass().getResource("menu-view.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            main = new Scene(root);
            stage.setScene(main);
            stage.show();
        }else{
            endController controller = new endController();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("win-view.fxml"));
            //setting controller
            loader.setController(controller);
            root = loader.load();


            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            main = new Scene(root);
            stage.setScene(main);
            stage.show();

            controller.setMain(main);
            controller.setRoot(root);
            controller.setStage(stage);
            controller.getWinnerPlayer().setFill(winner.getSelf().getFill());

        }
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
        Random lootValue = new Random();
        int loot = 0;
        Player currPlayer;
        if(turns %2 ==0){
            currPlayer = player2;
        }else {
            currPlayer = player1;
        }
        Coords currcoords = currPlayer.getPlayerCoords();
        Coords newcoords;
        Coords special = new Coords(0,0);

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
                }else{
                    special.setY(currcoords.getY());
                    special.setX(currcoords.getX());
                    switch (direction){
                        case UP, W:
                            special.setY(currcoords.getY()-1);
                            break;
                        case DOWN, S:
                            special.setY(currcoords.getY()+1);
                            break;
                        case LEFT, A:
                            special.setX(currcoords.getX()-1);
                            break;
                        case RIGHT, D:
                            special.setX(currcoords.getX()+1);
                            break;
                        default:
                            break;
                    }
                    currPlayer.getPlayerPath().add(special);
                }
                newcoords = currPlayer.getPlayerCoords();
                if(newcoords != currcoords){
                    moves--;
                    currPlayer.getPlayerPath().add(newcoords);
                    Message.setText("You rolled a "+rolledNum+".\nPlease move "+moves+" tile(s) to end your turn");

                    //checking if the current location is special
                    if(EventController.isItem(newcoords)){
                        loot = lootValue.nextInt(100)+1;
                        currPlayer.setWealth(loot);
                        Message.setText("You encountered treasure worth $"+ loot);
                        blueCells.remove(currPlayer.getPlayerCoords());
                        cells[currPlayer.getPlayerCoords().getX()][currPlayer.getPlayerCoords().getY()].setFill(free);
                    }else if(EventController.isCastle(newcoords)){
                        if(currPlayer.getPlayerPath().contains(quest.getLocation())){
                            currPlayer.setWealth(quest.getScore());
                            currPlayer.setScore(1);
                            if(!valuables.isEmpty()){
                                setQuest();
                                player1.getPlayerPath().remove(quest.getLocation());
                                player2.getPlayerPath().remove(quest.getLocation());
                            }else{
                                if(player1.getScore()> player2.getScore()){
                                    winner = player1;
                                } else if (player2.getScore()> player1.getScore()) {
                                    winner = player2;
                                }else{
                                    if(player1.getWealth()> player2.getWealth()){
                                        winner = player1;
                                    } else if (player2.getWealth()> player1.getWealth()) {
                                        winner = player2;
                                    }else{
                                        if(player1.getPower()> player2.getPower()){
                                            winner = player1;
                                        } else if (player2.getPower()> player1.getPower()) {
                                            winner = player2;
                                        }
                                    }
                                }
                                if(!(winner == null)){
                                    Message.setText("Game Over,\nPlayer "+winner.getPlayernum()+" Wins!");
                                    Message.autosize();
                                    ReturntoMenu.setText("Claim your Prize");
                                }else{
                                    Message.setText("This game ended in a surprising perfect draw!");
                                }
                            }
                            currPlayer.setPlayerPath(new ArrayList<Coords>());
                        }
                    } else if (EventController.isMarket(newcoords)) {
                        //last one to be implemented
                        System.out.println("Encountered Market");
                    } else if (EventController.isTreasure(newcoords)) {
                        //checking if anyone already encountered the quest treasure before the current treasure tile
                        if(!(questInPath(player1)||questInPath(player2))){
                            //checking if the new tile is the quest treasure
                            if(currPlayer.getPlayerPath().contains(quest.getLocation())){
                                int x = quest.getLocation().getX();
                                int y = quest.getLocation().getY();
                                //informing player that they found the quest item
                                Message.setText("You found the quest item "+quest.getName().toString()+".\n Go to the castle to turn it in!");
                                //what happens after finding the correct Item
                                cells[x][y].setFill(free);
                                if(!valuables.isEmpty()){
                                    valuables.remove(quest);
//                                    greenCells.remove(quest.getLocation());
                                }
                            }
                        }
                    } else if (EventController.isTrap(newcoords)) {
                        int lost,loop=0;
                        while(loop == 0){
                            loot = lootValue.nextInt(3)+1;
                            switch (loot){
                                case 1:
                                    lost = lootValue.nextInt(6)+1;
                                    if(getMoves()>0){
                                        moves--;
                                        Message.setText("You encountered a trap and got injured\nMoves reduced by "+ lost);
                                        loop++;
                                    }
                                    break;
                                case 2:
                                    lost = lootValue.nextInt(100)+1;
                                    if(currPlayer.getWealth()>lost){
                                        currPlayer.setWealth(-1*lost);
                                        Message.setText("You encountered a trap and lost $"+ lost);
                                        loop++;
                                    }
                                    break;
                                case 3:
                                    lost = lootValue.nextInt(10)+1;
                                    if(currPlayer.getPower()>lost){
                                        currPlayer.setPower(-1*lost);
                                        Message.setText("You encountered a trap and lost "+ lost+" power");
                                        loop++;
                                    }
                                    break;
                                default:
                                    System.out.println("Loot to be lost is undetermined.");
                                    break;
                            }
                        }
                        redCells.remove(currPlayer.getPlayerCoords());
                        cells[currPlayer.getPlayerCoords().getX()][currPlayer.getPlayerCoords().getY()].setFill(free);
                    }

                }
            }
            displayStats(currPlayer);
            upadateMiniMap(currPlayer);
        }
    }

    //method to check if a player has encountered the quest items' location
    protected boolean questInPath(Player player){
        boolean flag = false;
        if(player.getPlayerPath().size()>1){
            for(int i = 0; i<player.getPlayerPath().size()-1;i++){
                if(player.getPlayerPath().get(i)==quest.getLocation()){
                    flag = true;
                }
            }
        }
        return flag;
    }

    //displaying a players stats onto the side
    @FXML
    protected void displayStats(Player player){
        scoreLabel.setText(""+player.getScore());
        wealthLabel.setText(""+player.getWealth());
        powerLabel.setText(""+player.getPower());
    }

    //method to open a new window
    @FXML
    protected void statsBoard(MouseEvent event) throws IOException{
        if(rolledNum != 0){
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("stats-view.fxml"));
            Stage stage1 = new Stage();
            Parent root1 = loader1.load();

            Image icon = new Image("icon.png");
            stage1.getIcons().add(icon);

            Scene stats = new Scene(root1);

            stage1.setScene(stats);
            stage1.show();
            stage.getScene().getWindow();
            stage.getScene().getWindow().hide();
            StatsController statsController = getStatsController(loader1);
            statsController.setStage(stage1);
            statsController.timer();
            statsController.display();
        }
    }

    private StatsController getStatsController(FXMLLoader loader1) {
        StatsController statsController = loader1.getController();

        //sending players' info
        statsController.setTurn(turns);
        statsController.setQuest(quest);
        statsController.setP1Power(player1.getPower());
        statsController.setP1Score(player1.getScore());
        statsController.setP1Wealth(player1.getWealth());
        statsController.setP2Power(player2.getPower());
        statsController.setP2Score(player2.getScore());
        statsController.setP2Wealth(player2.getWealth());
        statsController.setStartTimeMillis(startTimeMillis);
        statsController.setQuestCounter(8-valuables.size());
        return statsController;
    }

    //method to enable the window again
    protected static void showGame(){
        stage.show();
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

    public ArrayList<Coords> getBlackCells() {
        return blackCells;
    }

    public ArrayList<Coords> getOrangeCells() {
        return orangeCells;
    }

    public ArrayList<Coords> getGreenCells() {
        return greenCells;
    }

    public ArrayList<Coords> getBlueCells() {
        return blueCells;
    }

    public ArrayList<Coords> getYellowCells() {
        return yellowCells;
    }

    public ArrayList<Coords> getRedCells() {
        return redCells;
    }

    public ArrayList<Coords> getPlayerSpawn() {
        return playerSpawn;
    }

    public GridPane getMainMap() {
        return mainMap;
    }

    public Treasures getQuest() {
        return quest;
    }

    public void setQuest() {
        Treasures quest;
        Random r = new Random();
        int num = r.nextInt(valuables.size());
        quest = valuables.get(num);
        this.quest = quest;
        questLabel.setText(quest.getName().toString());
    }
}