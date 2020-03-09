package sample;
import javafx.event.EventHandler;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;

import java.util.Observable;
import java.util.Observer;

public class TileView extends Pane implements Observer {
    private Tile tile;

    public TileView(Tile tile) {
        this.tile = tile;
        this.update(this.tile, this);
        this.setPrefSize(15, 15);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (tile.isDead()) {
            setStyle("-fx-background-color: black");
        } else {
            setStyle("-fx-background-color: white");
        }
    }

    public void revive(){
        tile.revive();
    }

    public Tile getTile(){
        return tile;
    }

    public void kill() {
        tile.kill();
    }
}
