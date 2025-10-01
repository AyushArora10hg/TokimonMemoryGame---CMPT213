package cmpt213.asn4.memorygame.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class MemoryGame extends Application {

    public static void main(String[] args) {
        launch();

    }

    @Override
    public void start(Stage stage) {

        GameConsole gc = new GameConsole();

        Scene scene = new Scene(gc.getConsole().getGameDisplay(), 1080, 760);

        stage.setScene(scene);

        stage.setTitle("Pokémon Memory Game");
        stage.getIcons().add(new Image("file:images/icon.png"));

        stage.show();

        gc.gameBoard.printBoard();
    }
}
