package task4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class task4 {

    public static int minMoves(int[] nums) {
        Arrays.sort(nums);
        int median = nums[nums.length / 2];
        int moves = 0;
        for (int num : nums) {
            moves += Math.abs(num - median);
        }
        return moves;
    }

    public static void main(String[] args) {

        String filePath = "src/main/java/task4/input.txt"; // Замените на ваше расположение файла

        List<Integer> numbers = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                numbers.add(Integer.parseInt(line.trim()));
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }


        int[] nums = numbers.stream().mapToInt(i -> i).toArray();


        int result = minMoves(nums);


        System.out.println(result);
    }
}
