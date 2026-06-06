package exception;

public class DuplicateBillException
        extends RuntimeException {

    public DuplicateBillException(
            Long billId) {

        super(
                "Duplicate bill id: "
                        + billId
        );
    }
}