package org.example.interpreter;

import org.example.components.EffectsController;
import org.example.components.PlayersManager;
import org.example.components.Table;
import org.example.system.Player;
import org.example.table.NormalCard;
import org.example.table.UnoCard;

public class Commands {
    private final PlayersManager players = PlayersManager.getInstance();
    private final Table table = Table.getInstance();


    public boolean playCard(String[] fields, EffectsController effect){
        if(fields[1].startsWith("WILD") && (fields.length < 3
                ||!NormalCard.validColor(fields[2]))){
            return true;
        }

        if(this.playerPlayCard(fields[1])){
            if(fields[1].startsWith("WILD")){
                return !this.applyEffect(fields[2], effect);
            }
            else{
                this.applyEffect(effect);
            }
            return false;
        }
        return true;
    }

    public boolean verifyEndGame(){
        return isEmptyHand();
    }

    public void draw(){
        playerTakeCard();
        System.out.println("\n---------------------------------------"
                + "-----------------------------------------");
        System.out.println("NEW CARD ADDED IN THE END OF THE LIST.");
    }

    public boolean applyEffect(String wildColor, EffectsController effect){

        if(!effect.setWildColor(wildColor)){
            return false;
        }
        this.applyEffect(effect);
        return true;
    }

    public void applyEffect(EffectsController effect){
        UnoCard card = this.table.showTopCard();
        card.applyEffect(effect);
    }

    public void pass(boolean unoAdvertise){
        if(unoAdvertise){
            if(players.getCurrent().getNumCard() != 1){
                this.playerTakeCard();
                this.playerTakeCard();
            }
            else{
                this.announceUno();
            }

        }
        else{
            if(players.getCurrent().getNumCard() == 1){
                this.playerTakeCard();
                this.playerTakeCard();
            }
        }
        players.rotate();
    }

    public void playerTakeCard(){
        Player currentPlayer = this.players.getCurrent();
        UnoCard card = this.table.pullCard();
        currentPlayer.takeCard(card);

    }

    public boolean playerPlayCard(String name){
        Player currentPlayer = this.players.getCurrent();
        UnoCard card = currentPlayer.playCard(name);
        if(card == null){
            return false;
        }
        if(!this.table.pushCard(card)){
            currentPlayer.takeCard(card);
            return false;
        }
        return true;
    }

    public boolean isEmptyHand(){
        return (0 == players.getCurrent().getNumCard());
    }

    private void announceUno(){
        System.out.println("Unoooooooooo");
    }

}