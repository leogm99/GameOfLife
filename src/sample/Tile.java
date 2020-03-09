package sample;

import java.util.Observable;
import java.util.Random;

public class Tile extends Observable {
    private boolean state;
    Board board;

    public Tile(Board board){
        this.board = board;
        Random rd = new Random();
        state = false;
    }

    public boolean isDead(){
        return !state;
    }


    public void kill() {
        state = false;
        notifyObservers();
    }

    public void revive(){
        state = true;
        notifyObservers();
    }

    public boolean getState(){
        return state;
    }

    public void setState(Boolean state){
        this.state = state;
    }
}
