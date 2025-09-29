package org.mbatty.view;

import org.mbatty.model.Map;
import org.mbatty.model.entities.Entity;

public class TerminalView extends View {
    public void    renderMap(Map map) {

        for (int y = 0; y < map.getSize(); y++) {
            for (int x = 0; x < map.getSize(); x++) {
                Entity entity = map.getTile(x, y);
                if (entity != null && entity.getName().equals("enemy"))
                    System.out.print("\uD83E\uDDCC");
                else if (entity != null)
                    System.out.print("\uD83E\uDD34");
                else
                    System.out.print("  ");
            }
            System.out.println();
        }
    }

    public void    renderPlayerStats(Entity player) {
        System.out.println(player.getName() + " (" + player.getClassType() + ")");
        System.out.println("Level " + player.getLevel() + " (" + player.getExperience() + "/" + player.xpToLevelUp() + ")");
        System.out.println(player.getHealth() + " HP");
        System.out.println(player.getAttack() + " Attack Damage");
        System.out.println(player.getDefense() + " Defense");
    }

    public void    renderInfo(String str) {
        System.out.println(str);
    }
}
