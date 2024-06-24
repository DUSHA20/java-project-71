package hexlet.code;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class DiffGenerator {

    public static Map<String, Object> generateDiffEntry(
            String key,
            Map<String, Object> map1,
            Map<String, Object> map2
    ) {
        Map<String, Object> diff = new LinkedHashMap<>();
        Object value1 = map1.get(key);
        Object value2 = map2.get(key);

        diff.put("key", key);
        if (map1.containsKey(key) && map2.containsKey(key)) {
            if (!Objects.equals(value1, value2)) {
                diff.put("oldValue", value1);
                diff.put("newValue", value2);
                diff.put("status", "updated");
            } else {
                diff.put("oldValue", value1);
                diff.put("status", "unchanged");
            }
        } else if (map1.containsKey(key)) {
            diff.put("oldValue", value1);
            diff.put("status", "removed");
        } else {
            diff.put("newValue", value2);
            diff.put("status", "added");
        }
        return diff;
    }
}
