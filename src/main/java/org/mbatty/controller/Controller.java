package org.mbatty.controller;

/*

    The controller has access to both, it takes inputs and sends them to the model and tells the view to redraw

*/

import org.mbatty.model.Map;
import org.mbatty.model.Model;
import org.mbatty.model.entities.Entity;
import org.mbatty.model.entities.Knight;
import org.mbatty.view.View;

abstract public class Controller {
    protected Model model;
    protected View view;

    abstract public void processInput();
    abstract public void processStartInput();

    public void startGame() {
        processStartInput();
        model.startNewLevel();
    }

    protected void handleMove(int dx, int dy) {
        Entity player = model.getGameState().getPlayer();
        Map map = model.getGameState().getMap();


        Entity tile = map.getTile(player.getPosX() + dx, player.getPosY() + dy);
        if (tile != null)
            model.startFight(player, tile);
        if (player.alive())
            model.moveTo(player, dx, dy);
        else
            System.out.println("OH NO I DIED!");

        if (map.onEdge(player.getPosX(), player.getPosY())) {
            System.out.println("YOU WIN");
            model.startNewLevel();
        }
    }
}
