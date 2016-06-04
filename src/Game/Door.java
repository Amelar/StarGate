package Game;



public class Door extends GameElements{

    private Boolean open;

    //Door konstruktora actual �rt�ke param�ter szerint
    //actualho ad�s �s alapb�l a kaput z�rtra �ll�tja
    public Door(Position pos){
        this.actual = pos;
        actual.addStack(this);
        open = false;
    }

    //Ha nyitott r�l�phet�, ha z�rt nem r�l�phet�
    public Boolean movePossible(Moveable target){
        return  open;
    }

    //Ajt� �llapot�nak megv�ltoztat�a, kiirat�sa f�jlba
    public void changeStatus(){
        if(open){
            open = false;
        }
        else{
            open = true;
        }
    }

    //Ha nyitott �tmegy rajta a goly�, ez�rt false-t ad vissza
    //ha z�rt akkor trueval �r vissza
    public Boolean sGatePossible(){
        if(open){
            return false;
        }
        else{
            return true;
        }
    }

    public String getTipus(){
        return "door"+open.toString();
    }

    public void DoorClose(){
        open =false;
    }

    //Z�rt �llapotn�l trueval t�r vissza, ez�rt megh�v�dik ez a f�ggv�ny
    //Itt csak t�r�lj�k a goly�t a removestak seg�ts�g�vel
    public void createSg(Bullet bullet){
        actual.removeStack(bullet);
    }
}