package repository.memory;

import domain.ScheduledPayment;
import repository.ScheduledPaymentRepository;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class InMemoryScheduledPaymentRepository
        implements ScheduledPaymentRepository {

    private final Map<Long, ScheduledPayment>
            storage = new HashMap<>();

    private long sequence = 1;

    @Override
    public ScheduledPayment save(
            ScheduledPayment payment) {

        storage.put(
                sequence++,
                payment
        );

        return payment;
    }

    @Override
    public Optional<ScheduledPayment> findById(
            Long id) {

        return Optional.ofNullable(
                storage.get(id)
        );
    }

    @Override
    public List<ScheduledPayment> findAll() {

        return new ArrayList<>(
                storage.values()
        );
    }

    @Override
    public boolean delete(Long id) {

        return storage.remove(id) != null;
    }

    @Override
    public List<ScheduledPayment>
    findDueSchedules(
            LocalDate date) {

        return storage.values()
                .stream()
                .filter(
                        schedule ->
                                !schedule
                                        .scheduledDate()
                                        .isAfter(date)
                )
                .collect(
                        Collectors.toList()
                );
    }
}