package tictactoe;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static Scanner scanner;

    public static void main(String[] args) {
        char[][] grid = new char[3][3];
        scanner = new Scanner(System.in);
        initGrid(grid);
        usersGrid(grid);
        printGrid(grid);
//        printGameState(grid);
        makePlayerMove(grid);
        printGrid(grid);
    }

    public static void initGrid(char[][] grid) {
        for (char[] rows : grid) {
            Arrays.fill(rows, '_');
        }
    }

    public static void usersGrid(char[][] grid) {
        System.out.print("Enter cells: ");
        String cells = scanner.nextLine();
        if (cells.length() >= 9) {
            int i = 0;
            for (int j = 0; j < grid.length; j++) {
                for (int k = 0; k < grid[j].length; k++) {
                    grid[j][k] = cells.charAt(i++);
                }
            }
        }
    }

    public static void makePlayerMove(char[][] grid) {
        System.out.print("Enter the coordinates: ");
        String y_s = scanner.next();
        String x_s = scanner.next();
        int y;
        int x;
        if (isInt(y_s) && isInt(x_s)) {
            y = Integer.parseInt(y_s);
            x = Integer.parseInt(x_s);
        } else {
            System.out.println("You should enter numbers!");
            makePlayerMove(grid);
            return;
        }
        if (x >= 1 && x <= 3 && y >= 1 && y <= 3) {
            if (grid[y - 1][x - 1] != '_') {
                System.out.println("This cell is occupied! Choose another one!");
                makePlayerMove(grid);
            } else {
                grid[y - 1][x - 1] = 'X';
            }
        } else {
            System.out.println("Coordinates should be from 1 to 3!");
            makePlayerMove(grid);
        }
    }

    public static boolean isInt(String s) {
        if (s.isEmpty()) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            if (i == 0 && s.charAt(0) == '-') {
                if (s.length() == 1) {
                    return false;
                } else {
                    continue;
                }
            }
            if (!Character.isDigit(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static void printGameState(char[][] grid) {
        int oCnt = 0;
        int xCnt = 0;
        int cnt = 0;
        int errorCnt = 0;
        int xWins;
        int oWins;
        for (char[] chars : grid) {
            for (char aChar : chars) {
                if (aChar == 'X') {
                    xCnt++;
                } else if (aChar == 'O') {
                    oCnt++;
                } else if (aChar == '_') {
                    cnt++;
                } else {
                    errorCnt++;
                }
            }
        }
        if (Math.abs(xCnt - oCnt) > 1) {
            errorCnt++;
        }
        xWins = checkWin(grid, 'X');
        oWins = checkWin(grid, 'O');
        if (xWins > 1 || oWins > 1 || (xWins == 1 && oWins == 1)) {
            errorCnt++;
        }
        if (errorCnt > 0) {
            System.out.println("Impossible");
        } else if (xWins == 1) {
            System.out.println("X wins");
        } else if (oWins == 1) {
            System.out.println("O wins");
        } else if (cnt > 0) {
            System.out.println("Game not finished");
        } else {
            System.out.println("Draw");
        }
    }

    public static int checkWin(char[][] grid, char player) {
        int wins = 0;
        if (grid[0][0] == player && grid[0][1] == player && grid[0][2] == player) {
            wins++;
        }
        if (grid[1][0] == player && grid[1][1] == player && grid[1][2] == player) {
            wins++;
        }
        if (grid[2][0] == player && grid[2][1] == player && grid[2][2] == player) {
            wins++;
        }
        if (grid[0][0] == player && grid[1][0] == player && grid[2][0] == player) {
            wins++;
        }
        if (grid[0][1] == player && grid[1][1] == player && grid[2][1] == player) {
            wins++;
        }
        if (grid[0][2] == player && grid[1][2] == player && grid[2][2] == player) {
            wins++;
        }
        if (grid[0][0] == player && grid[1][1] == player && grid[2][2] == player) {
            wins++;
        }
        if (grid[2][0] == player && grid[1][1] == player && grid[0][2] == player) {
            wins++;
        }
        return wins;
    }

    public static void printGrid(char[][] grid) {
        System.out.println("---------");
        for (char[] rows : grid) {
            System.out.print("| ");
            for (char cell : rows) {
                System.out.print(cell + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }
}
