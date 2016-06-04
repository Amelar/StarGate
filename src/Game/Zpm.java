package Game;

public class Zpm extends GameElements {

    //beallitja az aactual positiont
    //es a stackhez adja
    public Zpm(Position pos) {
        actual = pos;
        actual.addStack(this);
    }

    //ha ralepnek akkor a playerhez adja
    //a zpmet es kitorli a stackbol
    public void stepOn(Moveable target) {
        if(target.getType()!= Type.Rep) {
            ((Player) target).addZpm(this);
            actual.removeStack(this);
            actual = null;
        }
    }

    public String getTipus(){
        return "zpm";
    }

}