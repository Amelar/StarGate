package Game;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Created by RGergo on 2016. 05. 15..
 */
public class Kepbetolt {

    public static BufferedImage box;
    public static BufferedImage bullet_blue;
    public static BufferedImage bullet_green;
    public static BufferedImage bullet_red;
    public static BufferedImage bullet_yellow;
    public static BufferedImage floor;
    public static BufferedImage jaffaf;
    public static BufferedImage jaffaj;
    public static BufferedImage jaffal;
    public static BufferedImage jaffab;
    public static BufferedImage oneilf;
    public static BufferedImage oneilj;
    public static BufferedImage oneill;
    public static BufferedImage oneilb;
    public static BufferedImage replicator;
    public static BufferedImage scale;


    public static BufferedImage sgredup;
    public static BufferedImage sgredright;
    public static BufferedImage sgreddown;
    public static BufferedImage sgredleft;

    public static BufferedImage sgblueup;
    public static BufferedImage sgblueright;
    public static BufferedImage sgbluedown;
    public static BufferedImage sgblueleft;

    public static BufferedImage sgyellowup;
    public static BufferedImage sgyellowright;
    public static BufferedImage sgyellowdown;
    public static BufferedImage sgyellowleft;

    public static BufferedImage sggreenup;
    public static BufferedImage sggreenright;
    public static BufferedImage sggreendown;
    public static BufferedImage sggreenleft;

    public static BufferedImage trap;
    public static BufferedImage wall;
    public static BufferedImage zpm;
    public static BufferedImage dooropen;
    public static BufferedImage doorclose;

    Kepbetolt(){
        try {
            box = ImageIO.read(new BufferedInputStream(new FileInputStream("lib\\Design\\box.png")));

            bullet_blue = ImageIO.read(new BufferedInputStream(new FileInputStream("lib\\Design\\bullet_blue.png")));
            bullet_green = ImageIO.read(new BufferedInputStream(new FileInputStream("lib\\Design\\bullet_green.png")));
            bullet_red = ImageIO.read(new BufferedInputStream(new FileInputStream("lib\\Design\\bullet_red.png")));
            bullet_yellow = ImageIO.read(new BufferedInputStream(new FileInputStream("lib\\Design\\bullet_yellow.png")));

            floor = ImageIO.read(new BufferedInputStream(new FileInputStream("lib\\Design\\floor.jpg")));
            jaffaf = ImageIO.read(new BufferedInputStream(new FileInputStream("lib\\Design\\jaffaf.png")));
            jaffaj = ImageIO.read(new BufferedInputStream(new FileInputStream("lib\\Design\\jaffaj.png")));
            jaffal = ImageIO.read(new BufferedInputStream(new FileInputStream("lib\\Design\\jaffal.png")));
            jaffab = ImageIO.read(new BufferedInputStream(new FileInputStream("lib\\Design\\jaffab.png")));
            oneilf = ImageIO.read(new BufferedInputStream(new FileInputStream("lib\\Design\\Oneilf.png")));
            oneilj = ImageIO.read(new BufferedInputStream(new FileInputStream("lib\\Design\\Oneilj.png")));
            oneill = ImageIO.read(new BufferedInputStream(new FileInputStream("lib\\Design\\Oneill.png")));
            oneilb = ImageIO.read(new BufferedInputStream(new FileInputStream("lib\\Design\\Oneilb.png")));
            replicator = ImageIO.read(new BufferedInputStream(new FileInputStream("lib\\Design\\replicator.png")));
            scale = ImageIO.read(new BufferedInputStream(new FileInputStream("lib\\Design\\scale.jpg")));

            sgredup = ImageIO.read(new BufferedInputStream(new FileInputStream("lib\\Design\\sgredup.jpg")));
            sgredright = ImageIO.read(new BufferedInputStream(new FileInputStream("lib\\Design\\sgredright.jpg")));
            sgreddown = ImageIO.read(new BufferedInputStream(new FileInputStream("lib\\Design\\sgreddown.jpg")));
            sgredleft = ImageIO.read(new BufferedInputStream(new FileInputStream("lib\\Design\\sgredleft.jpg")));

            sggreenup = ImageIO.read(new BufferedInputStream(new FileInputStream("lib\\Design\\sggreenup.jpg")));
            sggreenright = ImageIO.read(new BufferedInputStream(new FileInputStream("lib\\Design\\sggreenright.jpg")));
            sggreendown = ImageIO.read(new BufferedInputStream(new FileInputStream("lib\\Design\\sggreendown.jpg")));
            sggreenleft = ImageIO.read(new BufferedInputStream(new FileInputStream("lib\\Design\\sggreenleft.jpg")));

            sgyellowup = ImageIO.read(new BufferedInputStream(new FileInputStream("lib\\Design\\sgyellowup.jpg")));
            sgyellowright = ImageIO.read(new BufferedInputStream(new FileInputStream("lib\\Design\\sgyellowright.jpg")));
            sgyellowdown = ImageIO.read(new BufferedInputStream(new FileInputStream("lib\\Design\\sgyellowdown.jpg")));
            sgyellowleft = ImageIO.read(new BufferedInputStream(new FileInputStream("lib\\Design\\sgyellowleft.jpg")));

            sgblueup = ImageIO.read(new BufferedInputStream(new FileInputStream("lib\\Design\\sgblueup.jpg")));
            sgblueright = ImageIO.read(new BufferedInputStream(new FileInputStream("lib\\Design\\sgblueright.jpg")));
            sgbluedown = ImageIO.read(new BufferedInputStream(new FileInputStream("lib\\Design\\sgbluedown.jpg")));
            sgblueleft = ImageIO.read(new BufferedInputStream(new FileInputStream("lib\\Design\\sgblueleft.jpg")));

            trap = ImageIO.read(new BufferedInputStream(new FileInputStream("lib\\Design\\trap.png")));
            wall = ImageIO.read(new BufferedInputStream(new FileInputStream("lib\\Design\\wall.jpg")));
            zpm = ImageIO.read(new BufferedInputStream(new FileInputStream("lib\\Design\\zpm.jpg")));
            dooropen = ImageIO.read(new BufferedInputStream(new FileInputStream("lib\\Design\\door-opened.jpg")));
            doorclose = ImageIO.read(new BufferedInputStream(new FileInputStream("lib\\Design\\door.jpg")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
