package org.mbatty.controller;

import org.mbatty.model.GameState;
import org.mbatty.model.Model;
import org.mbatty.view.TerminalView;
import org.mbatty.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;

public class TerminalController extends Controller {
    private Model model;
    private View view;
    private Scanner scanner = new Scanner(System.in);
    public TerminalController(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    private String  readInput(List<String> validArgs) {
        while (true) {
            System.out.println("Available input: " + validArgs);
            System.out.print(":> ");
            String  input = scanner.nextLine();
            if (input.equals("quit")) {
                System.out.println("Closing program.");
                exit(1);
            }
            else if (validArgs.contains(input))
                return (input);
            else
                System.out.println("Invalid input, please try again.");
        }
    }
    private String  readInputNoUnknown(List<String> validArgs) {
        System.out.println("Available input: " + validArgs);
        System.out.print(":> ");

        String input = scanner.nextLine();

        if (input.equals("quit")) {
            System.out.println("Closing program.");
            exit(1);
        }

        return (input);
    }

    public void    processInput() {
        System.out.println("What do you want to do now?");
        List<String> valid = List.of("north", "south", "west", "east", "quit");
        String input = readInput(valid);

        switch (input)
        {
            case "north":
                model.move(0, -1);
                break ;
            case "south":
                model.move(0, 1);
                break ;
            case "west":
                model.move(-1, 0);
                break ;
            case "east":
                model.move(1, 0);
                break ;
            default:
                System.out.println("Unknown instruction.");
        }
    }

    public void processCreateCharacter()
    {
        GameState   state = model.getGameState();

        System.out.println("Let's create a new character!");

        System.out.println("Enter your character's name:");
        List<String> valid = List.of("any_name", "quit");
        state.setPlayerName(readInputNoUnknown(valid));

        System.out.println("Enter your character's class:");
        valid = List.of("any_class", "quit");
        System.out.println(readInputNoUnknown(valid));
    }

    public void processLoadSaveFile()
    {
        System.out.println("Choose a file to open.");
        List<String> valid = List.of("file_name", "quit");
        String saveFile = readInputNoUnknown(valid);

        System.out.println("Loading file " + saveFile);
    }

    public void processStartGame() {
        System.out.println("Create/Load a character.");
        List<String> valid = List.of("load", "create", "quit");
        String input = readInput(valid);

        switch (input)
        {
            case "load":
                processLoadSaveFile();
                break ;
            case "create":
                processCreateCharacter();
                break ;
            default:
                System.out.println("Unknown instruction.");
        }
        System.out.println("Starting a new game!");
        model.startGame();
    }
}
