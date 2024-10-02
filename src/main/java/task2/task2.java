package task2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class task2 {
    public static int checkPointPosition(double x, double y, double centerX, double centerY, double radius) {
        double distanceSquared = Math.pow(x - centerX, 2) + Math.pow(y - centerY, 2);
        double radiusSquared = Math.pow(radius, 2);

        if (distanceSquared == radiusSquared) {
            return 0;
        } else if (distanceSquared < radiusSquared) {
            return 1;
        } else {
            return 2;
        }
    }

    public static void main(String[] args) {

        String circleFilePath = "src/main/java/task2/file1.txt";
        String pointsFilePath = "src/main/java/task2/file2.txt";

        double centerX = 0, centerY = 0, radius = 0;
        List<double[]> points = new ArrayList<>();


        try (BufferedReader circleReader = new BufferedReader(new FileReader(circleFilePath))) {
            String[] center = circleReader.readLine().trim().split(" ");
            centerX = Double.parseDouble(center[0]);
            centerY = Double.parseDouble(center[1]);
            radius = Double.parseDouble(circleReader.readLine().trim());
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }


        try (BufferedReader pointsReader = new BufferedReader(new FileReader(pointsFilePath))) {
            String line;
            while ((line = pointsReader.readLine()) != null) {
                String[] pointCoords = line.trim().split(" ");
                double x = Double.parseDouble(pointCoords[0]);
                double y = Double.parseDouble(pointCoords[1]);
                points.add(new double[]{x, y});
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }


        for (double[] point : points) {
            int result = checkPointPosition(point[0], point[1], centerX, centerY, radius);
            System.out.println(result);
        }
    }

}
