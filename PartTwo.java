package tictactoe;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Random;
public class PartTwo {
    public static void main(String[] args) {
       char[][] gameBoard = new char[3][3]; // create a 3 by 3 char array to store the board
       clearBoard(gameBoard);
       printBoard(gameBoard);
       play(gameBoard);
           
        
    }
    
    static void clearBoard(char gameBoard[][]) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                gameBoard[i][j] = ' ';
            }
        }
    }
   
    static void printBoard(char gameBoard[][]) { 
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (j == 0) {
                    System.out.print("|");
            
                } 
                System.out.print(gameBoard[i][j] + " ");
                if (j == 2) {
                    System.out.println(" |");
                }
            }
        }
        System.out.println("---------");
    }
    
    
    static boolean winner(char gameBoard[][]) {
        int rowIndex = 0;
        int colIndex = 0;
		boolean notBlank = false;
        for (int i = 0; i < 3; i++) { // find the row winner
			notBlank = gameBoard[0][i] != ' ';
			boolean threeInColumn = gameBoard[0][i] == gameBoard[1][i] && gameBoard[1][i] == gameBoard[2][i];
            boolean threeInRow = gameBoard[i][0] == gameBoard[i][1]  && gameBoard[i][1] == gameBoard[i][2];
            if (notBlank && threeInColumn) {
                colIndex = i;
                System.out.println(gameBoard[0][colIndex] + " wins");
                return true;
            } 
            notBlank = gameBoard[i][0] != ' ';
            if (notBlank && threeInRow) {
                rowIndex = i;
                System.out.println(gameBoard[rowIndex][0] + " wins");
                return true;
            }
            
        }
		notBlank = gameBoard[1][1] != ' ';
        boolean threeDiag = gameBoard[0][0] == gameBoard[1][1] && gameBoard[1][1] == gameBoard[2][2] ||
        gameBoard[2][0] == gameBoard[1][1] && gameBoard[1][1] == gameBoard[0][2];
        if (notBlank) {
            if (threeDiag) {
                System.out.println(gameBoard[1][1] + " wins");
                return true;
            } 
        }
        return false;      
    }
    
    static void play(char gameBoard[][]) {
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();
        int moves = 1;   
        while (true) {
            if (moves == 10) {
                System.out.println("Draw");
                break;
            } else if (moves % 2 == 0){
                int col = rand.nextInt(3);
                int row = rand.nextInt(3);
                if (gameBoard[col][row] == '_' || gameBoard[col][row] == ' ') {
                    System.out.println("Making move level \"easy\"");
                    gameBoard[col][row] = 'O';
                    moves++;
                    printBoard(gameBoard);
                    if(winner(gameBoard)) {
                        break;
                    }
                }
            }
            else if (moves % 2 == 1) {
                System.out.print("Enter the coordinates:");
                try {
                    int x = scanner.nextInt();
                    int y = scanner.nextInt();
                            
                    if (x > 0 && x < 4 && y > 0 && y < 4) {
                        int row = x - 1;
                        int col = Math.abs(y - 3);
                        if (gameBoard[col][row] == '_' || gameBoard[col][row] == ' ') { 
                            gameBoard[col][row] = 'X';
                            moves++;
                            printBoard(gameBoard);
                            if (winner(gameBoard)) {
                                break;
                            }
                        }
                    } else {
                        System.out.println("Enter numbers between 1 and 3");
                    }
                }
                        
                    catch (InputMismatchException e) {
                        System.out.println("You should enter numbers!");
                        scanner.next();
                    }
                } 
            }
        }

}            
    
