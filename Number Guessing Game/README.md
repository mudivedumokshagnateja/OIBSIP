# Number Guessing Game

A simple and interactive Number Guessing Game implemented in Java. The game generates a random number between 1 and 100, and the player needs to guess it.

## Project Structure
```
NumberGuessingGame/
├── src/
│   └── main/
│       └── java/
│           ├── NumberGuessingGame.java
│           └── PlayGame.java
├── README.md
└── .gitignore
```

## Features
- Random number generation between 1 and 100
- Input validation
- Attempt counting
- Helpful feedback messages
- Option to play multiple times
- Clean and organized code structure

## Requirements
- Java Development Kit (JDK) 8 or higher

## How to Play
1. Compile the Java files:
   ```bash
   javac src/main/java/*.java
   ```
2. Run the game:
   ```bash
   java -cp src/main/java NumberGuessingGame
   ```
3. Follow the on-screen instructions:
   - Enter a number between 1 and 100
   - Get feedback on your guess (too high, too low, or correct)
   - Keep guessing until you find the correct number
   - Choose to play again or exit

## Game Rules
- The game generates a random number between 1 and 100
- You need to guess this number
- After each guess, you'll get feedback:
  - "Too high" if your guess is higher than the target number
  - "Too low" if your guess is lower than the target number
  - "Correct" if you've guessed the right number
- The game keeps track of your attempts
- You can play multiple times

## Contributing
Feel free to fork this project and submit pull requests for any improvements.

## License
This project is open source and available under the MIT License. 