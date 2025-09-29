package org.mbatty.controller;

/*

    The controller has access to both, it takes inputs and sends them to the model and tells the view to redraw

*/

import org.mbatty.model.Map;
import org.mbatty.model.Model;
import org.mbatty.model.entities.Entity;
import org.mbatty.model.entities.EntityException;
import org.mbatty.view.View;

import java.io.FileNotFoundException;

import static java.lang.System.exit;

abstract public class Controller {
    protected Model model;
    protected View view;
    protected Boolean   switchView = false;

    abstract public void processInput() throws EntityException;
    abstract public void processStartInput() throws FileNotFoundException, EntityException;
    abstract public boolean processFightInput();

    public Boolean  getSwitchView() {
        return (this.switchView);
    }

    public void closeGUI() {
        view.closeWindow();
    }

    public void startGame() throws FileNotFoundException, EntityException {
        processStartInput();
        model.startNewLevel();
    }

    public void render() {
        view.renderMap(model.getGameState().getMap());
        view.renderPlayerStats(model.getGameState().getPlayer());
    }

    protected void handleMove(int dx, int dy) throws EntityException {
        Entity player = model.getGameState().getPlayer();
        Map map = model.getGameState().getMap();

        Entity tile = map.getTile(player.getPosX() + dx, player.getPosY() + dy);
        if (tile != null) {
            Boolean wantsToFight = processFightInput();
            if (wantsToFight || (!wantsToFight && Math.random() > 0.5)) {
                model.startFight(player, tile);
            }
            else
                return ;
        }
        if (player.alive())
            model.moveTo(player, dx, dy);
        else {
            view.renderInfo("You died!");
            exit(1);
        }

        if (map.onEdge(player.getPosX(), player.getPosY())) {
            view.renderInfo("You won this map!");
            model.startNewLevel();
        }
    }
}
