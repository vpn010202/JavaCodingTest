import app.ApplicationContext;
import command.Command;
import command.CommandRegistry;

void main() {

    ApplicationContext context =
            new ApplicationContext();

    CommandRegistry registry =
            context.getRegistry();

    Scanner scanner =
            new Scanner(System.in);

    IO.println(
            "Bill Payment System Started"
    );

    while (!Thread.currentThread().isInterrupted())

        try {

            context.getScheduleService()
                    .processSchedules(
                            LocalDate.now()
                    );

            IO.print("> ");

            String input =
                    scanner.nextLine();

            if (input == null ||
                    input.isBlank()) {

                continue;
            }

            String[] tokens =
                    input.trim()
                            .split("\\s+");

            String commandName =
                    tokens[0]
                            .toUpperCase();

            Command command =
                    registry.get(
                            commandName
                    );

            if (command == null) {

                IO.println(
                        "Unknown command."
                );

                continue;
            }

            command.execute(
                    tokens
            );

        } catch (Exception ex) {

            IO.println(
                    ex.getMessage()
            );
        }
}
