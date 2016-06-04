package Game;

public abstract class GameElements {

    protected Position actual;


    //Defaut fuggvenyek, melyeket a belole szarmazok felulirnak
    public Boolean movePossible(Moveable target) {
        return true;
    }

    public void stepDown(Player player) {
    }

    public void stepOn(Moveable target) {
    }

    public Boolean sGatePossible() {
        return  false;
    }

    public void pickUp(Player player) {
    }

    public void checkScale(Box box) {
    }

    public void putDown(Player player) {
    }

    public Boolean zpmPossible() {
        return false;
    }

    public Position getPosition() {
        return actual;
    }
    public void  releaseStarGate(){
    }

    public void createSg(Bullet bullet) {
    }

    public void boxPutScale(Box box){}

    public String getTipus(){return "valami";}
}