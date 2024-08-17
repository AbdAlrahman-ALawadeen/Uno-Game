package org.example.game;

abstract class Game {
    abstract void init();
    abstract void play();
    abstract boolean verifyNumPlayers();
    abstract void dealCards(int numCardDeal);
    abstract void finish();
}
