package Game;

public class Floor extends GameElements {

    //Floor konstruktora: be�ll�tja az actualt a kapott positionra
    //majd a stack elemeihez adja a floort
    public Floor(Position pos) {
        this.actual = pos;
        actual.addStack(this);
    }

    //Kell egy default konstruktor is
    public Floor() {
    }

    //A playert�l elveszi a dobozt, majd a box actualj�t a saj�tj�ra �ll�tja
    //�s a stack elemeihez pusholja az elemet
    public void putDown(Player player) {
        Box box = player.getBox();
        box.actual = actual;
        actual.addStack(box);
    }

    //L�trehozhat� rajta zpm, trueval t�r vissza
    public Boolean zpmPossible(){
        return true;
    }

    public String getTipus(){
        return "floor";
    }

}