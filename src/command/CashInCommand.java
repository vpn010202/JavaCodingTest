package command;

import service.PaymentService;

import java.math.BigDecimal;

public class CashInCommand
        implements Command {

    private final PaymentService paymentService;

    public CashInCommand(
            PaymentService paymentService) {

        this.paymentService = paymentService;
    }

    @Override
    public void execute(
            String[] args) {

        BigDecimal amount =
                new BigDecimal(args[1]);

        paymentService.cashIn(amount);

        System.out.println(
                "Your available balance: "
                        + paymentService.getBalance()
        );
    }
}