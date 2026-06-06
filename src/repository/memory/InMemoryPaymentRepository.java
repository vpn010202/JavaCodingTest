package repository.memory;

import domain.Payment;
import repository.PaymentRepository;

import java.util.*;

public class InMemoryPaymentRepository
        implements PaymentRepository {

    private final Map<Long, Payment> storage =
            new HashMap<>();

    @Override
    public Payment save(
            Payment payment) {

        storage.put(
                payment.getId(),
                payment
        );

        return payment;
    }

    @Override
    public Optional<Payment> findById(
            Long id) {

        return Optional.ofNullable(
                storage.get(id)
        );
    }

    @Override
    public List<Payment> findAll() {

        return new ArrayList<>(
                storage.values()
        );
    }

    @Override
    public boolean delete(Long id) {

        return storage.remove(id) != null;
    }
}