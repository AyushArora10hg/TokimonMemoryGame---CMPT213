package cmpt213.asn4.memorygame.game;

import java.util.ArrayList;

/**
 * @author Ayush Arora
 * @version 2.1 <br><br>
 * <p>
 * The {@link GameLogic} class defines the back-end working of the <b>Memory Game</b>. It has 3 fields;
 * an integer ArrayList {@code revealed}, an integer {@code moveCounter} and a static integer ArrayList
 * {@code scores}. <br><br>
 * {@code revealed} stores the numbers which have been paired or revealed during the gameplay; {@code moveCounter}
 * tracks the number of turns taken by user. <b> A turn in this game is defined as flipping of two
 * successive cards</b>. {@code scores} stores the moves taken to complete the game in each round. <br><br>
 * {@code play()} accepts two strings representing the images' URLs stored by two different ImageViews clicked
 * by the player. If the strings are the same, the method adds the integer value associated with those images
 * to the <b>revealed</b> field. <b>moveCounter</b> is incremented each time this method is called. <br>
 * {@code isRevealed} is a boolean type method that returns true if its argument is contained in the
 * <b>revealed</b> ArrayList.<br>
 * {@code addScore()} adds the latest score of the player to the <b>scores</b> ArrayList.<br>
 * {@code reset()} method clears the <b>revealed</b> ArrayList as well as sets the <b>movesCounter</b> to 0.
 * It is called during the start of a new game round.
 */

public class GameLogic {

    private static final ArrayList<Integer> scores = new ArrayList<>();
    private final ArrayList<Integer> revealed = new ArrayList<>();
    private int movesCounter = 0;

    // Getters
    public int getMovesCounter() {
        return movesCounter;
    }

    public int getHighScore() {

        return java.util.Collections.min(scores);
    }

    // Main Game Logic
    public void play(String card1, String card2) {

        ++movesCounter;

        if (card1.equals(card2)) {
            revealed.add((int) card1.charAt(12));
        }
    }

    public boolean isRevealed(String card) {

        return revealed.contains((int) card.charAt(12));
    }

    public void addScore(int newScore) {

        scores.add(newScore);
    }

    // Reset
    public void reset() {

        this.revealed.clear();
        this.movesCounter = 0;
    }
}
