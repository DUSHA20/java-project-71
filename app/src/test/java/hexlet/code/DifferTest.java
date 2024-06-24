package hexlet.code;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {

    private String readExpectedResult(String expectedPath) throws Exception {
        Path path = Paths.get(getClass().getClassLoader().getResource(expectedPath).toURI());
        return new String(Files.readAllBytes(path));
    }

    @Test
    public void testGenerateNestedJson() throws Exception {
        String filePath1 = "src/test/resources/fixtures/file3.json";
        String filePath2 = "src/test/resources/fixtures/file4.json";

        String formattedResult = Differ.generate(filePath1, filePath2, "stylish");

        String expectedPath = "fixtures/expected/StylishResult.txt";
        String expected = readExpectedResult(expectedPath);

        assertEquals(expected, formattedResult);
    }

    @Test
    public void testGenerateNestedYml() throws Exception {
        String filePath1 = "src/test/resources/fixtures/file3.yml";
        String filePath2 = "src/test/resources/fixtures/file4.yml";

        String formattedResult = Differ.generate(filePath1, filePath2, "stylish");

        String expectedPath = "fixtures/expected/StylishResult.txt";
        String expected = readExpectedResult(expectedPath);

        assertEquals(expected, formattedResult);
    }

    @Test
    public void testGenerateJsonPlain() throws Exception {
        String filePath1 = "src/test/resources/fixtures/file3.json";
        String filePath2 = "src/test/resources/fixtures/file4.json";

        String formattedResult = Differ.generate(filePath1, filePath2, "plain");

        String expectedPath = "fixtures/expected/PlainResult.txt";
        String expected = readExpectedResult(expectedPath);

        assertEquals(expected, formattedResult);
    }

    @Test
    public void testGenerateYmlPlain() throws Exception {
        String filePath1 = "src/test/resources/fixtures/file3.yml";
        String filePath2 = "src/test/resources/fixtures/file4.yml";

        String formattedResult = Differ.generate(filePath1, filePath2, "plain");

        String expectedPath = "fixtures/expected/PlainResult.txt";
        String expected = readExpectedResult(expectedPath);

        assertEquals(expected, formattedResult);
    }

    @Test
    public void testGenerateJson() throws Exception {
        String filePath1 = "src/test/resources/fixtures/file3.json";
        String filePath2 = "src/test/resources/fixtures/file4.json";

        String formattedResult = Differ.generate(filePath1, filePath2, "json");

        String expectedPath = "fixtures/expected/JsonResult.txt";
        String expected = readExpectedResult(expectedPath);

        assertEquals(expected, formattedResult);
    }

    @Test
    public void testGenerateYml() throws Exception {
        String filePath1 = "src/test/resources/fixtures/file3.yml";
        String filePath2 = "src/test/resources/fixtures/file4.yml";

        String formattedResult = Differ.generate(filePath1, filePath2, "json");

        String expectedPath = "fixtures/expected/JsonResult.txt";
        String expected = readExpectedResult(expectedPath);

        assertEquals(expected, formattedResult);
    }

    @Test
    public void testGenerateWithoutFormatterJson() throws Exception {
        String filePath1 = "src/test/resources/fixtures/file3.json";
        String filePath2 = "src/test/resources/fixtures/file4.json";

        String formattedResult = Differ.generate(filePath1, filePath2); // Не указываем форматтер

        String expectedPath = "fixtures/expected/StylishResult.txt";
        String expected = readExpectedResult(expectedPath);

        assertEquals(expected, formattedResult);
    }

    @Test
    public void testGenerateWithoutFormatterYml() throws Exception {
        String filePath1 = "src/test/resources/fixtures/file3.yml";
        String filePath2 = "src/test/resources/fixtures/file4.yml";

        String formattedResult = Differ.generate(filePath1, filePath2); // Не указываем форматтер

        String expectedPath = "fixtures/expected/StylishResult.txt";
        String expected = readExpectedResult(expectedPath);

        assertEquals(expected, formattedResult);
    }
}

