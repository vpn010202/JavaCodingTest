package domain;

import util.DateUtil;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Bill {

    private final Long id;
    private BillType type;
    private String provider;
    private BigDecimal amount;
    private LocalDate dueDate;
    private BillStatus status;

    public Bill(Long id,
                BillType type,
                String provider,
                BigDecimal amount,
                LocalDate dueDate) {

        validate(type, provider, amount, dueDate);

        this.id = id;
        this.type = type;
        this.provider = provider;
        this.amount = amount;
        this.dueDate = dueDate;
        this.status = BillStatus.NOT_PAID;
    }

    private void validate(BillType type,
                          String provider,
                          BigDecimal amount,
                          LocalDate dueDate) {

        if (type == null) {
            throw new IllegalArgumentException("Bill type is required");
        }

        if (provider == null || provider.isBlank()) {
            throw new IllegalArgumentException("Provider is required");
        }

        if (amount == null ||
                amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }

        if (dueDate == null) {
            throw new IllegalArgumentException("Due date is required");
        }
    }

    public void update(BillType type,
                       String provider,
                       BigDecimal amount,
                       LocalDate dueDate) {

        validate(type, provider, amount, dueDate);

        this.type = type;
        this.provider = provider;
        this.amount = amount;
        this.dueDate = dueDate;
    }

    public void markPaid() {
        this.status = BillStatus.PAID;
    }

    public boolean isPaid() {
        return status == BillStatus.PAID;
    }

    public Long getId() {
        return id;
    }

    public BillType getType() {
        return type;
    }

    public String getProvider() {
        return provider;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public BillStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return String.format(
                "%d | %s | %s | %s | %s | %s",
                id,
                type,
                amount,
                DateUtil.format(
                        dueDate
                ),
                status,
                provider
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bill bill)) return false;
        return Objects.equals(id, bill.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}