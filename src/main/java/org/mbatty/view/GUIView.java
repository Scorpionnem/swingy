package org.mbatty.view;

import org.mbatty.model.Map;
import org.mbatty.model.entities.Entity;

import javax.swing.*;
import java.awt.*;

public class GUIView extends View {
    JPanel statsPanel = new JPanel();
    JPanel mapPanel = new JPanel();
    JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

    public GUIView() {
        this.window = new JFrame();
        window.setSize(500, 500);
        window.setTitle("swingy");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        statsPanel.setPreferredSize(new Dimension(150, 500));
        mapPanel.setMinimumSize(new Dimension(0, 0));

        splitPane.setLeftComponent(statsPanel);
        splitPane.setRightComponent(mapPanel);

        window.add(splitPane);

        window.setVisible(true);
    }

    public void    renderMap(Map map) {

        mapPanel.removeAll();
        mapPanel.setLayout( new GridLayout(map.getSize(), map.getSize()));

        for (int y = 0; y < map.getSize(); y++) {
            for (int x = 0; x < map.getSize(); x++) {
                Entity entity = map.getTile(x, y);
                if (entity != null && entity.getName().equals("enemy"))
                    mapPanel.add(new Button("\uD83E\uDDCC"));
                else if (entity != null)
                    mapPanel.add(new Button("\uD83E\uDD34\uD83C\uDFFB"));
                else
                    mapPanel.add(new Button("empty"));
            }
        }

        mapPanel.revalidate();
        mapPanel.repaint();
    }

    public void    renderPlayerStats(Entity player) {
        statsPanel.removeAll();
        statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.Y_AXIS));

        statsPanel.add(new JLabel(player.getName() + " (" + player.getClassType() + ")"));
        statsPanel.add(new JLabel("Level " + player.getLevel() + " (" + player.getExperience() + "/" + player.xpToLevelUp() + ")"));
        statsPanel.add(new JLabel(player.getHealth() + " HP"));
        statsPanel.add(new JLabel(player.getAttack() + " Attack Damage"));
        statsPanel.add(new JLabel(player.getDefense() + " Defense"));

        statsPanel.revalidate();
        statsPanel.repaint();
    }

    public void    renderInfo(String str) {
        JOptionPane.showMessageDialog(null, str);
    }
}
