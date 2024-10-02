package task1;

import java.util.ArrayList;
import java.util.List;

public class task1 {
    public static void main(String[] args) {
    int n = 4;
    int m = 3;

    List<Integer> path = findPath(n, m);
    System.out.println(path);
}

    public static List<Integer> findPath(int n, int m) {
        List<Integer> path = new ArrayList<>();
        int[] circularArray = new int[n];

        for (int i = 0; i < n; i++) {
            circularArray[i] = i + 1;
        }

        int start = 0;
        path.add(circularArray[start]);

        while (true) {
            start = (start + m - 1) % n;
            if (start == 0) {
                break;
            }
            path.add(circularArray[start]);
        }

        return path;
    }
}

