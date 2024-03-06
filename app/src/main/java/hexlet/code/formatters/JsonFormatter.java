package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Map;

public class JsonFormatter {
    public static String formatJson(List<Map<String, Object>> differences) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(differences);
        } catch (Exception e) {
            System.out.println("Error formatting differences to JSON: " + e.getMessage());
            return "";
        }
    }
}
