package hexlet.code;

import hexlet.code.formatters.JsonFormatter;
import picocli.CommandLine;
import java.util.concurrent.Callable;
import hexlet.code.formatters.StylishFormatter;
import hexlet.code.formatters.PlainFormatter;

import java.util.List;
import java.util.Map;

@CommandLine.Command(name = "gendiff", mixinStandardHelpOptions = true,
        version = "1.0",
        description = "Compares two configuration files and shows a difference.")
public class AppCommand implements Runnable, Callable<String> {

    @CommandLine.Parameters(index = "0", description = "path to first file")
    private String filePath1;

    @CommandLine.Parameters(index = "1", description = "path to second file")
    private String filePath2;

    @CommandLine.Option(names = {"-f", "--format"}, defaultValue = "stylish",
            description = "output format [default: ${DEFAULT-VALUE}]")
    private String format;

    @CommandLine.Option(names = {"-h", "--help"}, usageHelp = true, description = "Show this help message and exit.")
    private boolean helpRequested;

    @CommandLine.Option(
            names = {"-V", "--version"},
            versionHelp = true,
            description = "Print version information and exit."
    )
    private boolean versionRequested;

    @Override
    public void run() {
        if (helpRequested) {
            CommandLine cmd = new CommandLine(this.getClass().getAnnotation(CommandLine.Command.class));
            System.out.println(cmd.getUsageMessage());
            return;
        }
        if (versionRequested) {
            CommandLine cmd = new CommandLine(this.getClass().getAnnotation(CommandLine.Command.class));
            cmd.printVersionHelp(System.out);
            return;
        }

        try {
            Map<String, Object> map1 = Parser.parseFile(filePath1);
            Map<String, Object> map2 = Parser.parseFile(filePath2);

            List<Map<String, Object>> differences = Differ.generateDifference(map1, map2);

            String formattedResult;
            if ("stylish".equals(format)) {
                formattedResult = StylishFormatter.formatStylish(differences);
            } else if ("plain".equals(format)) {
                formattedResult = PlainFormatter.formatPlain(differences);
            } else if ("json".equals(format)) {
                formattedResult = JsonFormatter.formatJson(differences);
            } else {
                throw new IllegalArgumentException("Unsupported format: " + format);
            }
            System.out.println(formattedResult);
        } catch (Exception e) {
            System.out.println("Error reading or comparing files: " + e.getMessage());
        }
    }

    @Override
    public String call() throws Exception {
        return Differ.generate(filePath1, filePath2);
    }
}
