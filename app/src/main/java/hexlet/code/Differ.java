package hexlet.code;

import java.util.Map;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.ArrayList;

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
            differences.add(DiffGenerator.generateDiffEntry(key, map1, map2));
        }
        return differences;
    }

    public static String generate(String pathfile1, String pathfile2) throws Exception {
        return generate(pathfile1, pathfile2, "stylish");
    }
}

