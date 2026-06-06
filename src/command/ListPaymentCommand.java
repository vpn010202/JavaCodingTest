package command;

import domain.Payment;
import service.PaymentService;
import util.DateUtil;

public class ListPaymentCommand
        implements Command {

    private final PaymentService paymentService;

    public ListPaymentCommand(
            PaymentService paymentService) {

        this.paymentService = paymentService;
    }

    @Override
    public void execute(
            String[] args) {

        System.out.printf(
                "%-5s %-12s %-15s %-12s %-10s%n",
                "No.",
                "Amount",
                "Payment Date",
                "State",
                "Bill Id"
        );

        for (Payment payment :
                paymentService.getHistory()) {

            System.out.printf(
                    "%-5d %-12s %-15s %-12s %-10d%n",
                    payment.getId(),
                    payment.getAmount(),
                    DateUtil.format(
                            payment.getPaymentDate()
                    ),
                    payment.getStatus(),
                    payment.getBillId()
            );
        }
    }
}