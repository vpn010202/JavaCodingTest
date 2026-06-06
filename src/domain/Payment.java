package domain;

import util.DateUtil;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Payment {

    private final Long id;
    private final Long billId;
    private final BigDecimal amount;
    private final LocalDate paymentDate;
    private PaymentStatus status;

    public Payment(Long id,
                   Long billId,
                   BigDecimal amount,
                   LocalDate paymentDate,
                   PaymentStatus status) {

        this.id = id;
        this.billId = billId;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.status = status;
    }

    public void markProcessed() {
        this.status = PaymentStatus.PROCESSED;
    }

    public Long getId() {
        return id;
    }

    public Long getBillId() {
        return billId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {

        return String.format(
                "%d | %s | %s | %s | %d",
                id,
                amount,
                DateUtil.format(
                        paymentDate
                ),
                status,
                billId
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Payment payment)) return false;
        return Objects.equals(id, payment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}