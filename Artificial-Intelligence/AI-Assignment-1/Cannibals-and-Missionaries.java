import java.util.Scanner;

class Missionary_Cannibal {
    int[] left = new int[3]; 
    int[] right = new int[3];

    Missionary_Cannibal() {
        left[0] = 3; 
        left[1] = 3; 
        left[2] = 1; 
        right[0] = 0;
        right[1] = 0;
        right[2] = 0; 
    }

    void displayState() {
        System.out.println("Left side: " + left[0] + " Missionaries, " + left[1] + " Cannibals, Boat: " + (left[2] == 1 ? "Yes" : "No"));
        System.out.println("Right side: " + right[0] + " Missionaries, " + right[1] + " Cannibals, Boat: " + (right[2] == 1 ? "Yes" : "No"));
    }

    boolean move(int missionaries, int cannibals, boolean toRight) {
        if (missionaries + cannibals > 2 || missionaries < 0 || cannibals < 0 || (missionaries == 0 && cannibals == 0)) {
            return false;
        }

        if (toRight) {
            if (left[0] >= missionaries && left[1] >= cannibals && left[2] == 1) {
                left[0] -= missionaries;
                left[1] -= cannibals;
                right[0] += missionaries;
                right[1] += cannibals;
                left[2] = 0;
                right[2] = 1;
                return true;
            }
        } else {
            if (right[0] >= missionaries && right[1] >= cannibals && right[2] == 1) {
                right[0] -= missionaries;
                right[1] -= cannibals;
                left[0] += missionaries;
                left[1] += cannibals;
                right[2] = 0;
                left[2] = 1;
                return true;
            }
        }
        return false;
    }

    // Method to check if the game is won
    int win() {
        if (right[0] == 3 && right[1] == 3) {
            return 1;
        } else {
            return 0;
        }
    }

    public static void main(String[] args) {
        Missionary_Cannibal game = new Missionary_Cannibal();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            game.displayState();
            if (game.win() == 1) {
                System.out.println("Congratulations! All missionaries and cannibals have successfully crossed the river.");
                break;
            }

            System.out.println("Enter number of missionaries to move:");
            int missionaries = scanner.nextInt();
            System.out.println("Enter number of cannibals to move:");
            int cannibals = scanner.nextInt();

            // Determine the direction based on the boat's current position
            boolean toRight = game.left[2] == 1;

            if (!game.move(missionaries, cannibals, toRight)) {
                System.out.println("Invalid move. Try again.");
            }
        }
        scanner.close();
    }
}
