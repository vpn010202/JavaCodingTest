package command;

import service.ScheduleService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SchedulePaymentCommand
        implements Command {

    private final ScheduleService scheduleService;

    public SchedulePaymentCommand(
            ScheduleService scheduleService) {

        this.scheduleService = scheduleService;
    }

    @Override
    public void execute(
            String[] args) {

        Long billId =
                Long.parseLong(
                        args[1]
                );

        LocalDate scheduledDate =
                LocalDate.parse(
                        args[2],
                        DateTimeFormatter.ofPattern(
                                "dd/MM/yyyy"
                        )
                );

        scheduleService.schedulePayment(
                billId,
                scheduledDate
        );

        System.out.printf(
                "Payment for bill id %s is scheduled on %s%n"
                , billId, scheduledDate
        );
    }
}