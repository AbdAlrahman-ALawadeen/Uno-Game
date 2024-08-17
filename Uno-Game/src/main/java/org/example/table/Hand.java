package org.example.table;

import java.util.ArrayList;

public class Hand extends CardCollection{

    public Hand(){
        super();
    }

    public ArrayList getCards(){
        return cardList;
    }

    public int getNumCards() {
        return this.cardList.size();
    }

    public boolean addCard(UnoCard card) {
        return this.cardList.add(card);
    }

    @Override
    public UnoCard getCard(int index){
        try{
            return (UnoCard) this.cardList.remove(index);
        } catch(IndexOutOfBoundsException e){
            return null;
        }
    }

    public String showCard(int index){
        try{
            return this.cardList.get(index).toString();
        } catch(IndexOutOfBoundsException e){
            return null;
        }
    }
}
