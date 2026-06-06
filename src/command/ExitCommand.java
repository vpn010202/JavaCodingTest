package command;

public class ExitCommand
        implements Command {

    @Override
    public void execute(
            String[] args) {

        System.out.println(
                "Good bye!"
        );

        System.exit(0);
    }
}