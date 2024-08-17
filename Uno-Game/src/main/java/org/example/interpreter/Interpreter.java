package org.example.interpreter;

import org.example.components.EffectsController;
import org.example.components.PlayersManager;
import org.example.system.Player;

import java.util.Arrays;

public class Interpreter {

    private int state = 0;
    private final Input input = new Input(System.in);
    private final Commands command = new Commands();
    private final PlayersManager players = PlayersManager.getInstance();

    public void readCommands(EffectsController effect) {
        while (true) {
            Player currentPlayer = players.getCurrent();
            if (currentPlayer == null) {
                System.out.println("No current player available.");
                return;
            }

            showPlayerStatus(currentPlayer);

            String[] fields = input.readFields();
            fields = normalizeFields(fields);

            if (fields.length == 0) continue;
            if ("E".equals(fields[0])) return;

            handleInput(fields, effect);
        }
    }

    private void showPlayerStatus(Player player) {
        System.out.println(player.getName());
        player.showCards();
        System.out.print("> Write P for Play (and card name you want to play), D for Draw, PS for Pass, E for Exit: ");
    }

    private String[] normalizeFields(String[] fields) {
        return Arrays.stream(fields)
                .map(String::toUpperCase)
                .toArray(String[]::new);
    }

    private void handleInput(String[] fields, EffectsController effect) {
        switch (state) {
            case 0 -> handleStateZero(fields, effect);
            case 1 -> handleStateOne(fields, effect);
        }
    }

    private void handleStateZero(String[] fields, EffectsController effect) {
        switch (fields[0]) {
            case "P" -> handlePlayCommand(fields, effect);
            case "D" -> {
                command.draw();
                state = 1;
            }
            case "PS" -> {
                System.out.println("You shall not pass.");
                state = 0;
            }
            default -> System.out.println("I couldn't understand you.");
        }
    }

    private void handleStateOne(String[] fields, EffectsController effect) {
        switch (fields[0]) {
            case "PLAY" -> handlePlayCommand(fields, effect);
            case "PASS" -> {
                command.pass(false);
                state = 0;
            }
            case "DRAW" -> System.out.println("Sorry, but you can only draw once a turn.");
            default -> System.out.println("I couldn't understand you. Let's try again.");
        }
    }

    private void handlePlayCommand(String[] fields, EffectsController effect) {
        if (fields.length < 2) {
            System.out.println("Hey, you forgot to choose a card.");
            return;
        }

        if (command.playCard(fields, effect)) {
            System.out.println("Try again.");
        } else {
            if (command.verifyEndGame()) return;

            boolean unoCalled = fields.length == 4 && "UNO".equals(fields[2]);
            command.pass(unoCalled);

            state = 0;
        }
    }
}
