package Game;


import static Game.Direction.*;

public class WE extends GameElements{

    private StarGate starGate;
    private Game game;

    //Osztaly default konstruktora, alap ertekeket allitbe
    //megtortennek a stack muveletek
    public WE(Position pos, Game game){
        actual = pos;
        actual.addStack(this);
        starGate = null;
        this.game = game;
    }

    //Ha letezik csillagkapu es olyan akar ralepni aki letrehozta vagy replikator
    //�s letezik parja aminek az iranyban nincs akadaly, akkor igaz ertekkel ter vissza
    //minden mas esetben falssal ter vissza
    public Boolean movePossible(Moveable target){
        if(starGate != null){
            Color sGateColor = starGate.getColor();
            Type gtarget = target.getType();
            StarGate pair = starGate.getPair();
            if(pair == null){
                return false;
            }
            Position next = pair.actual.getPosition(pair.getDirection());
            Boolean nextpossible = next.movePossible(target);
            Boolean direction = Directions(target);
            if(     (gtarget == Type.Oneil && (sGateColor == Color.Blue || sGateColor == Color.Yellow)) ||
                    (gtarget == Type.Jaffa && (sGateColor == Color.Red || sGateColor == Color.Green)) ||
                    (gtarget == Type.Rep)
                    ){
                if(pair != null){
                    if(nextpossible && direction){
                        return true;
                    }
                }
            }
            return false;
        }
        else{
            return false;
        }
    }

    public Boolean Directions(Moveable target){
        if(target.getType() == Type.Rep){
            Replicator t = (Replicator)target;
            Direction tdir = t.getDir();
            Direction sgdir = starGate.getDirection();
            if( t.getDir() == Direction.DOWN && sgdir == Direction.UP ||
                t.getDir() == Direction.UP && sgdir == Direction.DOWN ||
                t.getDir() == Direction.RIGHT && sgdir == Direction.LEFT ||
                t.getDir() == Direction.LEFT && sgdir == Direction.RIGHT
                    ){
                return true;
            }
            else return false;
        }
        else if(target.getType() == Type.Jaffa || target.getType() ==Type.Oneil){
            Player t = (Player) target;
            Direction tdir = t.getDir();
            Direction sgdir = starGate.getDirection();
            if( t.getDir() == Direction.DOWN && sgdir == Direction.UP ||
                    t.getDir() == Direction.UP && sgdir == Direction.DOWN ||
                    t.getDir() == Direction.RIGHT && sgdir == Direction.LEFT ||
                    t.getDir() == Direction.LEFT && sgdir == Direction.RIGHT
                    ){
                return true;
            }
            else return false;
        }
        else return false;
    }
    //Ha ralepnek akkor atallitja a ralepett target
    //positionjat arra helyre ami a parjanak az iranyaban van es
    //iranyba helyezi a playert
    public void stepOn(Moveable target){
        StarGate pair = starGate.getPair();
        Position next = pair.getPosition().getPosition(pair.getDirection());
        target.setPosition(next);
        if(target.getType() != Type.Rep)((Player)target).setDirection(pair.getDirection());
        next.stepOn(target);
    }

    //Igazzal ter vissza rajta letrehozhato csillagkapu
    public Boolean sGatePossible(){
        return true;
    }

    //A golyo szinenek megfelelo csillagkaput alakit ki
    //a listaba is beleteszi es beallitja a kapunak a parjat
    //ha az mar letezik
    public void createSg(Bullet bullet){
        if(starGate == null) {
            Direction bdir = bullet.getDirection();
            Direction sGatedir = bdir;
            switch (bdir) {
                case DOWN:
                    sGatedir = UP;
                    break;
                case UP:
                    sGatedir = DOWN;
                    break;
                case RIGHT:
                    sGatedir = LEFT;
                    break;
                case LEFT:
                    sGatedir = RIGHT;
                    break;
            }
            StarGate bStarGate = new StarGate(bullet.getColor(), sGatedir, game, actual);
            starGate = bStarGate;
            actual.removeStack(bullet);
        }
        else{
            actual.removeStack(bullet);
        }
    }

    //Visszaadja a WE csillagkapujat
    //ha nincs null, ha van akkor azt
    public StarGate getStarGate(){
        return starGate;
    }

    //A csillagkapuja torlodik a WE
    //azaz nullba allitja
    public void releaseStarGate(){
        starGate = null;
        Game.changedMap();
    }

    public Boolean zpmPossible(){
        return false;
    }

    public String getTipus(){
        if(starGate == null) return "wall";
        else return "sg"+starGate.getColor().toString().toLowerCase()+starGate.getDirection().toString().toLowerCase();
    }
}