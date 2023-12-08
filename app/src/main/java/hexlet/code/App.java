package hexlet.code;

import picocli.CommandLine;

public class App {
    public static void main(String[] args) {

        AppCommand appCommand = new AppCommand();
        CommandLine cmd = new CommandLine(appCommand);
        cmd.execute(args);
    }
}
