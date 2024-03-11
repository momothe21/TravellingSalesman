package gui.travellingsalesman;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

public class Player{
    //attributes
    private final int playernum;
    private Coords playerCoords;
    private ArrayList<Coords> playerPath=new ArrayList<Coords>();
    private double score=0;
    private double wealth=0;
    private double power=0;
    private Weapons weapon;
    private GridPane map;

    private Circle self;

    //method for when player encounters item
    protected void encounteredItem(){}

    //method for when player encounters Treasure
    protected void encounteredTreasure(){

    }


    //method to move player
    protected void move(int X, int Y) {
        int newX = getPlayerCoords().getX() + X;
        int newY = getPlayerCoords().getY() + Y;

        // Check if the new coordinates are within the bounds of the grid
        if (newX >= 0 && newX <= 9 && newY >= 0 && newY <= 9) {
            setPlayerCoords(new Coords(newX, newY));

            // Remove the player circle from its previous position in the grid
            map.getChildren().remove(getSelf());

            // Check if the player circle is null
            if (getSelf() == null) {
                System.out.println("The player circle is null for some reason");
            } else {
                // Add the player circle to the new coordinates in the grid
                map.add(getSelf(), getPlayerCoords().getX(), getPlayerCoords().getY());
            }
        }
    }


    //method to move player to spawn
    @FXML
    protected void spawn(GridPane main){
        //setting the map when spawning
        map = main;

        if(getPlayerCoords().getX()<0){
            if(playernum%2!=0){
                setPlayerCoords(new Coords(0,0));
                playerPath.add(new Coords(0,0));
            }else {
                setPlayerCoords(new Coords(9,9));
                playerPath.add(new Coords(9,9));
            }
            if(getSelf() == null){
                System.out.println("The player circle is null for some reason");
            }else {
                map.add(getSelf(),getPlayerCoords().getX(),getPlayerCoords().getY());
            }
        }
    }

    //enum weapons
    enum Weapons{
        Sword,
        Bow,
        Hammer
    }

    //construct

    public Player(Circle player, Coords coords, int num) {
        setSelf(player);
        setPlayerCoords(coords);
        playernum = num;
    }


    //setter getter

    public Coords getPlayerCoords() {
        return playerCoords;
    }

    public void setPlayerCoords(Coords playerCoords) {
        this.playerCoords = playerCoords;
    }

    public ArrayList<Coords> getPlayerPath() {
        return playerPath;
    }

    public void setPlayerPath(ArrayList<Coords> playerPath) {
        this.playerPath = playerPath;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score += score;
    }

    public double getWealth() {
        return wealth;
    }

    public void setWealth(double wealth) {
        this.wealth += wealth;
    }

    public double getPower() {
        return power;
    }

    public void setPower(double power) {
        this.power = power;
    }

    public Weapons getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapons weapon) {
        this.weapon = weapon;
    }

    public Circle getSelf() {
        return self;
    }

    public void setSelf(Circle self) {
        this.self = self;
    }

    public int getPlayernum() {
        return playernum;
    }
}
