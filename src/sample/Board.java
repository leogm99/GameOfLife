package sample;

import java.util.HashMap;
import java.util.Observable;

public class Board extends Observable {

    private HashMap<Coordinate, Tile> playBoard;
    private HashMap<Coordinate, Boolean> states;
    private int boardSize;

    Board(int boardSize){
        playBoard = new HashMap<>();
        states = new HashMap<>();
        this.boardSize = boardSize;
        for(int i = 1; i <= boardSize; i++) {
            for (int j = 1; j <= boardSize; j++) {
                Coordinate ij = new Coordinate(i, j);
                Tile tij = new Tile(this);
                states.put(ij, tij.getState());
                playBoard.put(ij, tij);
            }
        }
    }

    public int getSize() {
        return this.boardSize;
    }

    public Tile getTile(Coordinate coordinate) {
        return playBoard.get(coordinate);
    }


    public void play() throws InterruptedException {
        for(Coordinate stateCoord : states.keySet()){
            Tile tileState = playBoard.get(stateCoord);
            Boolean state = states.get(stateCoord);
            tileState.setState(state);
        }
        for(Coordinate coord : playBoard.keySet()){
            int tilesAlive = 0;
            Tile actual = playBoard.get(coord);
            for(Direction dir : Direction.values()){
                Coordinate displacement = coord.sum(dir.getDirection());
                displacement.validate(boardSize);
                Tile actualAdj = playBoard.get(displacement);
                if(!actualAdj.isDead()){
                    tilesAlive++;
                }
            }
            if(!actual.isDead()){
                if(tilesAlive < 2 || tilesAlive > 3){
                    states.put(coord, false);
                }
            }
            else{
                if(tilesAlive == 3){
                    states.put(coord, true);
                }
            }
        }
        setChanged();
        notifyObservers();
    }

    public void initialize() {
        for(Coordinate stateCoord : playBoard.keySet()){
            Tile tileState = playBoard.get(stateCoord);
            Boolean state = tileState.getState();
            states.put(stateCoord, state);
        }
        setChanged();
        notifyObservers();
    }
}
