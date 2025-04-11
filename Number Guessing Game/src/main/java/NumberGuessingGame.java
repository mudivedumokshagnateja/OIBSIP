import java.util.Scanner;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PlayGame game = new PlayGame();
        
        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("I'm thinking of a number between 1 and 100.");
        
        boolean playAgain = true;
        while (playAgain) {
            game.startNewGame();
            
            while (!game.isGameWon()) {
                System.out.print("Enter your guess: ");
                int guess = scanner.nextInt();
                
                String result = game.checkGuess(guess);
                System.out.println(result);
                
                if (game.isGameWon()) {
                    System.out.println("Congratulations! You've guessed the number in " + game.getAttempts() + " attempts!");
                }
            }
            
            System.out.print("Would you like to play again? (yes/no): ");
            String response = scanner.next().toLowerCase();
            playAgain = response.equals("yes") || response.equals("y");
        }
        
        System.out.println("Thanks for playing! Goodbye!");
        scanner.close();
    }
} 