package exception;

public class BillNotFoundException
        extends RuntimeException {

    public BillNotFoundException(Long billId) {

        super(
                "Sorry! Not found a bill with such id"
        );
    }
}