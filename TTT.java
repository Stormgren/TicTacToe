import java.util.Scanner;

public class TTT {
    public static void main(String[] args) {
        // write your code here
        Scanner scanner = new Scanner(System.in);
        String cells = "_________";
        char[] lineOne = new char[]{cells.charAt(0), cells.charAt(1), cells.charAt(2)};
        char[] lineTwo = new char[]{cells.charAt(3), cells.charAt(4), cells.charAt(5)};
        char[] lineThree = new char[]{cells.charAt(6), cells.charAt(7), cells.charAt(8)};
        char[][] board = {lineThree, lineTwo, lineOne};
        printBoard(board);
        int rounds = 1;
        char player = 'X';
        while(checkState(board).equals("Game not finished")) {
            System.out.print("Enter the coordinates: ");
            int column = scanner.nextInt();
            int row = scanner.nextInt();
            while (InvalidCoordinate(column, row) || cellIsOccupied(column, row, board)) {
                while (InvalidCoordinate(column, row)) {
                    System.out.println("Coordinates should be from 1 to 3!");
                    System.out.print("Enter the coordinates: ");
                    column = scanner.nextInt();
                    row = scanner.nextInt();
                }
                while (cellIsOccupied(column, row, board)) {
                    System.out.println("This cell is occupied! Choose another one!");
                    column = scanner.nextInt();
                    row = scanner.nextInt();
                    if (InvalidCoordinate(column, row)) {
                        break;
                    }
                }
            }
            board[row - 1][column - 1] = player;
            printBoard(board);
            rounds++;
            if (rounds % 2 == 0) {
                player = 'O';
            } else {
                player = 'X';
            }
        }
        System.out.println(checkState(board));
    }

    private static boolean InvalidCoordinate(int column, int row) {
        return column > 3 || column < 1 || row > 3 || row < 1;
    }

    private static boolean cellIsOccupied(int column, int row, char[][] board) {
        return board[row - 1][column - 1] == 'X' || board[row - 1][column - 1] == 'O';
    }

    private static void printBoard(char[][] board) {
        System.out.println("---------");
        System.out.println("| " + board[2][0] + " " + board[2][1] + " " + board[2][2] + " |");
        System.out.println("| " + board[1][0] + " " + board[1][1] + " " + board[1][2] + " |");
        System.out.println("| " + board[0][0] + " " + board[0][1] + " " + board[0][2] + " |");
        System.out.println("---------");
    }

    private static String checkState(char[][] board) {
        if (checkImpossible(board)) {
            return "Impossible";
        }
        if (checkWinner('X', board)) {
            return "X wins";
        }
        if (checkWinner('O', board)) {
            return "O wins";
        }
        if (checkGameUnfinished(board)) {
            return "Game not finished";
        }
        return "Draw";
    }

    private static boolean checkGameUnfinished(char[][] board) {
        for (char[] line : board) {
            for (char cell : line) {
                if (cell != 'X' && cell != 'O') {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean checkImpossible(char[][] board) {
        int numberOfX = numberOfPoints('X', board);
        int numberOfO = numberOfPoints('O', board);
        if (Math.abs(numberOfO - numberOfX) > 1) {
            return true;
        }
        return checkWinner('X', board) && checkWinner('O', board);
    }

    private static boolean checkWinner(char player, char[][] board) {
        return checkRows(player, board) || checkColumns(player, board) || checkDiagonals(player, board);
    }

    private static boolean checkDiagonals(char player, char[][] board) {
        if (board[1][1] != player) {
            return false;
        }
        if (board[0][0] == player && board[2][2] == player) {
            return true;
        }
        return board[0][2] == player && board[2][0] == player;
    }

    private static boolean checkColumns(char player, char[][] board) {
        for (int i = 0; i < 3; i++) {
            int points = 0;
            for (int j = 0; j < 3; j++) {
                if (board[j][i] != player) {
                    break;
                }
                points++;
            }
            if (points == 3) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkRows(char player, char[][] board) {
        for (int i = 0; i < 3; i++) {
            int points = 0;
            for (int j = 0; j < 3; j++) {
                if (board[i][j] != player) {
                    break;
                }
                points++;
            }
            if (points == 3) {
                return true;
            }
        }
        return false;
    }

    private static int numberOfPoints(char player, char[][] board) {
        int points = 0;
        for (char[] line : board ) {
            for (char cell : line) {
                if (cell == player) {
                    points++;
                }
            }
        }
        return points;
    }


}