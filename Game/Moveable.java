package Game;

public abstract class Moveable extends GameElements {

    //leszedi a stackrol az elemet, beallitja neki
    //az uj position-t melyet parameterbol kap
    //es hozza adja az uj positionhoz az aktualis elemet
    public void setPosition(Position pos) throws NullPointerException{
        actual.removeStack(this);
        actual= pos;
        actual.addStack(this);
    }

    //Belole szarmazo osztalyok feluldefinialjak
    public Type getType(){
        return null;
    }
}