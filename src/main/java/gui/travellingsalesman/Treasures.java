package gui.travellingsalesman;

import java.util.Random;

public class Treasures {
    //attributes
    private treasure name;
    private int score;
    private Coords location;

    //constructor
    public Treasures(int num){
        setScore(num);
    }

    //setters and getters
    public treasure getName() {
        return name;
    }

    public void setName(treasure name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Coords getLocation() {
        return location;
    }

    public void setLocation(Coords location) {
        this.location = location;
    }

    //types of treasures
    enum treasure{
        DiamondRing,
        JewelEncrustedSword,
        GoldenGoblet,
        CrystalGoblets,
        WoodenBow,
        PaladinsShield,
        GoldenKey,
        DragonsScroll
    }
}
