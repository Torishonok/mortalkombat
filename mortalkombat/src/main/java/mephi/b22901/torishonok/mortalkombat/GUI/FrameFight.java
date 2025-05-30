package mephi.b22901.torishonok.mortalkombat.GUI;

import javax.swing.*;
import java.awt.*;

public class FrameFight extends JFrame {

    public FrameFight(int locationCount, String playerName){
    
        FightPanel panel = new FightPanel(locationCount, playerName);
        setAllComponentsOpaqueFalse(panel);
       
        setTitle("Бой");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLocationRelativeTo(null);
        setSize(new Dimension(1200,800));
        setLocationRelativeTo(null);
        

        getContentPane().add(panel);
        setVisible(true);
        setResizable(true);
    }
     private void setAllComponentsOpaqueFalse(Container container) {
        for (Component component : container.getComponents()) {
            if (component instanceof JComponent) {
                ((JComponent) component).setOpaque(false);
            }
            if (component instanceof Container) {
                setAllComponentsOpaqueFalse((Container) component);
            }
        }
    }
}
