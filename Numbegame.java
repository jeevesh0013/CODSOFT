import java.util.Random;
import java.util.Scanner;

class Game {
    private int number;
    private int maxAttempts;
    private int attempts;
    private int score;

    Game(int maxAttempts) {
        Random rand = new Random();
        this.number = rand.nextInt(100) + 1;
        this.maxAttempts = maxAttempts;
        this.attempts = 0;
        this.score = 0;
    }

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    int takeInput() {
        System.out.println("Guess the number between 1 to 100: ");
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }

    boolean isCorrect(int inputNumber) {
        attempts++;
        if (inputNumber == number) {
            System.out.format("Yes, you guessed it right! It was %d. You guessed the number in %d attempts.%n", number, attempts);
            score++;
            return true;
        } else if (inputNumber < number) {
            System.out.println("Too low...");
        } else if (inputNumber > number) {
            System.out.println("Too high...");
        }

        if (attempts >= maxAttempts) {
            System.out.format("You've used all %d attempts. The correct number was %d.%n", maxAttempts, number);
            return true;
        }

        return false;
    }

    public int getScore() {
        return score;
    }

    public void resetGame() {
        Random rand = new Random();
        this.number = rand.nextInt(100) + 1; // +1 to include 1 to 100
        this.attempts = 0;
    }
}

public class Numbegame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the Number Guessing Game!!!");
        System.out.println("Enter the maximum number of attempts: ");
        int maxAttempts = sc.nextInt();
        boolean play;
        int rounds = 0;

        do {
            Game g = new Game(maxAttempts);
            boolean correctGuess = false;
            while (!correctGuess) {
                int n = g.takeInput();
                correctGuess = g.isCorrect(n);
            }
            rounds++;
            System.out.println("Do you want to play the next round? (yes/no): ");
            play = sc.next().equalsIgnoreCase("yes");

            if (play) {
                g.resetGame();
            }
        } while (play);

        System.out.println("Thanks for playing!");
        System.out.println("You played " + rounds + " rounds.");
        System.out.println("Your final score is: " + rounds);
        System.out.println("Please visit again...");
    }
}
