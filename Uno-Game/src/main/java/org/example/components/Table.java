package org.example.components;

import org.example.table.DiscardPile;
import org.example.table.UnoCard;
import org.example.table.UnoDeck;
import org.example.table.WildCards;

import java.util.ArrayList;

public class Table {
    private final DiscardPile discardPile;
    private final UnoDeck unoDeck;
    private static Table table = null;

    public Table(){
        this.discardPile = new DiscardPile();
        this.unoDeck = new UnoDeck();
    }

    public static Table getInstance() {
        if(table == null){
            table = new Table();
        }
        return table;
    }

    public void prepareGame() {
        if(discardPile.getNumCard() != 0){
            ArrayList<UnoCard> tempDiscardPile = discardPile.takeCardsBack();
            for(int index = 0; index < tempDiscardPile.size(); index++)
                this.unoDeck.addCard(tempDiscardPile.remove(0));
        }

        unoDeck.shuffle();

        UnoCard card = this.unoDeck.getCard(0);
        while(card.getColor().equals("BLACK")){
            this.unoDeck.addCard(card);
            card = this.unoDeck.getCard(0);
        }

        discardPile.initialize(card);
    }

    public UnoCard showTopCard(){
        return this.discardPile.getTopCard();
    }

    public boolean pushCard(UnoCard card){
        if(card == null){
            return false;
        }
        return this.discardPile.getTopCard().match(card);
    }

    public UnoCard pullCard() {
        UnoCard card = null;
        if(this.unoDeck.isEmpty()){
            ArrayList <UnoCard> list = discardPile.takeCardsBack();
            if(!list.isEmpty()){
                for(int i = 0; i < list.size(); i++){
                    card = list.remove(0);
                }
                if(card.getValue().equals("WILD DRAW FOUR")){
                    this.unoDeck.addCard(new WildCards("WILD DRAW FOUR"));
                }
                else if(card.getValue().equals("WILD")){
                    this.unoDeck.addCard(new WildCards("WILD"));
                }
                else{
                    this.unoDeck.addCard(card);
                }
                this.unoDeck.shuffle();
            }
            else{
                System.out.println("NO MORE CARDS AVAILABLE");
                return null;
            }
        }
        return this.unoDeck.getCard(0);
    }
}
