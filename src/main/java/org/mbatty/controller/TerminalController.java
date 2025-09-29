package org.mbatty.controller;

import org.mbatty.model.Model;
import org.mbatty.model.entities.EntityException;
import org.mbatty.model.entities.Player;
import org.mbatty.view.View;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;

public class TerminalController extends Controller {
    private Scanner scanner = new Scanner(System.in);
    public TerminalController(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public boolean processFightInput() {
        view.renderInfo("An enemy wants to fight you! What will you do");
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

    public void processStartInput() throws EntityException {
        view.renderInfo("Lets start, what do you want to do?");
        List<String> valid = List.of("create", "load", "quit");
        String input = readInput(valid);

        switch (input)
        {
            case "create":
                createCharacter();
                break ;
            case "load":
                break ;
            default:
                view.renderInfo("Unknown instruction");
        }
    }

    private void    createCharacter() throws EntityException {
        List<String> valid = List.of("any_name", "quit");
        String name = readInputNoUnknown(valid);
        valid = List.of("any_class", "quit");
        String classType = readInputNoUnknown(valid);

        model.getGameState().setPlayer(new Player(name, classType));
    }

    public void    processInput() throws EntityException {
        view.renderInfo("What do you want to do now?");
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
        while (true) {
            view.renderInfo("Available input: " + validArgs);
            System.out.print(":> ");
            String  input = scanner.nextLine();
            if (input.equals("quit")) {
                exit(1);
            }
            else if (validArgs.contains(input))
                return (input);
            else
                System.out.println("Invalid input, please try again.");
        }
    }
    private String  readInputNoUnknown(List<String> validArgs) {
        view.renderInfo("Available input: " + validArgs);
        System.out.print(":> ");

        String input = scanner.nextLine();

        if (input.equals("quit")) {
            exit(1);
        }

        return (input);
    }
}
