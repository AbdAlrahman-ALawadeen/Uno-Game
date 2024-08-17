package org.example.system;

import org.example.table.Hand;
import org.example.table.UnoCard;

public class Player {

    private final Hand p_hand;
    private final String p_name;

    public Player(String name) {
        this.p_name = name;
        this.p_hand = new Hand();
    }

    public String getName(){
        return this.p_name;
    }

    public int getNumCard(){
        return this.p_hand.getNumCards();
    }

    public void takeCard(UnoCard card) {
        boolean b = this.p_hand.addCard(card);
    }

    public UnoCard playCard(String cardName){
        int i = 0;
        while(i != this.p_hand.getNumCards()){
            if(this.p_hand.showCard(i).equals(cardName))
                return this.p_hand.getCard(i);
            i++;
        }
        return null;
    }

    public void showCards(){
        int numCards =  this.p_hand.getNumCards();
        String toPrint = "";
        int i;

        if(numCards == 0){
            System.out.print("EMPTY HAND.");
        }
        else{
            for(i = 0; i < numCards; i++){
                toPrint +=  this.p_hand.showCard(i) + " ";
            }
        }

        if(toPrint.length() > 80){
            for(i = 0; i < toPrint.length() / 80; i++){
                System.out.println(toPrint.substring(i * 80, i * 80 + 80));
            }
            System.out.println(toPrint.substring(i * 80));
        }
        else {
            System.out.println(toPrint);
        }
    }

}
