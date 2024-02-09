package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class PlainFormatter {

    public static String formatPlain(List<Map<String, Object>> differences) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < differences.size(); i++) {
            Map<String, Object> diff = differences.get(i);
            String key = (String) diff.get("key");
            String status = (String) diff.get("status");
            String oldValue = formatValue(diff.get("oldValue"));
            String newValue = formatValue(diff.get("newValue"));

            switch (status) {
                case "removed":
                    result.append("Property '").append(key).append("' was removed\n");
                    break;
                case "added":
                    result.append("Property '").append(key).append("' was added with value: ").append(newValue)
                            .append(i < differences.size() - 1 ? "\n" : "");
                    break;
                case "updated":
                    result.append("Property '").append(key).append("' was updated. From ").append(oldValue)
                            .append(" to ").append(newValue).append(i < differences.size() - 1 ? "\n" : "");
                    break;
                default:
                    break;
            }
        }
        return result.toString();
    }

    private static String formatValue(Object value) {
        if (value instanceof Map || value instanceof List) {
            return "[complex value]";
        }
        if (value instanceof String) {
            return "'" + value + "'";
        }
        return String.valueOf(value);
    }
}

