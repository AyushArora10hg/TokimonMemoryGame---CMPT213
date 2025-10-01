package cmpt213.asn4.memorygame.ui;

import cmpt213.asn4.memorygame.game.GameBoard;
import cmpt213.asn4.memorygame.game.GameLogic;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import java.util.ArrayList;

/**
 * The {@link GameConsole} handles user interaction with the program. It bridges the game's UI and
 * its logic. <br><br>
 * <b>Fields:</b><br>
 * {@code gameLogic}: An instance of GameLogic class to control gameplay.<br>
 * {@code gameDisplay}: An instance of GameDisplay class to display the UI.<br>
 * {@code gameBoard}: An instance of the GameBoard class to control positions of the hidden
 * 'Pokémon' images <br>
 * {@code firstCard}: The first ImageView clicked by the player during a turn. <br>
 * {@code secondCard}: The second ImageView clicked by the player during a turn. <br>
 * {@code firstCardURL}: A String holding the image path of firstCard's image. <br>
 * {@code secondCardURL}: A String holding the image path of secondCard's image. <br>
 * {@code revealedCards}: An ArrayList of ImageViews which have been matched/paired/revealed.<br><br>
 * <b>Methods:</b><br>
 * {@code GameConsole()} instantiates the necessary fields and sets them to default values. It adds EventHandler
 * to the {@link GameDisplay} <b>gameDisplay's cards ArrayList newGameButton</b> <br>
 * {@code addImageHandler()} : The method accepts a {@link GameBoard} argument and sets the images of each ImageView
 * at a given position to the image associated with the integer stored in the GameBoard argument's 2-D array
 * at that position. It controls the values of <b>firstCard, secondCard, firstCardURL and secondCardURL</b>
 * to control turns and cards revealed by the user. After every turn, the images of each ImageView except those contained
 * by the revealedCards is set to default image to keep the unmatched cards hidden and matched cards face up. <br>
 * {@code addButtonControl()}: The method adds an ActionEvent handler to the {@link GameDisplay} <b>gameDisplay's
 * newGameButton </b>. This button can be used by the player to start a new round of the game. <br>
 * {@code resetGame()} resets all the fields to default values to start a new round of game.<br>
 * {@code hideCards()} flips back the card not contained by the revealedCard array.<br>
 */

public class GameConsole {

    private final GameLogic gameLogic;
    private final GameDisplay gameDisplay;
    public final GameBoard gameBoard;
    private final ArrayList<ImageView> revealedCards;
    private ImageView firstCard;
    private ImageView secondCard;
    private String firstCardURL;
    private String secondCardURL;

    // Constructor
    public GameConsole() {

        gameLogic = new GameLogic();
        gameDisplay = new GameDisplay();
        gameBoard = new GameBoard();
        firstCard = new ImageView();
        secondCard = new ImageView();
        firstCardURL = "";
        secondCardURL = "";
        revealedCards = new ArrayList<>();

        this.addImageHandlers(gameDisplay.getCards(), gameBoard);
        this.addButtonControl(GameDisplay.getNewGameButton());
    }

    // Getters
    public GameDisplay getConsole() {

        return this.gameDisplay;
    }

    // Adding Controls
    private void addImageHandlers(ArrayList<ImageView> imageViews, GameBoard gameBoard) {


        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int row = i;
                int col = j;
                imageViews.get(i * 4 + j).addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {

                    ImageView clickedCard = (ImageView) mouseEvent.getSource();
                    clickedCard.setImage(new Image("file:images/" + gameBoard.getValue(row, col) + ".png"));

                    if (revealedCards.contains(clickedCard)) {
                        return;
                    }

                    if (firstCardURL.isEmpty()) {

                        firstCardURL = clickedCard.getImage().getUrl();
                        firstCard = clickedCard;
                    }
                    else if (!clickedCard.equals(firstCard)) {

                        secondCardURL = clickedCard.getImage().getUrl();
                        secondCard = clickedCard;

                        gameLogic.play(firstCardURL, secondCardURL);

                        gameDisplay.getMovesLabel().setText("Moves: " + gameLogic.getMovesCounter());

                        if (gameLogic.isRevealed(firstCardURL)) {

                            revealedCards.add(firstCard);
                            revealedCards.add(secondCard);

                        }


                        if (this.hasEnded()) {

                            gameLogic.addScore(gameLogic.getMovesCounter());
                            gameDisplay.getMovesLabel().setText("You won the game in " + gameLogic.getMovesCounter() +
                                    " moves. (Highest: " + gameLogic.getHighScore() + ")");


                        }

                        PauseTransition pause = new PauseTransition(Duration.seconds(0.2));
                        pause.setOnFinished(event -> {
                            hideCards();
                        });
                        pause.play();

                        firstCardURL = "";
                        secondCardURL = "";
                    }

                });

            }
        }
    }

    private void addButtonControl(Button button) {

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                resetGame();
            }
        });

        button.addEventHandler(MouseEvent.MOUSE_ENTERED, mouseEvent -> {
            button.setTextFill(Color.ROYALBLUE);

        });

        button.addEventHandler(MouseEvent.MOUSE_EXITED, mouseEvent -> {
            button.setTextFill(Color.MAROON);

        });

    }

    // Gameplay
    private void resetGame() {

        this.gameBoard.shuffle();
        this.gameLogic.reset();
        this.revealedCards.clear();

        hideCards();

        gameDisplay.getMovesLabel().setText("Moves: " + gameLogic.getMovesCounter());
        firstCardURL = "";
        secondCardURL = "";

        firstCard = new ImageView();
        secondCard = new ImageView();

    }

    private void hideCards() {

        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                if (!(revealedCards.contains(gameDisplay.getCards().get((row * 4) + col)))) {
                    gameDisplay.getCards().get((row * 4) + col).setImage(new Image("file:images/default.png"));
                }
            }
        }
    }

    public boolean hasEnded() {

        return revealedCards.size() == 16;

    }

}
