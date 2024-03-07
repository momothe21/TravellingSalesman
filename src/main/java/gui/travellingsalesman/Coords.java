package gui.travellingsalesman;

import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Coords {
    private int x;
    private int y;

    //constructer
    public Coords(int x, int y){
        setX(x);
        setY(y);
    }

    //setters/getters
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    //method to calculate distance between tiles(used to use Euclidean distance, updated to absolutes and ors)
    protected int distanceCalc(ArrayList<Coords> other){
        int distance=10,cur,x1,x2,dx,y1,y2,dy;
        for(int i = 0; i<other.size();i++){
            //getting current distance coords
            x1 = this.getX();
            y1 = this.getY();

            //getting other coords
            x2 = other.get(i).getX();
            y2 = other.get(i).getY();

            //getting differences
            dx = x1 - x2;
            dy = y1 - y2;

            //getting the absolute
            if(dx<0){
                dx=dx*-1;
            }
            if(dy<0){
                dy=dy*-1;
            }


            //getting the current distance
            cur = Math.max(dx,dy);

            //getting the more restrictive number
            if(cur<distance){
                distance = cur;
            }
        }

        return distance;
    }
}
