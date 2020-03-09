package sample;

import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;

import java.util.Observable;
import java.util.Observer;

public class BoardView extends Group implements Observer {

    private GridPane boardGui;
    private int size;
    private TileView[][] tileView;


    public BoardView(Board board, int size) {
        board.addObserver(this);
        this.size = size;
        boardGui = new GridPane();
        tileView = new TileView[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Coordinate coordinate = new Coordinate(i + 1, j + 1);
                Tile actual = board.getTile(coordinate);
                TileView t = new TileView(actual);
                actual.addObserver(t);
                tileView[i][j] = t;
                boardGui.add(t, i, j);
            }
        };
        boardGui.setOnMouseClicked(MouseEvent -> {
            if (MouseEvent.getTarget().getClass() == TileView.class && MouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                TileView tile = (TileView) MouseEvent.getTarget();
                tile.revive();
                tile.update(tile.getTile(), null);
                board.initialize();
            }
            else if (MouseEvent.getTarget().getClass() == TileView.class && MouseEvent.getButton().equals(MouseButton.SECONDARY)) {
                TileView tile = (TileView) MouseEvent.getTarget();
                tile.kill();
                tile.update(tile.getTile(), null);
                board.initialize();
            }
            MouseEvent.consume();
        });
        boardGui.setGridLinesVisible(true);
        addView(boardGui);
    }

    private void addView(GridPane boardGui) {
        this.getChildren().add(boardGui);
    }

    private void refreshView(){
        for(TileView[] tileRow : tileView){
            for(TileView tile : tileRow){
                tile.update(null, null);
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        refreshView();
    }
}
