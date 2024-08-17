package org.example.table;
import org.example.components.EffectsController;

import java.util.ArrayList;

public class ActionsCard extends UnoCard{

    private static final ArrayList<String> VALUES;
    static {
        VALUES = new ArrayList<>();
        VALUES.add("SKIP");
        VALUES.add("REVERSE");
        VALUES.add("+2");
    }

    private final String value;
    private final String color;

    public ActionsCard(String value, String color) {
        this.value = value;
        this.color = color;
    }

    public ArrayList<String> getValues(){
        return VALUES;
    }

    public void addValue(String value){
        VALUES.add(value);
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public void applyEffect(EffectsController effect) {
        return;
    }

    @Override
    public boolean match(UnoCard card) {
        return (this.color.equals(card.getColor()) || this.value.equals(card.getValue()));
    }
}
