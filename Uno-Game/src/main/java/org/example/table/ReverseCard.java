package org.example.table;

import org.example.components.EffectsController;

public class ReverseCard extends UnoCard{

    @Override
    public boolean match(UnoCard card) {
        return false;
    }

    @Override
    public String getValue() {
        return "";
    }

    @Override
    public String getColor() {
        return "";
    }

    @Override
    public void applyEffect(EffectsController effect) {
        effect.applyReverse();
    }
}