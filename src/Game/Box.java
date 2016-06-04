package Game;

public class Box extends Moveable{

    private int weight;

    // Box konstruktora, be�ll�tja a box s�ly�t, a positioj�t be�ll�tja �s
    //a position stackj�hez adja, Box l�trehoz�skor, ha m�rlegen van kell
    //a boxputscale, hogy be�ll�tsa a m�rleg s�ly�t helyesen
    public Box(Position pos, int weight) {
        actual = pos;
        actual.addStack(this);
        this.weight = weight;
        actual.boxPutScale(this);
    }

    //False-l t�r vissza nem engedi a playert r�l�pni
    public Boolean movePossible(Moveable ma) {
        return false;
    }

    //Playerhez ad�dik az addboxon kereszt�l �s t�rl�dik a stackr�l
    //�s az actual �rt�k�t nullra �ll�tja mert Playern�l lesz elt�rolva
    public void pickUp(Player player) {
        actual.removeStack(this);
        actual = null;
        player.addBox(this);
    }

    //visszaadja a doboz s�ly�t
    public int getWeight() {
        return weight;
    }

    //Be�ll�tja az �j kapott poz�ci�ra a dobozt
    public void setPosition(Position pos) {
        this.actual = pos;
        actual.addStack(this);
    }

    public String getTipus(){
        return "box";
    }
}