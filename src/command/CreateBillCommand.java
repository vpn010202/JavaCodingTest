package command;

import domain.BillType;
import service.BillService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CreateBillCommand
        implements Command {

    private final BillService billService;

    public CreateBillCommand(
            BillService billService) {

        this.billService = billService;
    }

    @Override
    public void execute(
            String[] args) {

        Long id =
                Long.parseLong(args[1]);

        BillType type =
                BillType.valueOf(args[2]);

        String provider =
                args[3].replace("_", " ");

        BigDecimal amount =
                new BigDecimal(args[4]);

        LocalDate dueDate =
                LocalDate.parse(
                        args[5],
                        DateTimeFormatter.ofPattern(
                                "dd/MM/yyyy"
                        )
                );

        billService.createBill(
                id,
                type,
                provider,
                amount,
                dueDate
        );

        System.out.println(
                "Bill created successfully."
        );
    }
}