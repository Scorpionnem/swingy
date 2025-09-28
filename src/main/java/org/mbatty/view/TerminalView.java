package org.mbatty.view;

import org.mbatty.model.Entity;
import org.mbatty.model.GameState;

public class TerminalView extends View {
    public void    render(GameState state) {

        for (int y = 0; y < state.getMapSize(); y++) {
            for (int x = 0; x < state.getMapSize(); x++) {
                Entity entity = state.getMap().getMap()[y][x];
                if (entity == state.getPlayer())
                    System.out.print("X");
                else if (entity != null)
                    System.out.print("E");
                else
                    System.out.print(".");
            }
            System.out.println();
        }

        System.out.println(state.getPlayerName() + " (" + state.getPlayerClass() + ")");
        System.out.println("Level " + state.getPlayerLevel() + " (" + state.getPlayerXP() + "/" + state.getPlayerXPToLevelUp() + ")");
        System.out.println(state.getPlayerHP() + " HP");
        System.out.println(state.getPlayerAttackDamage() + " Attack Damage");
        System.out.println(state.getPlayerDefense() + " Defense");
    }
}
