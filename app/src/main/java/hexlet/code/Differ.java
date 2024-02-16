package hexlet.code;

import java.util.Map;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Differ {

    public static String generate(String pathfile1, String pathfile2, String formatName) throws Exception {
        Map<String, Object> map1 = Parser.parseFile(pathfile1);
        Map<String, Object> map2 = Parser.parseFile(pathfile2);

        List<Map<String, Object>> result = generateDifference(map1, map2);

        return Formatter.formatStyle(result, formatName);
    }

    public static List<Map<String, Object>> generateDifference(Map<String, Object> map1, Map<String, Object> map2) {
        List<Map<String, Object>> differences = new ArrayList<>();

        // Обработаем ключи из первой мапы
        for (String key : map1.keySet()) {
            if (!map2.containsKey(key)) {
                differences.add(generateDiffEntry(key, map1.get(key), null, "removed"));
            } else if (!Objects.equals(map1.get(key), map2.get(key))) {
                differences.add(generateDiffEntry(key, map1.get(key), map2.get(key), "updated"));
            } else {
                differences.add(generateDiffEntry(key, map1.get(key), map2.get(key), "unchanged"));
            }
        }

        // Обработаем ключи из второй мапы, которых нет в первой
        for (String key : map2.keySet()) {
            if (!map1.containsKey(key)) {
                differences.add(generateDiffEntry(key, null, map2.get(key), "added"));
            }
        }

        return differences;
    }

    private static Map<String, Object> generateDiffEntry(
            String key,
            Object value1,
            Object value2,
            String status
    ) {
        Map<String, Object> diff = new LinkedHashMap<>();
        diff.put("key", key);
        diff.put("status", status);

        if (value1 != null) {
            diff.put("oldValue", value1);
        }

        if (value2 != null) {
            diff.put("newValue", value2);
        }

        return diff;
    }

    public static String generate(String pathfile1, String pathfile2) throws Exception {
        return generate(pathfile1, pathfile2, "stylish");
    }
}

