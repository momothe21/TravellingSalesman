package gui.travellingsalesman;

import javafx.scene.input.KeyCode;

import java.util.ArrayList;

public class EventController {
    //attributes
    protected static ArrayList<Coords> walls = new ArrayList<Coords>();
    protected static ArrayList<Coords> traps = new ArrayList<Coords>();
    protected static ArrayList<Coords> items = new ArrayList<Coords>();
    protected static ArrayList<Coords> treasure = new ArrayList<Coords>();
    protected static ArrayList<Coords> markets = new ArrayList<Coords>();
    protected static ArrayList<Coords> castle = new ArrayList<Coords>();


    //method to check if two players meet
    public static boolean isFight(Player player1, Player player2){
        return player1.getPlayerCoords().equals(player2.getPlayerCoords());
    }

    //method to check if currently in a special space
    //traps
    public static boolean isTrap(Coords space){
        return traps.contains(space);
    }

    //markets
    public static boolean isMarket(Coords space){
        return markets.contains(space);
    }

    //items
    public static boolean isItem(Coords space){
        return items.contains(space);
    }

    //treasure
    public static boolean isTreasure(Coords space){
        return treasure.contains(space);
    }

    //castle
    public static boolean isCastle(Coords space){
        return castle.contains(space);
    }

    //method to check if a coordinate is going towards a barrier
    public static boolean canMove(Coords space, KeyCode dir){
        boolean flag = true;
        Coords copy = null;
        switch (dir){
            case UP, W:
                copy = new Coords(space.getX(),space.getY()-1);
                break;
            case DOWN, S:
                copy = new Coords(space.getX(),space.getY()+1);
                break;
            case LEFT, A:
                copy = new Coords(space.getX()-1,space.getY());
                break;
            case RIGHT, D:
                copy = new Coords(space.getX()+1,space.getY());
                break;
            default:
                break;
        }

        //checking if the new coordinate is enterable
        if((walls.contains(copy))){
            flag = false;
        }

        return flag;
    }


    //setters and getters
    public static ArrayList<Coords> getWalls() {
        return walls;
    }

    public static void setWalls(ArrayList<Coords> walls) {
        EventController.walls = walls;
    }

    public static ArrayList<Coords> getTraps() {
        return traps;
    }

    public static void setTraps(ArrayList<Coords> traps) {
        EventController.traps = traps;
    }

    public static ArrayList<Coords> getItems() {
        return items;
    }

    public static void setItems(ArrayList<Coords> items) {
        EventController.items = items;
    }

    public static ArrayList<Coords> getTreasure() {
        return treasure;
    }

    public static void setTreasure(ArrayList<Coords> treasure) {
        EventController.treasure = treasure;
    }

    public static ArrayList<Coords> getMarkets() {
        return markets;
    }

    public static void setMarkets(ArrayList<Coords> markets) {
        EventController.markets = markets;
    }

    public static ArrayList<Coords> getCastle() {
        return castle;
    }

    public static void setCastle(ArrayList<Coords> castle) {
        EventController.castle = castle;
    }
}
