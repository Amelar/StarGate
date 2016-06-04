package Game;


import java.util.ArrayList;

public class Player extends  Moveable {

    private Box box;
    private ArrayList<Zpm> zpms;
    private Direction dir;
    private Game game;
    private int weight;
    private Type type;
    private final int ZPM_WIN;

    //Player defaul ertekeit allitja be a konstruktoraban
    public Player(Position pos,Type type,int weight, Game game) {
        zpms = new ArrayList<>();
        box = null;
        dir = Direction.UP;
        this.game = game;
        this.weight = weight;
        this.type = type;
        actual = pos;
        actual.addStack(this);
        ZPM_WIN = 8;
    }

    //atallitja a player directionjat a parameterkent kapott
    //directionra
    public void setDirection(Direction dir){
        this.dir = dir;
    }

    //zpm listahoz adja a kapott zpmet es ha 2-vel
    //oszthatot vett fel akkor randomol egy uj zpmet
    //ha megegyezik a jatek vegehez szukseges
    //zpm szamhoz akkor meghivodik a gameover
    public void addZpm(Zpm zpm) {
        zpms.add(zpm);
        if(zpms.size() % 2 == 0){
            game.generateZpm();
        }
        if(zpms.size() == ZPM_WIN){
            game.GameOver(this.type);
        }
	/*	if(zpmszam != zpms.length)
		System.out.println("Zpm felveve");
		*/
    }

    //ha a playernel nincs box akkor a kapott dobozt a player
    //dobozahoz adja es a doboz sulyat hozza adja a sajat
    //sulyahoz, majd meghivjuk a checkscalet, leellenorizve
    //hogy merlegrol lett-e felveve
    public void addBox(Box box) {
        if(this.box == null){
            this.box = box;
            box.actual = null;
            weight += box.getWeight();
            actual.getPosition(dir).checkScale(box);
        }
		/*if(this.box != null)
		System.out.println("Doboz felveve");
		*/
    }

    //Kap egy iranyt es ha lehetseges arra menni akkor
    //atallitja a player positionjat es elvegzi a stack
    //muveleteket
    public void move(Direction dir) {
        if(this.dir == dir) {
            Position next = actual.getPosition(dir);
            Boolean value = next.moveAble(this);
            actual.stepDown(this);
            if (value) {
                setPosition(next);
            }
            actual.stepOn(this);
            if(!value)Game.changedMap();

        }
        else {
            this.dir = dir;
            Game.changedMap();
            //System.out.println(type.toString() +" new irany: "+ dir.toString()+"\n");
        }

    }

    //letrehoz egy uj golyot es meghivja createsgable-t
    //ami elinditja a golyo-t a kiloves iranyaba
    public void shoot(Color color) {
        Bullet bullet = new Bullet(actual,dir,color);
        actual.createSgAble(bullet);
    }

    //ha van nala doboz, akkor leteszi ha az iranyaban leteheto
    //ha nincs es van elotte akkor felveszi
    public void use() {
        Position next = actual.getPosition(dir);
        if(box != null){
            next.putDown(this);
        }
        else {
            next.pickUp(this);
        }
    }

    //Visszaadja a player sulyat
    public int getWeight() {
        return weight;
    }

    //meghivja a jatek veget atadva magat
    public void gameOver() {
        if(this.type == Type.Jaffa) game.GameOver(Type.Oneil);
        if(this.type == Type.Oneil) game.GameOver(Type.Jaffa);
    }

    //Visszaadja a fajtajat
    public Type getType() {
        return type;
    }

    public String getTipus(){
        return (type.toString()).toLowerCase()+dir.toString();
    }

    //player boxat nullaza majd visszaadja
    public Box getBox() {
        Box back = box;
        box = null;
        weight -= back.getWeight();
        //if(box == null)
        //	System.out.println("Doboz leteve");
        return back;
    }
    public Direction getDir(){
        return dir;
    }

}