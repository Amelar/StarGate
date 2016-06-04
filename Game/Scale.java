package Game;

public class Scale extends GameElements {

    private Door door;
    private int allWeight;
    private Player[] players;
    private int railing;


    //scale konstruktora, alap beallitasok tortennek meg
    public Scale(Position pos) {
        actual = pos;
        allWeight = 0;
        players = null;
        actual.addStack(this);
        railing = 2;
    }

    //lelep a player a merlegrol es elkeri a sulyat
    //ha a korlat ala csokken becsukja a kaput
    public void stepDown(Player player) {
        int weight = player.getWeight();
        int lastweight = allWeight;
        allWeight -= weight;
        if(allWeight<railing && lastweight>=railing){
            door.changeStatus();
        }
    }

    //Ralepeskor elkeri a ralepett sulyat a sajatjahoz adja
    // es ha a korlat fole kerul akkor meghivja a doorra
    //a changestatust
    public void stepOn(Moveable target) {
        if(target.getType() != Type.Rep) {
            int weight = ((Player) target).getWeight();
            allWeight += weight;
            if (allWeight >= railing) {
                door.changeStatus();
            }
        }
    }

    //Doboz sulyat levonaja a sajat sulyabol
    //es ha a korlat ala kerul akkor megvaltoztata az ajto
    //allapotat
    public void checkScale(Box box) {
        int bweight = box.getWeight();
        allWeight -= bweight;
        if(allWeight < railing){
            door.DoorClose();
        }
    }

    //letrehozaskor ha alapbol van rajta doboz akkor
    //a sulyat itt adja hozza
    public void boxPutScale(Box box){
        int bweight = box.getWeight();
        allWeight += bweight;

    }

    //ha player dobozt tesz a merlegre elkeri a sulyat
    //ha a korat fole novekszik, akkor megvaltoztatja az
    //ajto allapotat es elvegzi a stack muveleteket
    public void putDown(Player player) {
        Box box = player.getBox();
        box.actual = actual;
        actual.addStack(box);
        int bweight = box.getWeight();
        allWeight += bweight;
        if(allWeight >= railing){
            door.changeStatus();
        }

    }

    //beallitja merleghez tartozo ajtot
    public void setDoor(Door d){
        door = d;
        if(allWeight >= railing) door.changeStatus();
    }

    public String getTipus(){
        return "scale";
    }



}