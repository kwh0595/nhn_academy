import java.util.Scanner;

/*
 * 콘솔창에 방향을 입력받아 길을 찾아가는 미로 게임입니다.
 * 출구를 찾을 때까지 게임은 계속됩니다.
 * 입구는 (0,0)이며, 출구는(4,4)입니다. 
 * 오른쪽 R , 왼쪽 L, 아래 D, 위 U로 방향을 설정하고 V를 통해 자신이 지나온 길과 현재 상황을 확인할 수 있습니다.
 * 자신이 지나온 길은 8로 표시되며 잘못 들었던 길의 사방이 막혀있으면 2로 채워 지나온 길을 다시 가지 않도록 합니다.
 * 한 칸씩 이동 가능하며, 막힌 길을 가게 되면 이전의 길로 돌아갑니다.
 */
public class Mirogame {
    static int[][] miro = { // 미로 초기화
            { 0, 0, 1, 1, 0 },
            { 1, 0, 0, 0, 0 },
            { 1, 1, 0, 1, 1 },
            { 1, 0, 0, 0, 0 },
            { 1, 1, 1, 0, 0 }
    };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = 0;
        int y = 0;
        while (true) {
            miro[0][0] = 8;
            System.out.print("방향을 입력해주세요(오른쪽 R, 왼쪽 L, 아래 D, 위 U, 지도 현황 V): ");
            String direction = sc.nextLine();
            try {
                switch (direction) { // R,L,D,U 모두 같은 알고리즘이며 방향만 다름
                    case "R": // 오른쪽
                        y++;
                        if (miro[x][y] == 0 || miro[x][y] == 8) {
                            // 갈 수 있는 길이거나 자신이 지나 온 길
                            System.out.println("길을 찾았습니다.");
                            if (miro[x][y] == 8) {
                                // 지나온 길이었을 경우
                                if (miro[x - 1][y - 1] == 2 && miro[x + 1][y - 1] == 2 && miro[x][y - 2] == 2) {
                                    // 위, 아래, 오른쪽이 이미 잘못 들었던 길일 경우
                                    miro[x][y - 1] = 2;
                                    // 지나온 길을 다시 가지 않도록 표시
                                    break;
                                }
                            }
                            miro[x][y] = 8; // 처음 가는 길을 찾으면 표시
                        } else if (miro[x][y] == 2) {
                            // 한 번 잘못 갔던 길일 경우
                            System.out.println("잘못 들었던 길입니다.");
                            y--;
                        } else {
                            // 막힌 길(1)인 경우
                            System.out.println("길을 못 찾았습니다.");
                            miro[x][y] = 2; // 다시 가지 않도록 표시
                            y--;

                        }
                        break;
                    case "L": // 왼쪽
                        y--;
                        if (miro[x][y] == 0 || miro[x][y] == 8) {
                            System.out.println("길을 찾았습니다.");
                            if (miro[x][y] == 8) {
                                if (miro[x - 1][y + 1] == 2 && miro[x + 1][y + 1] == 2 && miro[x][y + 2] == 2) {
                                    miro[x][y + 1] = 2;
                                    break;
                                }
                            }
                            miro[x][y] = 8;
                        } else if (miro[x][y] == 2) {
                            System.out.println("잘못 들었던 길입니다.");
                            y++;
                        } else {
                            System.out.println("길을 못 찾았습니다.");
                            miro[x][y] = 2;
                            y++;
                        }
                        break;
                    case "D": // 아래
                        x++;
                        if (miro[x][y] == 0 || miro[x][y] == 8) {
                            System.out.println("길을 찾았습니다.");
                            if (miro[x][y] == 8) {
                                if (miro[x - 1][y + 1] == 2 && miro[x - 1][y - 1] == 2 && miro[x - 2][y] == 2) {
                                    miro[x - 1][y] = 2;
                                    break;
                                }

                            }
                            miro[x][y] = 8;
                        } else if (miro[x][y] == 2) {
                            System.out.println("잘못 들었던 길입니다.");
                            x--;
                        } else {
                            System.out.println("길을 못 찾았습니다.");
                            miro[x][y] = 2;
                            x--;

                        }
                        break;
                    case "U": // 위
                        x--;
                        if (miro[x][y] == 0 || miro[x][y] == 8) {
                            System.out.println("길을 찾았습니다.");
                            if (miro[x][y] == 8) {
                                if (miro[x + 1][y + 1] == 2 && miro[x + 1][y - 1] == 2 && miro[x + 2][y] == 2) {
                                    miro[x + 1][y] = 2;
                                    break;
                                }
                            }
                            miro[x][y] = 8;
                        } else if (miro[x][y] == 2) {
                            System.out.println("잘못 들었던 길입니다.");
                            x++;
                        } else {
                            System.out.println("길을 못 찾았습니다.");
                            miro[x][y] = 2;
                            x++;

                        }
                        break;
                    case "V": // 현재 지도 상황
                        System.out.println("현재 지도 상황을 표시합니다.");
                        view(miro); // view 메소드 호출
                        break;
                    default: // 지원하는 문자 외 다른 문자를 입력했을 경우
                        System.out.println("올바르지 않은 방향입니다.");
                        break;
                }
                if ((x + 1) == miro.length && (y + 1) == miro[0].length) {
                    // 출구에 도착한 경우
                    System.out.println("게임이 종료됩니다!!");
                    view(miro); // 최종 view 호출
                    break;
                }

            } catch (ArrayIndexOutOfBoundsException e) { // 지도 범위 내에서 벗어난 경우
                System.out.println("존재하지 않는 길입니다.");
                if (direction.equals("R")) {
                    miro[x][y - 1] = 2; // 다시 오지 않도록 표시
                    y -= 2; // 이전 위치로
                } else if (direction.equals("L")) {
                    miro[x][y + 1] = 2;
                    y += 2;
                } else if (direction.equals("D")) {
                    miro[x - 1][y] = 2;
                    x -= 2;
                } else if (direction.equals("U")) {
                    miro[x + 1][y] = 2;
                    x += 2;
                }
            }
        }
        sc.close();
    }

    // 현재 지도 상황을 표시하는 view 메소드
    static void view(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.printf("%d ", array[i][j]);
            }
            System.out.println();
        }
    }
}