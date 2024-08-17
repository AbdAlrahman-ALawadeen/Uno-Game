package org.example.table;

import org.example.components.EffectsController;

public abstract class UnoCard {
    public abstract String getValue();
    public abstract String getColor();
    public abstract void applyEffect(EffectsController effect);
    public abstract boolean match(UnoCard card);

    @Override
    public String toString(){
        return this.getColor() + this.getValue();
    }
}
