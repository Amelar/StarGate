package Game;



import java.util.Random;

public class Replicator extends Moveable implements Runnable{

    private Type type;
    private Boolean flag;
    private Direction dir;

    public Replicator(Position pos){
        actual = pos;
        type = Type.Rep;
        actual.addStack(this);
        flag = true;
        dir = Direction.DOWN;
        (new Thread(this)).start();
    }

    public Boolean sGatePossible(){
        return true;
    }
    public void createSg(Bullet bullet){
        actual.removeStack(bullet);
        stop();
    }
    public void stop(){
        flag = false;
        actual.removeStack(this);
    }
    public void move(){ //Programban random kell majd
        Random rand = new Random();
        int random = rand.nextInt(4)+1;
        Direction dir = Direction.LEFT;
        this.dir = dir;
        switch (random){
            case 1: dir = Direction.UP;
                break;
            case 2: dir = Direction.RIGHT;
                break;
            case 3: dir = Direction.DOWN;
                break;
            case 4: dir = Direction.LEFT;
                break;
        }
        Position next = actual.getPosition(dir);
        if(next != null) {
            Boolean can = next.moveAble(this);
            if (can) {
                this.setPosition(next);
                actual.stepOn(this);
            } else {
                move();
            }
        }
    }
    public Type getType(){
        return type;
    }

    @Override
    public void run() {
        try {
            while (flag) {
                move();
                Thread.currentThread().sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getTipus(){
        return "replicator";
    }
    public Direction getDir(){return dir;}

}