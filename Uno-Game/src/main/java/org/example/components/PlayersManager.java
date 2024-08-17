package org.example.components;

import org.example.system.Player;

import java.util.ArrayList;

public class PlayersManager {
    private static ArrayList<Player> players;
    private boolean toRight;
    private boolean canAdd;
    private int currentPlayer;
    private int nextPlayer;
    private static PlayersManager playersManager = null;

    public static PlayersManager getInstance() {
        if(playersManager == null){
            playersManager = new PlayersManager();
        }
        return playersManager;
    }

    public PlayersManager(){
        players = new ArrayList<>();
        this.toRight = true;
        this.canAdd = true;
        this.currentPlayer = 0;
        this.nextPlayer = 0;
    }

    public void addPlayer(Player player){
        if(this.canAdd){
            players.add(player);
            if(players.size() == 2){
                this.nextPlayer = 1;
            }
            if(players.size() == 10){
                canAdd = false;
            }
        }
        else{
            System.out.println("Number of players full.");
        }
    }

    public int getNumPlayers() {
        return players.size();
    }

    public void changeDirection(){
        this.toRight ^= true;
        this.rotateNextPlayer();
    }

    public void rotate() {
        this.currentPlayer = this.nextPlayer;
        this.rotateNextPlayer();
    }

    public void rotateNextPlayer(){
        if(this.toRight)
            this.nextPlayer = (this.nextPlayer + 1) % players.size();
        else
            this.nextPlayer = (this.nextPlayer - 1 + players.size()) % players.size();
    }

    public Player getCurrent() {
        Player player;

        try {
            player = players.get(currentPlayer);
        }
        catch (IndexOutOfBoundsException e){
            System.out.println("There isn't any players.");
            player = null;
        }
        return player;
    }

    public Player getNext(){
        return players.get(this.nextPlayer);
    }

    public Player lookForWinner(){
        for (Player player : players){
            if (player.getNumCard() == 0){
                return player;
            }
        }

        return null;
    }
}
