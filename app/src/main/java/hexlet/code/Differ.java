package hexlet.code;

import java.util.Map;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Differ {

    public static String generate(String pathfile1, String pathfile2, String formatName) throws Exception {
        String content1 = readFile(pathfile1);
        String content2 = readFile(pathfile2);

        Map<String, Object> map1 = parseContent(content1, pathfile1);
        Map<String, Object> map2 = parseContent(content2, pathfile2);

        List<Map<String, Object>> result = generateDifference(map1, map2);

        return Formatter.formatStyle(result, formatName);
    }

    public static List<Map<String, Object>> generateDifference(Map<String, Object> map1, Map<String, Object> map2) {
        List<Map<String, Object>> differences = new ArrayList<>();
        Set<String> keysSet = new TreeSet<>(map1.keySet());
        keysSet.addAll(map2.keySet());

        for (String key : keysSet) {
            differences.add(DiffGenerator.generateDiffEntry(key, map1, map2));
        }
        return differences;
    }

    public static String generate(String pathfile1, String pathfile2) throws Exception {
        return generate(pathfile1, pathfile2, "stylish");
    }

    private static String readFile(String filePath) throws Exception {
        Path fullPath = Paths.get(filePath).toAbsolutePath().normalize();
        if (!Files.exists(fullPath)) {
            throw new IllegalArgumentException("File not found: " + filePath);
        }
        return Files.readString(fullPath);
    }

    private static Map<String, Object> parseContent(String content, String filePath) throws Exception {
        if (filePath.endsWith(".json")) {
            return Parser.parseJson(content);
        } else if (filePath.endsWith(".yaml") || filePath.endsWith(".yml")) {
            return Parser.parseYaml(content);
        } else {
            throw new IllegalArgumentException("Unsupported file format: " + filePath);
        }
    }
}

