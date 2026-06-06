package domain;

import java.time.LocalDate;
import java.util.Objects;

public record ScheduledPayment(Long billId, LocalDate scheduledDate) {

    public ScheduledPayment {

        if (billId == null) {
            throw new IllegalArgumentException("Bill id is required");
        }

        if (scheduledDate == null) {
            throw new IllegalArgumentException("Schedule date is required");
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ScheduledPayment(Long id, LocalDate date))) return false;
        return Objects.equals(billId, id)
                && Objects.equals(scheduledDate, date);
    }

    @Override
    public Long billId() {
        return billId;
    }

    @Override
    public LocalDate scheduledDate() {
        return scheduledDate;
    }
}