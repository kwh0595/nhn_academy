import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.Scanner;

public class TikTacToe {

    public static void main(String[] args) { // 게임을 실행하는 메인 메소드
        String arr[][] = new String[3][3];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j] = " ";
            }
        }
        String user = "USER";
        String computer = "COMPUTER";
        while (true) {
            yourTurn(user, arr);
            board(arr);
            if (winner(user, arr)) {
                System.out.println("사용자 승리");
                break;
            }
            yourTurn(computer, arr);
            board(arr);
            if (winner(computer, arr)) {
                System.out.println("컴퓨터 승리");
                break;
            }
        }
    }

    public static void board(String arr[][]) { // 보드를 그리는 메소드

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (j != 0) {
                    System.out.print("| " + arr[i][j] + " ");
                } else {
                    System.out.print(" " + arr[i][j] + " ");
                }
            }
            System.out.println();
            if (i < 2) { // 마지막 줄에는 구분선을 그리지 않음
                System.out.println("---|---|---");
            }
        }
        System.out.println();
    }

    public static void yourTurn(String name, String[][] arr) { // 차례에 따라 입력받는 메소드
        if (name.equals("USER")) {
            System.out.print("사용자 턴(x,y): ");
            Scanner sc = new Scanner(System.in);
            StringTokenizer str = new StringTokenizer(sc.nextLine(), " ");
            int x = Integer.parseInt(str.nextToken());
            int y = Integer.parseInt(str.nextToken());
            draw(name, x, y, arr);

        } else {
            System.out.println("컴퓨터 턴");
            int i = 0;
            while (true) {
                int x = (int) ((Math.random() * ((arr.length - i) - 0)) + i);
                int y = (int) ((Math.random() * ((arr.length - i) - 0)) + i);
                if (arr[x][y] == " ") {
                    draw(name, x, y, arr);
                    break;
                }
            }
        }
    }

    public static void draw(String name, int x, int y, String arr[][]) { // 입력 값을 보드에 찍는 메소드
        if (name.equals("USER")) {
            if (arr[x][y] != " ") {
                yourTurn(name, arr);
            } else {
                arr[x][y] = "O";
            }
        } else {
            arr[x][y] = "X";
        }
    }

    public static boolean winner(String name, String[][] arr) {// 승리자 판별 메소드
        int column = 0;
        int row = 0;
        int slash = 0;
        int slash2 = 0;
        for (int i = 0; i < arr.length; i++) { // 가로
            for (int j = 0; j < arr[i].length; j++) {
                if (j + 1 < arr.length && arr[i][j].equals(arr[i][j + 1]) && !arr[i][j].equals(" ")) {
                    column++;
                    if (column == 2) {
                        return true;
                    }
                }
            }
            column = 0;
        }
        for (int j = 0; j < arr[0].length; j++) { // 세로
            row = 0;
            for (int i = 0; i < arr.length; i++) {
                if (i + 1 < arr.length && arr[i][j].equals(arr[i + 1][j]) && !arr[i][j].equals(" ")) {
                    row++;
                    if (row == 2) {
                        return true;
                    }
                }

                if (i == j) { // 대각선1
                    if (i + 1 < arr.length && j + 1 < arr.length) {
                        if (arr[i][j].equals(arr[i + 1][j + 1]) && !arr[i][j].equals(" ")) {
                            slash++;
                            if (slash == 2) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        for (int i = 0; i < arr.length; i++) { // 대각선2
            for (int j = 0; j < arr[i].length; j++) {
                if (i + j == arr.length - 1) {
                    if (i + 1 < arr.length && j - 1 >= 0) {
                        if (arr[i][j].equals(arr[i + 1][j - 1]) && !arr[i][j].equals(" ")) {
                            slash2++;
                            if (slash2 == 2) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}
