package exception;

public class BillAlreadyPaidException
        extends RuntimeException {

    public BillAlreadyPaidException(
            Long billId) {

        super(
                "Bill already paid: "
                        + billId
        );
    }
}