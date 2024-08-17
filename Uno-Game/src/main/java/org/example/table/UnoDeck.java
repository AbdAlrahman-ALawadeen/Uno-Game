package org.example.table;

import java.util.ArrayList;
import java.util.Random;

public class UnoDeck extends CardCollection<UnoCard> {
    private static final Random sourceRandom = new Random();

    public UnoDeck() {
        for(NormalCard.Color color : NormalCard.Color.values()) {
            for (int numCard = 0; numCard < 2; numCard++) {
                for (NormalCard.Value value : NormalCard.Value.values()) {
                    if (value.equals(NormalCard.Value.ZERO) && numCard == 1) {
                        continue;
                    }
                    else {
                        cardList.add(new NormalCard(value, color));
                    }
                }
            }
        }

        ArrayList<String> Values;
        ActionsCard card = new ActionsCard("", "");
        Values = card.getValues();
        for(NormalCard.Color color : NormalCard.Color.values()) {
            for (int numCard = 0; numCard < 2; numCard++) {
                for (String value : Values) {
                    cardList.add(new ActionsCard(value, color.name()));
                }
            }
        }

        WildCards cards = new WildCards("");
        Values = cards.getValues();
        for (String value : Values) {
            for (int cnt = 0; cnt < 4; cnt++) {
                cardList.add(new WildCards(value));
            }
        }

    }

    public void shuffle() {
        for(int index = cardList.size() - 1; index > 0; index--){
            int tempIndex = sourceRandom.nextInt(index);
            UnoCard card = cardList.get(tempIndex);
            cardList.set(tempIndex,cardList.get(index));
            cardList.set(index, card);
        }
    }

    @Override
    public boolean addCard(UnoCard card) {
        return this.cardList.add(card);
    }

    @Override
    public UnoCard getCard(int index) {
        try{
            return this.cardList.remove(index);
        } catch(IndexOutOfBoundsException e){
            return null;
        }
    }
}
