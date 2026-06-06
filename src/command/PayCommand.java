package command;

import service.PaymentService;

import java.util.Arrays;
import java.util.List;

public class PayCommand
        implements Command {

    private final PaymentService paymentService;

    public PayCommand(
            PaymentService paymentService) {

        this.paymentService = paymentService;
    }

    @Override
    public void execute(
            String[] args) {
        Long billId =
                Long.parseLong(args[1]);
        if (args.length == 2) {

            paymentService.pay(
                    billId
            );

            System.out.println(
                    "Payment has been completed for Bill with id "
                            + billId
                            + "."
            );

            System.out.println(
                    "Your current balance is: "
                            + paymentService.getBalance()
            );

            return;
        }

        List<Long> billIds =
                Arrays.stream(args)
                        .skip(1)
                        .map(Long::parseLong)
                        .toList();

        paymentService.payMultiple(
                billIds
        );

        System.out.println(
                "Multiple payment completed."
        );
    }
}