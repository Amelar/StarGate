package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;


public class Kirajzol extends JPanel implements KeyListener{


    private BufferedImage image;


    Kirajzol() {
        addKeyListener(this);
        image = null;
    }

    public JPanel getPanel(){
        return this;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int x = 0; x < Math.sqrt(Game.allPos.size()); x++) {
            for (int y = 0; y < Math.sqrt(Game.allPos.size()); y++) {
                Position pos = Game.allPos.get(new Point(x, y));
                int posdb = pos.getDb();
                for (int z = 0; z < posdb; z++) {
                    String tipus = pos.getStack().get(z).getTipus();
                    switch (tipus) {
                        case "box":
                            image = Kepbetolt.box;
                            break;
                        case "bullet_blue":
                            image = Kepbetolt.bullet_blue;
                            break;
                        case "bullet_green":
                            image = Kepbetolt.bullet_green;
                            break;
                        case "bullet_red":
                            image = Kepbetolt.bullet_red;
                            break;
                        case "bullet_yellow":
                            image = Kepbetolt.bullet_yellow;
                            break;
                        case "floor":
                            image = Kepbetolt.floor;
                            break;
                        case "jaffaUP":
                            image = Kepbetolt.jaffaf;
                            break;
                        case "jaffaRIGHT":
                            image = Kepbetolt.jaffaj;
                            break;
                        case "jaffaDOWN":
                            image = Kepbetolt.jaffal;
                            break;
                        case "jaffaLEFT":
                            image = Kepbetolt.jaffab;
                            break;
                        case "oneilUP":
                            image = Kepbetolt.oneilf;
                            break;
                        case "oneilRIGHT":
                            image = Kepbetolt.oneilj;
                            break;
                        case "oneilDOWN":
                            image = Kepbetolt.oneill;
                            break;
                        case "oneilLEFT":
                            image = Kepbetolt.oneilb;
                            break;
                        case "replicator":
                            image = Kepbetolt.replicator;
                            break;
                        case "scale":
                            image = Kepbetolt.scale;
                            break;

                        case "sgredup":
                            image = Kepbetolt.sgredup;
                            break;
                        case "sgredright":
                            image = Kepbetolt.sgredright;
                            break;
                        case "sgreddown":
                            image = Kepbetolt.sgreddown;
                            break;
                        case "sgredleft":
                            image = Kepbetolt.sgredleft;
                            break;

                        case "sgblueup":
                            image = Kepbetolt.sgblueup;
                            break;
                        case "sgblueright":
                            image = Kepbetolt.sgblueright;
                            break;
                        case "sgbluedown":
                            image = Kepbetolt.sgbluedown;
                            break;
                        case "sgblueleft":
                            image = Kepbetolt.sgblueleft;
                            break;

                        case "sggreenup":
                            image = Kepbetolt.sggreenup;
                            break;
                        case "sggreenright":
                            image = Kepbetolt.sggreenright;
                            break;
                        case "sggreendown":
                            image = Kepbetolt.sggreendown;
                            break;
                        case "sggreenleft":
                            image = Kepbetolt.sggreenleft;
                            break;

                        case "sgyellowup":
                            image = Kepbetolt.sgyellowup;
                            break;
                        case "sgyellowright":
                            image = Kepbetolt.sgyellowright;
                            break;
                        case "sgyellowdown":
                            image = Kepbetolt.sgyellowdown;
                            break;
                        case "sgyellowleft":
                            image = Kepbetolt.sgyellowleft;
                            break;


                        case "trap":
                            image = Kepbetolt.trap;
                            break;
                        case "wall":
                            image = Kepbetolt.wall;
                            break;
                        case "zpm":
                            image = Kepbetolt.zpm;
                            break;
                        case "doortrue":
                            image = Kepbetolt.dooropen;
                            break;
                        case "doorfalse":
                            image = Kepbetolt.doorclose;
                            break;
                        default:
                            image = Kepbetolt.box;
                            break;
                    }

                    g.drawImage(image, x * 30, y * 30, 30, 30, this);
                    this.invalidate();
                }
            }


        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_CONTROL:    //doboz felv?tele/let?tele Jaff?val
                Game.Jaffa.use();
                break;
            case KeyEvent.VK_F:            //doboz felv?tele/let?tele Oneill ezredessel
                Game.Oneil.use();
                break;
            case KeyEvent.VK_NUMPAD1:    //Jaff?val l?v?s, z?ld
                Game.Jaffa.shoot(Color.Green);
                break;
            case KeyEvent.VK_NUMPAD2:    //Jaff?val l?v?s, piros
                Game.Jaffa.shoot(Color.Red);
                break;
            case KeyEvent.VK_E:        //Ezredesel l?v?s, s?rga
                Game.Oneil.shoot(Color.Yellow);
                break;
            case KeyEvent.VK_R:            //Ezredessel l?v?s, k?k
                Game.Oneil.shoot(Color.Blue);
                break;
            case KeyEvent.VK_UP:        //Jaff?val mozg?s, fel
                Game.Jaffa.move(Direction.UP);
                break;
            case KeyEvent.VK_LEFT:        //Jaff?val mozg?s, balra
                Game.Jaffa.move(Direction.LEFT);
                break;
            case KeyEvent.VK_RIGHT:        //Jaff?val mozg?s, jobbra
                Game.Jaffa.move(Direction.RIGHT);
                break;
            case KeyEvent.VK_DOWN:        //Jaff?val mozg?s, le
                Game.Jaffa.move(Direction.DOWN);
                break;
            case KeyEvent.VK_W:            //Ezredessel mozg?s, fel
                Game.Oneil.move(Direction.UP);
                break;
            case KeyEvent.VK_A:            //Ezredessel mozg?s, balra
                Game.Oneil.move(Direction.LEFT);
                break;
            case KeyEvent.VK_D:            //Ezredessel mozg?s, jobbra
                Game.Oneil.move(Direction.RIGHT);
                break;
            case KeyEvent.VK_S:            //Ezredessel mozg?s, le
                Game.Oneil.move(Direction.DOWN);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}