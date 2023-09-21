package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import java.io.IOException;

public class DifferTest {

    @Test
    public void testGenerate() throws IOException {
        String filePath1 = "src/test/resources/file1.json";
        String filePath2 = "src/test/resources/file2.json";

        String expected = "{\n" +
                "  - follow: false\n" +
                "  host: \"hexlet.io\"\n" +
                "  - proxy: \"123.234.53.22\"\n" +
                "  - timeout: 50\n" +
                "  + timeout: 20\n" +
                "  + verbose: true\n" +
                "}";

        String result = Differ.generate(filePath1, filePath2);

        assertEquals(expected, result);
    }
}