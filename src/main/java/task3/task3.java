package task3;

import com.google.gson.*;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class task3 {


    private static void fillValues(JsonObject test, Map<Integer, String> values) {
        int testId = test.get("id").getAsInt();
        if (values.containsKey(testId)) {
            test.addProperty("value", values.get(testId));
        }


        if (test.has("values")) {
            JsonArray subtests = test.getAsJsonArray("values");
            for (JsonElement subtest : subtests) {
                fillValues(subtest.getAsJsonObject(), values);
            }
        }
    }

    public static void main(String[] args) {

        String valuesFilePath = "src/main/java/task3/values.json"; // Замените на путь к файлу values.json
        String testsFilePath = "src/main/java/task3/tests.json"; // Замените на путь к файлу tests.json
        String reportFilePath = "src/main/java/task3/report.json"; // Замените на путь к файлу report.json

        Map<Integer, String> valuesMap = new HashMap<>();


        try (Reader valuesReader = new FileReader(valuesFilePath)) {
            Gson gson = new Gson();
            JsonObject valuesJson = gson.fromJson(valuesReader, JsonObject.class);
            JsonArray valuesArray = valuesJson.getAsJsonArray("values");
            for (JsonElement element : valuesArray) {
                JsonObject obj = element.getAsJsonObject();
                int id = obj.get("id").getAsInt();
                String value = obj.get("value").getAsString();
                valuesMap.put(id, value);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }


        JsonObject tests;
        try (Reader testsReader = new FileReader(testsFilePath)) {
            Gson gson = new Gson();
            tests = gson.fromJson(testsReader, JsonObject.class);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        JsonArray testsArray = tests.getAsJsonArray("tests");
        for (JsonElement test : testsArray) {
            fillValues(test.getAsJsonObject(), valuesMap);
        }


        try (Writer writer = new FileWriter(reportFilePath)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(tests, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Report generated successfully in " + reportFilePath);
    }
}
