package command;

import domain.Bill;
import service.BillService;
import util.DateUtil;

import java.util.List;

public class SearchBillByProviderCommand
        implements Command {

    private final BillService billService;

    public SearchBillByProviderCommand(
            BillService billService) {

        this.billService = billService;
    }

    @Override
    public void execute(String[] args) {

        String provider =
                String.join(
                        " ",
                        java.util.Arrays.copyOfRange(
                                args,
                                1,
                                args.length
                        )
                );

        System.out.printf(
                "%-8s %-12s %-12s %-15s %-12s %-20s%n",
                "Bill No.",
                "Type",
                "Amount",
                "Due Date",
                "State",
                "Provider"
        );
        List<Bill> listBillOfProvider = billService
                .searchByProvider(provider);
        for (Bill bill : listBillOfProvider) {
            System.out.printf(
                    "%-8d %-12s %-12s %-15s %-12s %-20s%n",
                    bill.getId(),
                    bill.getType(),
                    bill.getAmount(),
                    DateUtil.format(
                            bill.getDueDate()
                    ),
                    bill.getStatus(),
                    bill.getProvider()
            );
        }

    }
}