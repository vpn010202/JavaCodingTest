package command;

import domain.Bill;
import service.BillService;
import util.DateUtil;

public class ListBillCommand
        implements Command {

    private final BillService billService;

    public ListBillCommand(
            BillService billService) {

        this.billService = billService;
    }

    @Override
    public void execute(String[] args) {

        System.out.printf(
                "%-8s %-12s %-12s %-15s %-12s %-20s%n",
                "Bill No.",
                "Type",
                "Amount",
                "Due Date",
                "State",
                "Provider"
        );

        for (Bill bill : billService.getAllBills()) {

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