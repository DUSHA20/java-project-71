package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class StylishFormatter {

    public static String formatStylish(List<Map<String, Object>> differences) {
        StringBuilder result = new StringBuilder("{\n");
        for (Map<String, Object> diff : differences) {
            switch (diff.get("status").toString()) {
                case "removed" -> result.append("  - ").append(diff.get("key")).append(": ")
                        .append(diff.get("oldValue")).append("\n");
                case "added" -> result.append("  + ").append(diff.get("key")).append(": ")
                        .append(diff.get("newValue")).append("\n");
                case "unchanged" -> result.append("    ").append(diff.get("key")).append(": ")
                        .append(diff.get("oldValue")).append("\n");
                default -> {
                    throw new IllegalArgumentException("Unknown status: " + diff.get("status"));
                }
            }
        }
        result.append("}");
        return result.toString();
    }
}

