package tictactoe;
import java.util.InputMismatchException;
import java.util.Scanner;
public class PartOne {
    public static void main(String[] args) {
       char[][] gameBoard = new char[3][3]; // create a 3 by 3 char array to store the board
        inputBoard(gameBoard);
        printBoard(gameBoard);
        play(gameBoard);
        printBoard(gameBoard);
        winner(gameBoard);
        
    }
    static void inputBoard(char gameBoard[][]) { // this function inputs itesm into the gameboard
        System.out.print("Enter cells: ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next(); // allow the user to input a populated board as a string
        int count = 0; // create a variable to index the string
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                gameBoard[i][j] = input.charAt(count);// input elements of the string in the gameBoard array
                count++;
            }
        }
    }
    static void printBoard(char gameBoard[][]) { 
        System.out.println("----------");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (j == 0) {
                    System.out.print("| ");
            
                } 
                System.out.print(gameBoard[i][j] + " ");
                if (j == 2) {
                    System.out.println(" |");
                }
            }
        }
        System.out.println("----------");
    }
    
     
    static boolean winner(char gameBoard[][]) {
        boolean rowWinner = false;
        boolean colWinner = false;
        boolean diagWinner = false;
        int rowIndex = 0;
        int colIndex = 0;
        int countWinner = 0;
		boolean notBlank = false;
        for (int i = 0; i < 3; i++) {// not equal to white space //then check the values of each row
			notBlank = gameBoard[i][0] != ' ' && gameBoard[i][0] != '_';
			boolean threeInRow = gameBoard[i][0] == gameBoard[i][1]  && gameBoard[i][1] == gameBoard[i][2];
            if (notBlank && threeInRow) {
                rowWinner = true;
                countWinner++;
                rowIndex = i;
            }
        }
        for (int i = 0; i < 3; i++) { // find the row winner
			notBlank = gameBoard[0][i] != ' ' && gameBoard[i][0] != '_';
			boolean threeInColumn = gameBoard[0][i] == gameBoard[1][i] && gameBoard[1][i] == gameBoard[2][i];
            if (notBlank && threeInColumn) {
                colWinner = true;
                countWinner++;
                colIndex = i;
            }
            
        }
		notBlank = gameBoard[1][1] != ' ' && gameBoard[1][1] != '_'; 
        if (notBlank) {
            if (gameBoard[0][0] == gameBoard[1][1] && gameBoard[1][1] == gameBoard[2][2]) {
                diagWinner = true;
                countWinner++;
            } else if (gameBoard[2][0] == gameBoard[1][1] && gameBoard[1][1] == gameBoard[0][2]) {
                diagWinner = true;
                countWinner++;
            }
        }
        int countO = 0;
        int countX = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (gameBoard[i][j] == 'O') {
                    countO++;
                } else if (gameBoard[i][j] == 'X') {
                        countX++;
                }
            }
        }
        if (countWinner > 1) { // if there are multipe winnners print impossible
            System.out.println("Impossible");
        } else if (Math.abs(countO - countX) > 2) {
            System.out.println("Impossible");
        } else if (rowWinner) {
            System.out.println(gameBoard[rowIndex][0] + " wins");
        } else if (colWinner) {
            System.out.println(gameBoard[0][colIndex] + " wins");
        } else if (diagWinner) {
            System.out.println(gameBoard[1][1] + " wins");
            return true;
        } else if (countX + countO == 9) {
            System.out.println("Draw");
            return true;
        } else {
            System.out.println("Game not finished");
            return false;
        }
        return false;
    }
    static void play(char gameBoard[][]) {
        boolean notEntered = true;
        Scanner scanner = new Scanner(System.in);
            while (notEntered) {
                
                System.out.print("Enter the coordinates:");
                boolean equal = countXO(gameBoard);
                try {
                    int x = scanner.nextInt();
                    int y = scanner.nextInt();
                    
                    if (x > 0 && x < 4 && y > 0 && y < 4) {
                        int row = x - 1;
                        int col = Math.abs(y - 3);
                        if (gameBoard[col][row] == '_' || gameBoard[col][row] == ' ') {
                            notEntered = false;
                            if (equal) {
                                gameBoard[col][row] = 'X';
                            } else {
                                gameBoard[col][row] = 'O';
                            }
                        } else {
                            System.out.println("This cell is occupied! Choose another one!");
                            
                        }
                    } else {
                        System.out.println("Coordinates should be from 1 to 3!");
                        continue;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("You should enter numbers!");
                    scanner.next();
                }
        }
    }
    static boolean countXO(char gameBoard[][]) {
        int countO = 0;
        int countX = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (gameBoard[i][j] == 'O') {
                    countO++;
                } else if (gameBoard[i][j] == 'X') {
                    countX++;
                }
                
            }  
        }
     if (countO == countX) {
            return true;
        } else {
            return false;
        }    
    }
}


