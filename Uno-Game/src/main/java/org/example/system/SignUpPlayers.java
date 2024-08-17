package org.example.system;

import org.example.components.PlayersManager;

import java.util.InputMismatchException;
import java.util.Scanner;

public class SignUpPlayers {

    private Scanner input = new Scanner(System.in);
    private PlayersManager playerManager = PlayersManager.getInstance();

    public boolean sign(){
        int number;
        System.out.print("DIGIT THE NUMBER OF PLAYERS (2 TO 10): ");

        try{
            number = input.nextInt();

        } catch(InputMismatchException e){
            System.out.println("PLEASE INSERT A NUMBER.");
            input.next();
            return false;
        }

        while(number < 2 || number > 10){
            System.out.println("RANGER FROM 2 UNTIL 10 PLAYERS");
            System.out.print("DIGIT THE NUMBER OF PLAYER (2 TO 10): ");
            number =input.nextInt();
        }

        for(int i = 0 ; i < number ; i++){
            System.out.print("PLAYER " + String.valueOf(i+1) + ": ");
            playerManager.addPlayer(new Player(input.next()));
        }

        return true;
    }
}