package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.util.*;

public class Game {

    static Map<Point, Position> allPos;
    private static Kirajzol kipanel;
    private Map<Color, StarGate> sGates;
    private Position pos;
    public static Player Oneil;
    public static Player Jaffa;
    public Replicator rep;
    private boolean exist;
    private Control control;

    //Default ertekek beallitasa
    public Game() {
        pos = null;
        allPos = new LinkedHashMap<>();
        sGates = new LinkedHashMap<>();
        Oneil  =null;
        Jaffa = null;
        rep = null;
        exist = false;
        control = new Control(this);
    }

    //Fajlbol torteno beolvas, ebben soronkent beolvasunk
    //majd a sorokat egyesevel ertelmezunk, ha kierunk egy
    //[] keretb�l �s nem ugrottunk uj sorba akkor noveljuk
    //sorszamot, ha uj sor kovetkezik akkor az oszlopszamot noveljuk
    //[ kezdetekor keletkezik egy uj stack es a [] levo dolgok kerulnek egy
    // ilyen stackbe. Uj position eseten a regit az allposhoz adjuk
    public void Init(String path) {
        InputStream in = null;
        int row=0;
        int column=0;
        int scalecount=0;
        try {
            char c;
            in = new FileInputStream(path);
            Reader reader = new InputStreamReader(in);
            Map<Integer, Door> doors = new HashMap<Integer, Door>();
            Map<Integer, Scale> scales = new HashMap<Integer, Scale>();

            while ((c = (char)reader.read()) != '@'){
                switch(c)
                {
                    case '[':
                        pos = new Position(new Point(column, row));
                        allPos.put(new Point(column,row),pos);
                        break;
                    case ']':
                        column++;
                        break;
                    case '\n':
                        column=0;
                        row++;
                        break;
                    case ',':
                        break;
                    case 'b':
                        new Box(pos, 1);
                        break;
                    case 'd':
                        Door door = new Door(pos);
                        String doorID="";
                        if((c = (char)reader.read())=='(') {
                            while ((c = (char) reader.read()) != ')') {
                                doorID = doorID+c;
                            }
                        }
                        doors.put(Integer.valueOf(doorID), door);
                        break;
                    case 'f':
                        new Floor(pos);
                        break;
                    case 'o':
                        Oneil = new Player(pos, Type.Oneil, 1, this);
                        break;
                    case 'j':
                        Jaffa = new Player(pos, Type.Jaffa, 1, this);
                        break;
                    case 'r':
                        rep = new Replicator(pos);
                        break;
                    case 's':
                        Scale scale = new Scale(pos);
                        String scaleID="";
                        if((c = (char)reader.read())=='(') {
                            while ((c = (char) reader.read()) != ')') {
                                scaleID = scaleID+c;
                            }
                        }
                        scales.put(Integer.valueOf(scaleID), scale);
                        scalecount++;
                        break;
                    case 't':
                        new Trap(pos);
                        break;
                    case 'w':
                        WE wall = new WE(pos, this);
                        Color color = null;
                        Direction dir = null;
                        if((c = (char)reader.read())=='(') {
                            String koztes = new String();
                            while ((c = (char) reader.read()) != ')') {
                                koztes = koztes+c;
                                if (c == 'Y') {
                                    color = Color.Yellow;
                                } else if (c == 'B') {
                                    color = Color.Blue;
                                } else if (c == 'P') {
                                    color = Color.Red;
                                } else if (c == 'G') {
                                    color = Color.Green;
                                } else if (c == 'U') {
                                    dir = Direction.UP;
                                } else if (c == 'D') {
                                    dir = Direction.DOWN;
                                } else if (c == 'L') {
                                    dir = Direction.LEFT;
                                } else if (c == 'R') {
                                    dir = Direction.RIGHT;
                                }
                            }
                            if(!koztes.equals(""))wall.createSg(new Bullet(pos, dir, color));
                        }
                        break;
                    case 'z':
                        new Zpm(pos);
                        break;

                }
            }
            //Az ajtokat es a doorokat be kell allitani a lefutas vegen
            //mivel fajlbol betolteskor ez kozvetlen nm lehetseges
            for(int i=0; i<scalecount;i++)
            {
                scales.get(i).setDoor(doors.get(i));
            }
            //Egy positionra beallitja a szomszedokat
            setNeighbor();
            //System.out.println("Palya betoltes vege\n");


        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Jatek vegekor hivodik meg es megkapja a jatek veget jelento okot
    public void GameOver(Type type) {
        System.out.println(type.toString());
       // Control.myFrame.removeAll();
        JPanel jatekvege = new JPanel();
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints constraints  =  new GridBagConstraints();
        constraints.fill = GridBagConstraints.CENTER;
        gridbag.setConstraints(jatekvege, constraints);
        jatekvege.setLayout(gridbag);


        jatekvege.setVisible(true);
        jatekvege.setFocusable(true);
        JLabel label = new JLabel(type.toString()+" Nyert");
        label.setFont(new Font("Serif",Font.BOLD, 40));
        jatekvege.add(label);
        JButton gomb = new JButton("Uj jatek");
        gomb.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Container container = Control.myFrame.getContentPane();
                container.removeAll();
                container.add(Control.panel);
                container.validate();
                container.repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        jatekvege.add(gomb);

        Container contain = Control.myFrame.getContentPane();
        contain.removeAll();
        contain.add(jatekvege);
        contain.validate();
        contain.repaint();
    }

    //Egy positionra beallitja a szomszedokat a point �s allpos segitsegevel
    public void setNeighbor(){
        Position pos = null;
        Position tmp = null;
        Point point = null;

        for(Map.Entry<Point,Position> current: allPos.entrySet()){
            pos = current.getValue();
            point = current.getKey();

            tmp = allPos.get(new Point(point.x-1,point.y));
            pos.setPosition(Direction.LEFT,tmp);

            tmp = allPos.get(new Point(point.x+1,point.y));
            pos.setPosition(Direction.RIGHT,tmp);

            tmp = allPos.get(new Point(point.x,point.y+1));
            pos.setPosition(Direction.DOWN,tmp);

            tmp = allPos.get(new Point(point.x,point.y-1));
            pos.setPosition(Direction.UP,tmp);
        }
    }

    //az allpos kozul randomol egyet es ha teheto ra zpm, akkor megcsinalja
    //ha nem teheto ra akkor randolom egy ujabb helyet
    public void generateZpm() {
        Random random = new Random();
        int randomint = random.nextInt(allPos.size()-1);
        Position randompos = (Position)allPos.values().toArray()[randomint];
        exist = randompos.zpmPossible();
        if(exist){
            Zpm newZpm = new Zpm(randompos);
        }
        else{
            generateZpm();
        }
    }

    //a stargatehez adjaa szin serint a kapott csillagkaput
    public void setStarGate(Color color, StarGate starGate) {
        sGates.put(color,starGate);
    }

    //Szin alapjan visszaadja a listabol a kert csillagkaput
    public StarGate getStarGates(Color color) {
        StarGate gate = sGates.get(color);        if(gate != null) return gate;
        else{return null;}
    }

    //Kivulrol hivodi meg akkor ha valami torles vagy hozzaadas tortent a palyan
    //kiiratja positonok stack elemeit
    public static void changedMap(){
        kipanel.repaint();
    }
    public static void setPanel(JFrame myFrame){
        kipanel  = new Kirajzol();
        kipanel.setFocusable(true);
        myFrame.add(kipanel);
        myFrame.pack();
        myFrame.setVisible(true);
        kipanel.requestFocusInWindow();
        myFrame.setSize(600,600);

			/*for(Map.Entry<Point,Position> current: allPos.entrySet()){
			//System.out.print("pos[" + current.getKey().x +","+ current.getKey().y + "]: ");
			Stack<GameElements> temp = current.getValue().getStack();
			String elem;
			for(int i = 0; i<temp.size(); i++){
				elem = temp.get(i).getClass().getName().substring(5);
				if(elem.equals("WE")){
					if(((WE)temp.get(i)).getStarGate() == null){
						elem = "Wall";
					}
					else{
						elem = "StarGate(" + ((WE)temp.get(i)).getStarGate().getColor()+")";
					}
				}
				if(elem.equals("Player")) {
					if(((Player)temp.get(i)).getType() == Type.Jaffa){
						elem = "Jaffa";
					}
					else{
						elem = "Oneil";
					}
				}
				//System.out.print(elem + " ");

			}
			//System.out.println("");

		}
		//System.out.println();
		*/
    }
}