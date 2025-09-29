package org.mbatty;

import org.mbatty.controller.Controller;
import org.mbatty.controller.GUIController;
import org.mbatty.controller.TerminalController;
import org.mbatty.model.Model;
import org.mbatty.view.GUIView;
import org.mbatty.view.TerminalView;
import org.mbatty.view.View;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

import static java.lang.System.exit;

public class Main {
    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Invalid arguments");
            return ;
        }

        Model   model = new Model();
        Controller controller;

        if (args[0].equals("gui")) {
            controller = new GUIController(model, new GUIView());
        }
        else if (args[0].equals("console")) {
            controller = new TerminalController(model, new TerminalView());
        } else {
            System.out.println("Invalid arguments");
            return ;
        }

        String  currentView = args[0];

        try {
            controller.startGame();
        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
            exit(1);
        }
        controller.render();

        while (true) {
            controller.processInput();

            if (controller.getSwitchView()) {
                if (currentView.equals("gui")) {
                    controller.closeGUI();
                    currentView = "console";
                    controller = new TerminalController(model, new TerminalView());
                }
                else if (currentView.equals("console")) {
                    currentView = "gui";
                    controller = new GUIController(model, new GUIView());
                }
            }

            controller.render();
        }
    }
}
