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
        Set<String> keysSet = new TreeSet<>(map1.keySet());
        keysSet.addAll(map2.keySet());

        for (String key : keysSet) {
            Map<String, Object> diff = new LinkedHashMap<>();
            if (map1.containsKey(key) && !map2.containsKey(key)) {
                diff.put("key", key);
                diff.put("oldValue", map1.get(key));
                diff.put("status", "removed");
            } else if (!map1.containsKey(key) && map2.containsKey(key)) {
                diff.put("key", key);
                diff.put("newValue", map2.get(key));
                diff.put("status", "added");
            } else if (!Objects.equals(map1.get(key), map2.get(key))) {
                diff.put("key", key);
                diff.put("oldValue", map1.get(key));
                diff.put("newValue", map2.get(key));
                diff.put("status", "updated");
            } else {
                diff.put("key", key);
                diff.put("oldValue", map1.get(key));
                diff.put("status", "unchanged");
            }
            differences.add(diff);
        }
        return differences;
    }

    public static String generate(String pathfile1, String pathfile2) throws Exception {
        return generate(pathfile1, pathfile2, "stylish");
    }
}

