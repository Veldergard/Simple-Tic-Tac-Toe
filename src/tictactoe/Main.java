package tictactoe;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static Scanner scanner;

    public static void main(String[] args) {
        char[][] grid = new char[3][3];
        scanner = new Scanner(System.in);
        initGrid(grid);
        printGrid(grid);
        while (true) {
            makePlayerMove(grid, 'X');
            printGrid(grid);
            if (checkWin(grid, 'X')) {
                System.out.println("X wins");
                break;
            }
            makePlayerMove(grid, 'O');
            printGrid(grid);
            if (checkWin(grid, 'O')) {
                System.out.println("O wins");
                break;
            }
        }
    }

    public static void initGrid(char[][] grid) {
        for (char[] rows : grid) {
            Arrays.fill(rows, ' ');
        }
    }

    public static void makePlayerMove(char[][] grid, char player) {
        System.out.print("Enter the coordinates: ");
        String yString = scanner.next();
        String xString = scanner.next();
        int y;
        int x;
        if (isInt(yString) && isInt(xString)) {
            y = Integer.parseInt(yString);
            x = Integer.parseInt(xString);
        } else {
            System.out.println("You should enter numbers!");
            makePlayerMove(grid, player);
            return;
        }
        if (x >= 1 && x <= 3 && y >= 1 && y <= 3) {
            if (grid[y - 1][x - 1] != ' ') {
                System.out.println("This cell is occupied! Choose another one!");
                makePlayerMove(grid, player);
            } else {
                grid[y - 1][x - 1] = player;
            }
        } else {
            System.out.println("Coordinates should be from 1 to 3!");
            makePlayerMove(grid, player);
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

    public static boolean checkWin(char[][] grid, char player) {
        if (grid[0][0] == player && grid[0][1] == player && grid[0][2] == player) {
            return true;
        }
        if (grid[1][0] == player && grid[1][1] == player && grid[1][2] == player) {
            return true;
        }
        if (grid[2][0] == player && grid[2][1] == player && grid[2][2] == player) {
            return true;
        }
        if (grid[0][0] == player && grid[1][0] == player && grid[2][0] == player) {
            return true;
        }
        if (grid[0][1] == player && grid[1][1] == player && grid[2][1] == player) {
            return true;
        }
        if (grid[0][2] == player && grid[1][2] == player && grid[2][2] == player) {
            return true;
        }
        if (grid[0][0] == player && grid[1][1] == player && grid[2][2] == player) {
            return true;
        }
        return grid[2][0] == player && grid[1][1] == player && grid[0][2] == player;
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
