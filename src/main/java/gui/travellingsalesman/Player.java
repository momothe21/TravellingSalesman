package gui.travellingsalesman;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Player{
    //attributes
    private final int playernum;
    private static int playercounter;
    private Coords playerCoords;
    private ArrayList<Coords> playerPath;
    private int score;
    private int wealth;
    private double power;
    private Weapons weapon;

    private Circle self;

    private int moves;

    //method to move player to spawn
    @FXML
    protected void spawn(GridPane main){
        if(getPlayerCoords().getX()<0){
            if(playernum==1){
                setPlayerCoords(new Coords(0,0));
            }else {
                setPlayerCoords(new Coords(9,9));
            }
        }
        if(getSelf() == null){
            System.out.println("The player circle is null for some reason");
        }else {
            main.add(getSelf(),getPlayerCoords().getX(),getPlayerCoords().getY());
        }
    }

    //enum weapons
    enum Weapons{
        Sword,
        Bow,
        Hammer
    }

    //construct

    public Player(Circle player, Coords coords) {
        setSelf(player);
        setPlayerCoords(coords);
        playercounter++;
        playernum = playercounter;
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getWealth() {
        return wealth;
    }

    public void setWealth(int wealth) {
        this.wealth = wealth;
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
}
