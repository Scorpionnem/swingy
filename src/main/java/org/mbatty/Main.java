package org.mbatty;

import org.mbatty.controller.Controller;
import org.mbatty.controller.TerminalController;
import org.mbatty.model.Model;
import org.mbatty.view.TerminalView;
import org.mbatty.view.View;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        Model   model = new Model();
        View view = new TerminalView();
        Controller controller = new TerminalController(model, view);

        controller.startGame();

        while (true) {
            controller.processInput();

            view.renderMap(model.getGameState().getMap());
            view.renderPlayerStats(model.getGameState().getPlayer());
        }
    }
}

/*
    Start: the player chooses to load/create a player, either case we prompt them to give us the right values

    We then start a game and render it.

    Each turn we ask for input, model gets modified accordingly.

    If death, leave.

    If win, go to next level,

    if on enemy, simulate fight (50/50 to who starts the fight then just attack, attack...)
*/
