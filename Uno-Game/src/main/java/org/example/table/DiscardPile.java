package org.example.table;

import java.util.ArrayList;

public class DiscardPile extends CardCollection<UnoCard>{

    public UnoCard getTopCard() {
        UnoCard card;
        try{
            card = cardList.get(cardList.size() - 1);
        }
        catch(IndexOutOfBoundsException e){
            card = null;
        }
        return card;
    }

    public ArrayList<UnoCard> takeCardsBack() {
        ArrayList<UnoCard> deck = new ArrayList<>();
        while(cardList.size() > 1){
            deck.add(cardList.remove(0));
        }
        return deck;
    }

    public void initialize(UnoCard card){
        if(this.cardList.isEmpty()){
            this.cardList.add(card);
            System.out.println(card.getColor() + " " + card.getValue());
        }
    }

    @Override
    public boolean addCard(UnoCard card) {
        if(this.getTopCard() == null){
            return false;
        }
        if(!this.getTopCard().match(card)){
            return false;
        }
        return this.cardList.add(card);
    }

    @Override
    public UnoCard getCard(int index) {
        throw new UnsupportedOperationException("Operation not allowed.");
    }

}
