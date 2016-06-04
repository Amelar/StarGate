package Game;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import Game.Color;


/**
 * Created by RGergo on 2016. 05. 02..
 */
public class Control {

    static JPanel panel;
    static JFrame myFrame;

    static Kirajzol kipanel;
    private Game jatek;
    Control(final Game game){
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            
          
        jatek = game;
        myFrame = new JFrame();
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setSize(300,300);
        myFrame.setResizable(false);
        myFrame.setVisible(true);

        
        panel = new JPanel();
        myFrame.add(panel);
        JButton button = new JButton("Inditas");

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

              myFrame.remove(panel);
			  Game.setPanel(myFrame);
				jatek.Init("lib\\Map\\palya.txt");
                double x = Math.sqrt(Game.allPos.size());
                Math.ceil(x);
                x=x*30+30;
                myFrame.setSize((int)x-25,(int)x);
              Game.changedMap();
            }
        });
        panel.add(button);
        panel.repaint();
        panel.invalidate();
            }
        });
    }
    public static JPanel getPanel(){
        return panel;
    }
}
