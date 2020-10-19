import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int cases;
    static int H = 0, W = 0;
    static int coverType[][][] = {
        {{0,0},{1,0},{0,1}},
        {{0,0},{0,1},{1,1}},
        {{0,0},{1,0},{1,1}},
        {{0,0},{1,0},{1,-1}}
    };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        cases = sc.nextInt();
        for(int c = 1; c <= cases; c++) {
            H = sc.nextInt();
            W = sc.nextInt();
            int[][] matrix = new int[H][W];
            sc.nextLine();
            for(int i = 0; i < H; i++) {
                String line = sc.nextLine();
                for(int j = 0; j < W; j++) {
                    if(line.charAt(j) == (char)'#') {
                        matrix[i][j] = 1;
                    } else {
                        matrix[i][j] = 0;
                    }
                }
            }

            //            Arrays.stream(matrix).forEach(ints -> {
            //                Arrays.stream(ints).forEach(System.out::print);
            //                System.out.println();
            //            });

            System.out.println(cover(matrix));
        }
    }

    public static boolean set(int[][] matrix, int y, int x, int type, int delta) {
        boolean ok = true;

        for(int i =0; i < 3; ++i) {
            int ny = y + coverType[type][i][0];
            int nx = x + coverType[type][i][1];
            if(ny < 0 || ny >= H || nx < 0 || nx >= W) {
                ok = false;
            } else if ((matrix[ny][nx] += delta) > 1) {
                ok = false;
            }
        }

        return ok;
    }

    public static int cover(int[][] matrix) {
        int y = -1;
        int x = -1;
        for(int i =0; i < H; ++i) {
            for(int j = 0; j < W; ++j) {
                if(matrix[i][j] == 0) {
                    y = i;
                    x = j;
                    break;
                }
            }
            if(y != -1) break;
        }

        if(y == -1) return 1;
        int ret = 0;
        for(int type = 0; type < 4; ++type) {
            if(set(matrix, y, x, type, 1)) {
                ret += cover(matrix);
            }
            set(matrix, y, x, type, -1);
        }
        return ret;
    }



}