package domain;

import java.math.BigDecimal;

public class Account {

    private BigDecimal balance = BigDecimal.ZERO;

    public void cashIn(BigDecimal amount) {

        if (amount == null ||
                amount.compareTo(BigDecimal.ZERO) <= 0) {

            throw new IllegalArgumentException(
                    "Amount must be positive"
            );
        }

        balance = balance.add(amount);
    }

    public void deduct(BigDecimal amount) {

        if (amount == null) {
            throw new IllegalArgumentException();
        }

        if (balance.compareTo(amount) < 0) {
            throw new IllegalStateException(
                    "Insufficient fund"
            );
        }

        balance = balance.subtract(amount);
    }

    public boolean hasEnough(BigDecimal amount) {
        return balance.compareTo(amount) < 0;
    }

    public BigDecimal getBalance() {
        return balance;
    }
}