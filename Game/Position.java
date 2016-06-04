package Game;


import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Position extends GameElements {

    private Map<Direction,Position> neighbors;
    private ArrayList<GameElements> elements;
    public Point pos;

    public Position(Point point) {
        pos = point;
        elements = new ArrayList<>();
        neighbors = new HashMap<>();
    }

    public void addStack(GameElements gameElement) {

        elements.add(gameElement);
        Game.changedMap();
    }

    public void removeStack(GameElements gameElement) {
        elements.remove(gameElement);
        Game.changedMap();
    }

    public Position getPosition(Direction dir) {
        return neighbors.get(dir);
    }

    public void setPosition(Direction dir,Position pos) {
        neighbors.put(dir,pos);
    }

    public void boxPutScale(Box box) {
        for(int i=0;i<elements.size();i++){
            elements.get(i).boxPutScale(box);
        }
    }

    public Boolean moveAble(Moveable target) {
        Boolean finalvalue = true;
        for(int i=0;i<elements.size() && finalvalue != false;i++){
            Boolean result= elements.get(i).movePossible(target);
            if(result == false){
                finalvalue = false;
            }
        }
        return finalvalue;
    }

    public void releaseStarGate() {
        for(int i=0;i<elements.size();i++){
            elements.get(i).releaseStarGate();
        }
    }

    public void stepDown(Player player) {
        for(int i=0;i<elements.size();i++){
            elements.get(i).stepDown(player);
        }
    }

    public void stepOn(Moveable target) {
        for(int i=0;i<elements.size();i++){
            elements.get(i).stepOn(target);
        }
    }
    public ArrayList<GameElements> getStack(){
        return elements;
    }

    public void createSgAble(Bullet bullet) {
        bullet.leptet(this);


    }

    public void createSg(Bullet bullet) {
        for(int i=0;i<elements.size();i++){
            elements.get(i).createSg(bullet);
        }
    }

    public ArrayList<GameElements> getElements(){
        return elements;
    }
    public Boolean zpmPossible() {
        Boolean finalvalue = true;
        Boolean ered;
        for (int i = 0; i < elements.size() && finalvalue == true; i++) {
            ered = elements.get(i).zpmPossible();
            finalvalue =  finalvalue & ered;
        }
        return finalvalue;
    }
    public void pickUp(Player player) {
        for(int i=0;i<elements.size();i++){
            elements.get(i).pickUp(player);
        }
    }

    public void checkScale(Box box) {
        for(int i=0;i<elements.size();i++){
            elements.get(i).checkScale(box);
        }
    }

    public void putDown(Player player) {
        for(int i=0;i<elements.size();i++){
            elements.get(i).putDown(player);
        }
    }
    public int getDb(){
        return elements.size();
    }
    public Map<Direction,Position> getNeighbors(){
        return neighbors;
    }
}