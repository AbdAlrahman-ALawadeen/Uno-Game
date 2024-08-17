package org.example.game;

import org.example.components.EffectsController;
import org.example.components.PlayersManager;
import org.example.components.Table;
import org.example.system.Player;
import org.example.table.UnoCard;


public class FirstVariation extends Game{
    private final PlayersManager players;
    public final EffectsController effect;
    private final Table table;
    private final int numCardsIn = 7;
    private static FirstVariation match = null;

    public FirstVariation(){
        this.players = PlayersManager.getInstance();
        this.table = Table.getInstance();
        this.effect = new EffectsController(this.table,this.players);
    }

    public static FirstVariation getInstance(){
        if(match == null){
            match = new FirstVariation();
        }
        return match;
    }

    @Override
    public void init() {
        this.table.prepareGame();
    }

    @Override
    public void play() {
        if(!verifyNumPlayers()){
            System.out.println("Number of players is too low or too high.");
            return;
        }
        this.dealCards(numCardsIn);
    }

    @Override
    public boolean verifyNumPlayers() {
        return this.players.getNumPlayers() > 1 && this.players.getNumPlayers() <= 10;
    }

    @Override
    public void dealCards(int numCardDeal) {
        Player player = this.players.getCurrent();
        int counter;
        String name = player.getName();
        UnoCard card;

        do{
            for(counter = 0; counter < numCardsIn; counter++){
                card = this.table.pullCard();
                if(card == null){
                    return;
                }
                player.takeCard(card);
            }
            this.players.rotate();
            player = this.players.getCurrent();
        }while(!player.getName().equals(name));
    }

    @Override
    public void finish() {
        Player winner = players.lookForWinner();

        if(winner == null){
            System.out.println("\nNO WINNER IN THIS GAME. =(");
        }
        else{
            System.out.println("\nCONGRATULATIONS " + winner.getName() + "! \nYOU DEFEATED " + players.getNumPlayers() + " OPONENTS.");
        }
        System.out.println("\nTHANK YOU FOR PLAYING. " + "\nCOME BACK ANYTIME YOU WANT.\n");
    }

}
