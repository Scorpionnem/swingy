package org.mbatty.controller;

import org.mbatty.model.GameState;
import org.mbatty.model.Model;
import org.mbatty.model.entities.Knight;
import org.mbatty.view.TerminalView;
import org.mbatty.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;

public class TerminalController extends Controller {
    private Scanner scanner = new Scanner(System.in);
    public TerminalController(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public void processStartInput() {
        System.out.println("Lets start, what do you want to do?");
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
                System.out.println("Unknown instruction.");
        }
    }

    private void    createCharacter() {
        List<String> valid = List.of("any_name", "quit");
        String name = readInputNoUnknown(valid);

        model.getGameState().setPlayer(new Knight(name));
    }

    public void    processInput() {
        System.out.println("What do you want to do now?");
        List<String> valid = List.of("north", "south", "west", "east", "quit");
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
            default:
                System.out.println("Unknown instruction.");
        }
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
}
