import java.util.Arrays;
import java.util.Scanner;

public class MazeRecursize {
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

    public static void createMaze(int[][] maze) {
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
        if (findPath(maze, 0, 0)) { // 갈 수 있는 길이 있으면 미로 완성
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

    public static boolean findPath(int[][] maze, int x, int y) {
        if (x < 0 || y < 0 || x >= maze.length || y >= maze[0].length || maze[x][y] != PATH) {
            return false;
        }

        maze[x][y] = WALK;

        if (x == maze.length - 1 && y == maze[0].length - 1) {
            return true;
        }

        if (findPath(maze, x - 1, y) || findPath(maze, x + 1, y) || findPath(maze, x, y - 1)
                || findPath(maze, x, y + 1)) {
            return true;
        }

        maze[x][y] = PATH; // 현재 위치가 막힌 길인 경우, 되돌아가야 하므로 PATH로 다시 설정
        return false;
    }

}
