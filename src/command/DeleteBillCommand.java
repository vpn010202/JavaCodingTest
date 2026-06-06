package command;

import service.BillService;

public class DeleteBillCommand
        implements Command {

    private final BillService billService;

    public DeleteBillCommand(
            BillService billService) {

        this.billService = billService;
    }

    @Override
    public void execute(
            String[] args) {

        Long billId =
                Long.parseLong(args[1]);

        billService.deleteBill(
                billId
        );

        System.out.println(
                "Bill deleted successfully."
        );
    }
}