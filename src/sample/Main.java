package sample;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;

public class Main extends Application {
    private Stage stage;
    private Thread game;
    private Board board;

    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        stage.setTitle("Game of Life");
        stage.setMinWidth(1024);
        stage.setMinHeight(720);
        this.board = new Board(40);
        BoardView boardView = new BoardView(board, board.getSize());
        StackPane stack = new StackPane();
        VBox hbox = new VBox();
        Button play = new Button("Play");
        Button stop = new Button("Stop");
        stop.setDisable(true);
        hbox.getChildren().add(play);
        hbox.getChildren().add(stop);
        stack.getChildren().add(hbox);
        stack.getChildren().add(boardView);
        stage.setScene(new Scene(stack));
        stage.show();
        stage = primaryStage;
        board.initialize();

        Task task = new Task() {
            @Override
            protected Object call() throws Exception {
                while(!Thread.currentThread().isInterrupted()) {
                    board.play();
                    Thread.sleep(30);
                }
                return null;
            }

        };
        game = new Thread(task);
        play.setOnAction(MouseEvent -> {
            game.start();
            play.setDisable(true);
            stop.setDisable(false);
            MouseEvent.consume();
        });
        stop.setOnAction(MouseEvent -> {
            game.interrupt();
            stop.setDisable(true);
            play.setDisable(false);
            Task newtask = new Task() {
                @Override
                protected Object call() throws Exception {
                    while(!Thread.currentThread().isInterrupted()) {
                        board.play();
                        Thread.sleep(30);
                    }
                    return null;
                }

            };
            game = new Thread(newtask);
            MouseEvent.consume();
        });
    }
}
