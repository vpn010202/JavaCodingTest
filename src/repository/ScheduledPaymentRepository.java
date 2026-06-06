package repository;

import domain.ScheduledPayment;

import java.time.LocalDate;
import java.util.List;

public interface ScheduledPaymentRepository
        extends Repository<ScheduledPayment, Long> {

    List<ScheduledPayment> findDueSchedules(
            LocalDate date
    );
}