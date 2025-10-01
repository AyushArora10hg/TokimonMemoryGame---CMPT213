package cmpt213.asn4.memorygame.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import java.util.ArrayList;

/**
 * @author Ayush Arora
 * @version 2.1 <br><br>
 * <p>
 * The {@link GameDisplay} class' Main responsibility is to set up various UI elements.<b>The theme of
 * the game is "Pokémon".</b> <br>
 * <b>Fields:</b><br>
 * {@code cards}: An ArrayList of ImageViews, storing 16 ImageViews to display to the user. <br>
 * {@code gameDisplay}: A GridPane to organize the elements.<br>
 * {@code moves}: A Label displaying moves taken by the user. <br>
 * {@code newGameButton}: A Button for the player to start a new round of the game. <br><br>
 * <b>Methods</b> <br>
 * Getters are defined for each field. <br>
 * {@code GameDisplay()} : The constructor instantiates the class' fields and calls the element setters. <br>
 * {@code setUpCards} populates cards with 16 ImageViews. Each ImageView's image is set to the default
 * image. Various properties like size and effects are adjusted in this method. <br>
 * {@code setUpGameDisplay()} organizes all the elements into HBoxes and VBoxes and organizes those onto the
 * GridPane <b>gameDisplay</b>. Properties like background, alignment, padding etc. are adjusted here.<br>
 * The helper methods provide designing to the individual elements of the UI like the 'Moves' Label and the
 * 'New Game'.
 */

public class GameDisplay {

    private static Button newGameButton;
    private final ArrayList<ImageView> cards;
    private final GridPane gameDisplay;
    private final Label moves;

    // Constructor
    public GameDisplay() {

        cards = new ArrayList<>();
        gameDisplay = new GridPane();
        moves = this.createMovesLabel();
        newGameButton = createNewGameButton();

        this.setUpCards();
        this.setUpGameDisplay();

    }

    public static Button getNewGameButton() {

        return newGameButton;
    }

    private static Button createNewGameButton() {
        Button newGame = new Button("New Game");
        newGame.setAlignment(Pos.CENTER);
        newGame.setFont(Font.font("Forte", FontWeight.NORMAL, 50));
        newGame.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));
        newGame.setTextFill(Color.MAROON);
        newGame.setPadding(new Insets(0));
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(30);
        dropShadow.setColor(Color.IVORY);

        newGame.setEffect(dropShadow);

        return newGame;
    }

    // Getters
    public ArrayList<ImageView> getCards() {

        return cards;
    }

    public GridPane getGameDisplay() {

        return gameDisplay;
    }

    public Label getMovesLabel() {

        return moves;
    }

    // Elements' Setup
    public void setUpCards() {

        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(30);
        dropShadow.setColor(Color.BLACK);

        for (int i = 0; i < 16; i++) {
            cards.add(new ImageView(new Image("file:images/default.png")));
            cards.get(i).setFitWidth(100);
            cards.get(i).setFitHeight(100);
            cards.get(i).setEffect(dropShadow);
            cards.get(i).setPreserveRatio(true);
        }

    }

    private void setUpGameDisplay() {

        VBox top = new VBox(1, this.createTitle(), newGameButton);
        top.setAlignment(Pos.CENTER);

        VBox main = new VBox(10);
        main.getChildren().add(this.createHBox(cards.get(0), cards.get(1), cards.get(2), cards.get(3)));
        main.getChildren().add(this.createHBox(cards.get(4), cards.get(5), cards.get(6), cards.get(7)));
        main.getChildren().add(this.createHBox(cards.get(8), cards.get(9), cards.get(10), cards.get(11)));
        main.getChildren().add(this.createHBox(cards.get(12), cards.get(13), cards.get(14), cards.get(15)));
        main.setAlignment(Pos.CENTER);

        VBox bottom = new VBox(moves);
        bottom.setAlignment(Pos.CENTER);

        gameDisplay.setVgap(15);
        gameDisplay.add(top, 0, 0);
        gameDisplay.add(main, 0, 1);
        gameDisplay.add(bottom, 0, 2);
        gameDisplay.setBackground(new Background(new BackgroundImage(new Image("file:images/bg.jpg"), null, null, null, null)));
        gameDisplay.setPadding(new Insets(10, 30, 20, 30));
        gameDisplay.setAlignment(Pos.CENTER);
    }

    // Helper
    private HBox createHBox(ImageView iv1, ImageView iv2, ImageView iv3, ImageView iv4) {

        HBox temp = new HBox(10, iv1, iv2, iv3, iv4);
        temp.setAlignment(Pos.CENTER);
        return temp;
    }

    private ImageView createTitle() {

        ImageView title = new ImageView(new Image("file:images/title.png"));
        title.setFitHeight(180);
        title.setFitWidth(400);

        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(30);
        dropShadow.setColor(Color.LIGHTGOLDENRODYELLOW);

        title.setEffect(dropShadow);

        return title;
    }

    private Label createMovesLabel() {

        Label moves = new Label("Moves: " + 0);
        moves.setAlignment(Pos.CENTER);
        moves.setBackground(Background.fill(Color.MAROON));
        moves.setFont(new Font("Forte", 25));
        moves.setTextFill(Color.rgb(255, 200, 37));
        moves.setPadding(new Insets(0, 130, 0, 130));

        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(30);
        dropShadow.setColor(Color.LIGHTGOLDENRODYELLOW);

        moves.setEffect(dropShadow);

        return moves;
    }

}
