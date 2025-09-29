package org.mbatty.controller;

import org.mbatty.model.Model;
import org.mbatty.model.entities.Entity;
import org.mbatty.model.entities.Knight;
import org.mbatty.view.View;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;
import static java.lang.System.load;

public class GUIController extends Controller {
    public GUIController(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public boolean processFightInput() {
        List<String> valid = List.of("fight", "run", "quit");
        String input = readInput(valid);

        switch (input)
        {
            case "fight":
                return (true);
            case "run":
                return (false);
            default:
                view.renderInfo("Unknown instruction");
        }
        return (false);
    }

    public void processStartInput() throws FileNotFoundException {
        List<String> valid = List.of("create", "load", "quit");
        String input = readInput(valid);

        String[] array = new String[valid.size()];
        valid.toArray(array);

        switch (input)
        {
            case "create":
                createCharacter();
                break ;
            case "load":
                loadCharacter();
                break ;
            default:
                view.renderInfo("Unknown instruction");
        }
    }

    private void    loadCharacter() throws FileNotFoundException {
        String file = JOptionPane.showInputDialog(null, "Enter your name:");

        if (file == null)
            exit(1);
        model.getGameState().setPlayer(new Entity(file));
    }

    private void    createCharacter() {
        String name = JOptionPane.showInputDialog(null, "Enter your name:");

        if (name == null)
            exit(1);
        model.getGameState().setPlayer(new Knight(name));
    }

    public void    processInput() {
        List<String> valid = List.of("north", "south", "west", "east", "switchview", "save", "quit");
        String input = readInput(valid);

        switch (input)
        {
            case "north":
                handleMove(0, -1);
                break ;
            case "south":
                handleMove(0, 1);
                break ;
            case "west":
                handleMove(-1, 0);
                break ;
            case "east":
                handleMove(1, 0);
                break ;
            case "switchview":
                switchView = true;
                break ;
            case "save":
                try {
                    model.getGameState().getPlayer().exportFile();
                } catch (IOException e) {
                    exit (1);
                }
                break ;
            default:
                view.renderInfo("Unknown instruction");
        }
    }

    private String  readInput(List<String> validArgs) {
        String[] options = new String[validArgs.size()];
        validArgs.toArray(options);

        int choice = JOptionPane.showOptionDialog(
                null,
                "What do you want to do?",
                "Choose an option",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        if (choice == -1 || options[choice].equals("quit"))
            exit(1);

        return (options[choice]);
    }
    private String  readInputNoUnknown(List<String> validArgs) {
        return ("");
    }
}
