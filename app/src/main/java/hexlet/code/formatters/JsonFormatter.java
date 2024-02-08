package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class JsonFormatter {

    public static String formatJson(List<Map<String, Object>> differences) {
        StringBuilder result = new StringBuilder("[\n");

        for (Map<String, Object> diff : differences) {
            result.append("{\n");
            result.append("  \"key\" : \"").append(diff.get("key")).append("\",\n");

            if (diff.containsKey("oldValue")) {
                result.append("  \"oldValue\" : ").append(formatValue(diff.get("oldValue"))).append(",\n");
            }

            if (diff.containsKey("newValue")) {
                result.append("  \"newValue\" : ").append(formatValue(diff.get("newValue"))).append(",\n");
            }

            result.append("  \"status\" : \"").append(diff.get("status")).append("\"\n");
            result.append("},\n");
        }

        if (!differences.isEmpty()) {
            result.deleteCharAt(result.length() - 2).append('\n');
        }

        result.append("]");
        return result.toString();
    }

    private static String formatValue(Object value) {
        if (value instanceof String) {
            return "\"" + value + "\"";
        }
        return String.valueOf(value);
    }
}
