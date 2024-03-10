package gui.travellingsalesman;

import java.util.Random;

public class Treasures {
    //attributes
    private treasure name;
    private int score;
    private Coords location;

    //overiding the equals method in order to make sure the quest and treasure are the same
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Coords)) {
            return false;
        }
        Treasures other = (Treasures) obj;
        return this.name == other.name;
    }


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
