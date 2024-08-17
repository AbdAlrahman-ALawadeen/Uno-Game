package org.example.table;

import org.example.components.EffectsController;

public class NormalCard extends UnoCard{

    public enum Color{
        BLUE {
            @Override
            public String toString(){
                return " BLUE";
            }
        },
        YELLOW {
            @Override
            public String toString(){
                return " YELLOW";
            }
        },
        RED {
            @Override
            public String toString(){
                return " RED";
            }
        },
        GREEN {
            @Override
            public String toString(){
                return " GREEN";
            }
        }
    }

    public enum Value{
        ZERO {
            @Override
            public String toString(){
                return "ZERO";
            }
        },
        ONE {
            @Override
            public String toString(){return "ONE";
            }
        },
        TWO {
            @Override
            public String toString(){
                return "TWO";
            }
        },
        THREE {
            @Override
            public String toString(){
                return "THREE";
            }
        },
        FOUR {
            @Override
            public String toString(){
                return "FOUR";
            }
        },
        FIVE {
            @Override
            public String toString(){
                return "FIVE";
            }
        },
        SIX {
            @Override
            public String toString(){
                return "SIX";
            }
        },
        SEVEN {
            @Override
            public String toString(){
                return "SEVEN";
            }
        },
        EIGHT {
            @Override
            public String toString(){
                return "EIGHT";
            }
        },
        NINE {
            @Override
            public String toString(){
                return "NINE";
            }
        }
    }

    private final Color color;
    private final Value value;

    public NormalCard(Value value, Color color){
        this.color = color;
        this.value = value;
    }

    public static boolean validColor(String colorToComp){
        for(Color color : Color.values()){
            if(color.name().equals(colorToComp.toUpperCase()))
                return true;
        }
        return false;
    }

    @Override
    public String getValue() {
        return this.value.toString();
    }

    @Override
    public String getColor() {
        return this.color.name();
    }

    @Override
    public void applyEffect(EffectsController effect) {
        return;
    }

    @Override
    public boolean match(UnoCard card) {
        return (card.getValue().equals(this.getValue()) || card.getColor().equals(this.getColor()));
    }

}
