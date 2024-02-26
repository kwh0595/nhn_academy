import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class MazeStack {
    private static final int PATH = 0;
    private static final int WALL = 1;
    private static final int WALK = 2;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        int maze[][] = new int[n][m];
        createMaze(maze);
    }

    public static void createMaze(int[][] maze) { // 미로 생성 메소드
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                maze[i][j] = (Math.random() < 0.5) ? PATH : WALL; // 미로 랜덤 생성
            }
        }
        maze[0][0] = maze[maze.length - 1][maze[0].length - 1] = PATH; // 출구와 입구는 1로 통일
        int preMaze[][] = new int[maze.length][maze[0].length]; // 처음 미로 복사본
        for (int i = 0; i < maze.length; i++) {
            preMaze[i] = Arrays.copyOf(maze[i], maze[i].length);
        }
        if (findPath(maze)) { // 갈 수 있는 길이 있으면 미로 완성
            printMaze(preMaze); // 처음 미로 출력
            printMaze(maze); // 길을 찾은 미로 출력
        } else {
            createMaze(maze); // 찾을 수 없는 길일 시, 미로 재생성
        }
    }

    public static void printMaze(int[][] maze) { // 미로 출력 메소드
        for (int[] i : maze) {
            for (int j : i) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static boolean findPath(int[][] maze) { // 길 찾는 메소드
        Stack<xy> stack = new Stack<>();
        stack.push(new xy(0, 0)); // 처음 좌표 값을 스택에 넣기
        while (!stack.isEmpty()) { // 스택이 비어있지 않을 동안 반복 -> 방문할 길이 없을 때까지
            xy current = stack.pop(); // 좌표 값을 꺼내고
            int x = current.x; // x에 할당
            int y = current.y; // y에 할당

            if (x < 0 || y < 0 || x >= maze.length || y >= maze[0].length || maze[x][y] != PATH) { // x,y가 미로의 범위를 벗어나거나
                                                                                                   // 길이 아닐 경우
                continue;
            }
            maze[x][y] = WALK; // 미로에 지나온 길임을 표시 -> 2

            if (x == maze.length - 1 && y == maze[0].length - 1) { // 출구에 도착할시
                return true; // 참 반환
            }
            stack.push(new xy(x - 1, y)); // 근처의 좌표를 스택에 넣기
            stack.push(new xy(x + 1, y));
            stack.push(new xy(x, y - 1));
            stack.push(new xy(x, y + 1));
        }
        return false; // 길을 못 찾았을 경우 거짓 반환
    }
}

class xy { // 좌표 클래스
    int x;
    int y;

    xy(int x, int y) { // 파라미터 2개를 가진 생성자
        this.x = x;
        this.y = y;
    }
}