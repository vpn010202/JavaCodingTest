package command;

import domain.Bill;
import service.BillService;
import util.DateUtil;

public class ViewBillCommand
        implements Command {

    private final BillService billService;

    public ViewBillCommand(
            BillService billService) {

        this.billService = billService;
    }

    @Override
    public void execute(
            String[] args) {

        Long billId =
                Long.parseLong(args[1]);

        Bill bill =
                billService.getBill(
                        billId
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