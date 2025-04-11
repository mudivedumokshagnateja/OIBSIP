import java.util.Random;

public class PlayGame {
    private int targetNumber;
    private int attempts;
    private boolean gameWon;
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 100;
    private Random random;

    public PlayGame() {
        random = new Random();
        startNewGame();
    }

    public void startNewGame() {
        targetNumber = random.nextInt(MAX_NUMBER - MIN_NUMBER + 1) + MIN_NUMBER;
        attempts = 0;
        gameWon = false;
    }

    public String checkGuess(int guess) {
        attempts++;
        
        if (guess < MIN_NUMBER || guess > MAX_NUMBER) {
            return "Please guess a number between " + MIN_NUMBER + " and " + MAX_NUMBER + ".";
        }
        
        if (guess == targetNumber) {
            gameWon = true;
            return "Correct! You've guessed the number!";
        } else if (guess < targetNumber) {
            return "Too low! Try again.";
        } else {
            return "Too high! Try again.";
        }
    }

    public boolean isGameWon() {
        return gameWon;
    }

    public int getAttempts() {
        return attempts;
    }
} 