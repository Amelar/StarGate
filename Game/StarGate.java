package Game;


import static Game.Color.*;

public class StarGate extends GameElements{

    private StarGate pair;
    private Direction dir;
    private Color species;
    private Game game;

    //Az osztaly konstukrora
    //alap bellitasok tortennek meg benne
    public StarGate(Color color, Direction dir, Game game, Position actual){
        this.actual = actual;
        pair = null;
        this.dir = dir;
        species = color;
        this.game = game;
        StarGate old = game.getStarGates(species);
        if(old !=null) old.getPosition().releaseStarGate();
        game.setStarGate(species,this);
        setPair();
    }

    //a listabol kiszedi a parjat szin alapjan
    //es ha nem null akkor beallitja a csillagkapuk
    //parjait
    public void setPair(){
        game.setStarGate(species,this);
        Color cpair = Blue;
        switch(species){
            case Blue: cpair = Yellow;
                break;
            case Yellow: cpair = Blue;
                break;
            case Red: cpair = Green;
                break;
            case Green: cpair = Red;
                break;
        }
        StarGate back = game.getStarGates(cpair);
        if(back != null){
            pair = back;
            back.setPair(this);
        }
    }

    //beallitja a csillagkapu parjat
    public void setPair(StarGate starGate){
        pair = starGate;
    }

    //Visszaadja a csillagkapu parjat
    public StarGate getPair(){
        return pair;
    }

    //Visszaadja a csillagkap szinet
    public Color getColor(){
        return species;
    }

    //Visszakapja a csillagkapu iranyat
    public Direction getDirection(){
        return dir;
    }
}