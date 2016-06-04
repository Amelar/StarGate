package Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;

public class Bullet extends Moveable{

    private Direction dir;
    private Color color;
    private Timer timer;

    //Bullet konstruktora be�ll�tja az positionj�t, a directionj�t
    //�s a colorj�t a kapott param�terek szerint, stackhez rendel
    public Bullet(Position pos, Direction dir, Color color) {
        this.dir= dir;
        this.color = color;
        this.actual = pos;
        actual.addStack(this);





    }

    //Visszaadja a Bullet sz�n�t
    public Color getColor(){
        return color;
    }


    //Visszaadja a Goly� ir�ny�t
    public Direction getDirection(){
        return dir;
    }

    //actu�lisb�l t�rl�dik, �j actalt kap, annak stackj�hez adjuk
    //�t�ll�t�s ut�n pr�b�lunk csillagkaput csin�lin act poz�ci�ra
    @Override
    public void setPosition(Position pos) {
        actual.removeStack(this);
        actual= pos;
        actual.addStack(this);
        actual.createSgAble(this);
    }

    public void leptet(Position posget){

        Boolean finalvalue = false;
        for(int i=0;i<posget.getElements().size() && finalvalue != true;i++){
            Boolean result= posget.getElements().get(i).sGatePossible();
            if(result == true){
                finalvalue = true;
            }
        }
        if(finalvalue == true){
            posget.createSg(this);
        }
        else{
            Position pos = posget.getNeighbors().get(this.getDirection());
            if (pos != null) {
                this.setPosition(pos);
            } else {
                posget.removeStack(this);
            }
        }


    }

    public String getTipus(){
        return "bullet_"+getColor().toString().toLowerCase();
    }

}