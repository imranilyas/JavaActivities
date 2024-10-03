package TicTacToe;

import java.util.*;

public class TicTacToe {

    // Board for the players to see the game and the map for tracking which positions still need to be filled
    public String[][] board;
    public HashMap<Integer, int[]> map;

    // Initialize board
    public TicTacToe() {
        this.board = new String[3][3];
        for(String row[] : board) {
            Arrays.fill(row, "-");
        }
    }
    public static void main(String[] args) throws InterruptedException {
        TicTacToe obj = new TicTacToe();
        obj.map = obj.createMap(obj.board);
        boolean gameOver = false;

        // Setting up the player turn for two players or vs computer
        boolean playerOne = false;
        boolean cpu = false;
        String type = "O";

        try(Scanner scanner = new Scanner(System.in)) {
            //!! Still need to implement playing against the computer
            System.out.println("Select the Game Mode");
            System.out.println("1. Player 1 vs Player 2");
            System.out.println("2. Player 1 vs CPU");

            boolean b = false;
            while(!b) {
                if(!scanner.hasNextInt()) {
                    scanner.next();
                    System.out.println("Invalid input. You must choose a number.");
                } else {
                    int mode = scanner.nextInt();
                    if(mode == 2) {
                        cpu = true;
                        b = true;
                        System.out.println("You chose to play against the CPU...Good luck");
                    }
                    else if(mode == 1) {
                        System.out.println("You chose PvP");
                        b = true;
                    }
                    else {
                        System.out.println("Please select a valid choice...1 or 2");
                    }
                }
            }

            // Repeat while 3 are not in a row, col, or diagonal
            while(!gameOver && !obj.map.isEmpty()) {

                // Switch player turn
                playerOne = !playerOne;

                // Print board and choices
                obj.printBoard(obj.board);
                obj.printMap(obj.map);

                // Change type depending on player's turn
                if (!playerOne) {
                    type = "O";
                    if(cpu) {
                        // CPU turn
                        gameOver = obj.cpuTurn(type, obj.board, obj.map);
                        continue;
                    }
                } else {
                    type = "X";
                }

                // Prompt player and handle input by checking if it's valid, if not, repeat steps
                // Remove from map and update board
                boolean valid = false;
                while (!valid) {
                    System.out.println("What position would you like to fill?");

                    // Check for illegal input (not an integer)
                    int choice;
                    if(!scanner.hasNextInt()) {
                        scanner.next();
                        System.out.println("Invalid input. You must choose a number.");
                        continue;
                    } else {
                        choice = scanner.nextInt();
                    }

                    // Check if the integer is among one of the available spots
                    if (obj.map.containsKey(choice)) {
                        valid = true;
                        int row = obj.map.get(choice)[0];
                        int col = obj.map.get(choice)[1];

                        obj.board[row][col] = type;
                        obj.map.remove(choice);

                        gameOver = obj.checkBoard(type, obj.board, row, col);
                    } else {
                        System.out.println("Invalid choice. Try again.");
                    }
                }
            }
        }

        obj.printBoard(obj.board);
        if(obj.map.isEmpty() && !gameOver) {
            System.out.println("Cat's Scratch");
        } else {
            System.out.println("Congratulations!!! " + type + " wins!!");
        }
    }

    public boolean cpuTurn(String type, String[][] board, HashMap<Integer, int[]> map) {
        // To give the illusion of the computer making a decision
        System.out.println("CPU: It is my turn now...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        // Convert to array to randomize a choice
        int arr[] = new int[map.size()];
        int count = 0;
        for(Integer i : map.keySet()) {
            arr[count] = i;
            count++;
        }

        // Randomize choice and update map and board
        int computerChoice = (int) Math.round(Math.random()*(arr.length-1));
        int row = map.get(arr[computerChoice])[0];
        int col = map.get(arr[computerChoice])[1];
        map.remove(arr[computerChoice]);
        board[row][col] = type;

        // Check to see if computer won
        return checkBoard(type, board, row, col);
    }

    // Print board
    public void printBoard(String[][] board) {
        for(String row[] : board) {
            System.out.println(row[0] + " | " + row[1] + " | " + row[2]);
        }
    }

    // Create map for the user inputs
    public HashMap<Integer, int[]> createMap(String[][] board) {
        HashMap<Integer, int[]> map = new HashMap<>();
        int count = 1;
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board.length; j++) {
                if (board[i][j].equals("-")) {
                    int[] arr = new int[]{i, j};
                    map.put(count, arr);
                    count++;
                }
            }
        }
        return map;
    }

    public void printMap(HashMap<Integer, int[]> map) {
        for(Map.Entry<Integer, int[]> entry: map.entrySet()) {
            System.out.println(entry.getKey() + ".  Row: " + entry.getValue()[0] + ", Col:" + entry.getValue()[1]);
        }
    }

    // Check if a user has won
    public boolean checkBoard(String type, String[][] gameBoard, int row, int col) {
        // Solve for row
        for(int i = 0; i < gameBoard.length; i++) {
            if(!gameBoard[row][i].equals(type)) {
                break;
            } else if(i == 2) {
                return true;
            }
        }

        // Solve for col
        for(int i = 0; i < gameBoard.length; i++) {
            if(!gameBoard[i][col].equals(type)) {
                break;
            } else if(i == 2) {
                return true;
            }
        }

        // Solve for diagonal
        // Diagonal down to the bottom right
        if((row == 0 && col == 0) || ((row == 2 && col == 2))) {
            for(int i = 0; i < gameBoard.length; i++) {
                if(!gameBoard[i][i].equals(type)) {
                    break;
                }
                else if(i == 2) {
                    return true;
                }
            }
        }
        // Diagonal up to the top right
        if((row == 2 && col == 0) || ((row == 0 && col == 2))) {
            for(int i = 0; i < gameBoard.length; i++) {
                if(!gameBoard[gameBoard.length - 1 - i][i].equals(type)) {
                    break;
                }
                else if(i == 2) {
                    return true;
                }
            }
        }
        return false;
    }
}