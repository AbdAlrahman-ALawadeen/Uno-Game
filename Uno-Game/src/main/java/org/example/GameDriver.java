package org.example;

import org.example.game.FirstVariation;
import org.example.interpreter.Interpreter;
import org.example.system.SignUpPlayers;


public class GameDriver {
    public static void main(String[] args) {
        Interpreter inter = new Interpreter();
        SignUpPlayers sign = new SignUpPlayers();
        FirstVariation game = FirstVariation.getInstance();

        System.out.println("---------------------------------------"
                + "-----------------------------------------");
        System.out.println("\t\t\t\tUNO CARD GAME");
        System.out.println("---------------------------------------"
                + "-----------------------------------------");
        game.init();
        while(!sign.sign());
        game.play();
        inter.readCommands(game.effect);
        game.finish();

    }
}