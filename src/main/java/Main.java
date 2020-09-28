import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
    static int cases;
    static boolean[][] friends = new boolean[10][10];
    static int n = 0, m = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        cases = sc.nextInt();
        for(int c = 1; c <= cases; c++) {
            n = sc.nextInt();
            m = sc.nextInt();
            friends = new boolean[10][10];
            boolean[] students = new boolean[n];
            int i = 0, j = 0;
            while (--m >= 0) {
                i = sc.nextInt();
                j = sc.nextInt();
                friends[i][j] = friends[j][i] = true;
            }
            System.out.println("---------");
            System.out.println("case" + c);

            IntStream.range(0, n).forEach(x -> System.out.print(x + ":" + students[x] + " "));
            System.out.println();
            System.out.println("---------");

            Arrays.stream(friends).forEach(fr ->{
                IntStream.range(0,10).forEach(x -> System.out.print(fr[x] + " "));
                System.out.println();
            });

            System.out.println("answer : " + mate(students));
        }
    }


    public static int mate(boolean[] students) {

        int startIndex = -1;
        for(int i = 0; i < n; i++) {
            if(!students[i]) {
                startIndex = i;
                break;
            }
        }

        if(startIndex == -1) return 1;

        int ret = 0;

        for(int y = startIndex + 1; y < n; y++){
            if(!students[y] && friends[y][startIndex]) {
                students[startIndex] = students[y] = true;
                ret += mate(students);
                students[startIndex] = students[y] = false;
            }
        }

        return ret;
    }
}