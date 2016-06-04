package Game;


public class Trap extends Floor {

    //Trap alapertelmezett konstruktora
    //alap beallitasok tortennek meg
    public Trap(Position pos) {
        actual = pos;
        actual.addStack(this);
    }

    //ralepeskor ha replikator rep la, akkor
    //trap torlodik a stackrol es a helyere egy floor elemet
    //hoz letre, ha player lep ra akkor torli a stackrol
    //es meghivja a gameover-t.
    public void stepOn(Moveable target) {
        if(target.getType() == Type.Rep){
            actual.removeStack(this);
            Floor floor = new Floor(actual);
            Replicator rep = (Replicator) target;
            rep.stop();
        }
        else {
            Player player = (Player) target;
            actual.removeStack(player);
            player.gameOver();
        }
    }

    //Playertol elkeri a dobozt, ezaltal torlodik a doboz
    //mert getboxban megtortenik a removestack
    public void putDown(Player player) {
        player.getBox();
        //System.out.println("Doboz megsemmisitve");
    }

    public String getTipus(){
        return "trap";
    }


}