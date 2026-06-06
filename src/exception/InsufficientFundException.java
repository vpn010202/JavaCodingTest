package exception;

public class InsufficientFundException
        extends RuntimeException {

    public InsufficientFundException() {

        super(
                "Sorry! Not enough fund to proceed with payment."
        );
    }
}