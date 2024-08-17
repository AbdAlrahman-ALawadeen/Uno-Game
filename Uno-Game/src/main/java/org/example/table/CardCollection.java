package org.example.table;

import java.util.ArrayList;

public abstract class CardCollection <UnoType>{
    protected ArrayList<UnoType> cardList;
    public CardCollection(){
        cardList = new ArrayList<>();
    }
    public abstract boolean addCard(UnoCard card);
    public abstract UnoType getCard(int index);

    public int getNumCard() {
        return this.cardList.size();
    }

    public boolean isEmpty(){
        return getNumCard() == 0;
    }
}
