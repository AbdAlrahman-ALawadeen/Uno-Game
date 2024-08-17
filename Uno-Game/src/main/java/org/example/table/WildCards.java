package org.example.table;

import org.example.components.EffectsController;
import java.util.ArrayList;

public class WildCards extends UnoCard{

    private static final ArrayList<String> VALUES;
    static {
        VALUES = new ArrayList<>();
        VALUES.add("WILD");
        VALUES.add("WILD DRAW FOUR");
    }

    private final String color = " BLACK";
    private final String value;
    private boolean used;
    private String effectiveColor;

    public WildCards(String value){
        this.value = value;
    }

    public ArrayList<String> getValues() {
        return VALUES;
    }

    public void addValue(String value){
        VALUES.add(value);
    }

    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public String getColor() {
        if(!this.used) {
            return this.color;
        }
        else{
            return this.effectiveColor;
        }
    }

    @Override
    public void applyEffect(EffectsController effect) {
        if(this.value.equals("WILD")){
            this.setColor(effect.applyWild());
        }
        else{
            effect.applyDrawFour();
            this.setColor(effect.applyWild());
        }
    }

    public void setColor(String color){
        if(this.used){
            return;
        }
        if(!NormalCard.validColor(color)){
            throw new IllegalArgumentException();
        }
        this.effectiveColor = color;
        this.used = true;
    }

    @Override
    public boolean match(UnoCard card) {
        return (this.color.equals(card.getColor()) || this.value.equals(card.getValue()));
    }

    @Override
    public String toString(){
        if(this.used){
            return super.toString();
        }
        return this.getValue();
    }

}
