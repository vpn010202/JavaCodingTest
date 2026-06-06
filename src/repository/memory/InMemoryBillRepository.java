package repository.memory;

import domain.Bill;
import repository.BillRepository;

import java.util.*;
import java.util.stream.Collectors;

public class InMemoryBillRepository
        implements BillRepository {

    private final Map<Long, Bill> storage =
            new HashMap<>();

    @Override
    public Bill save(Bill bill) {

        storage.put(
                bill.getId(),
                bill
        );

        return bill;
    }

    @Override
    public Optional<Bill> findById(Long id) {

        return Optional.ofNullable(
                storage.get(id)
        );
    }

    @Override
    public List<Bill> findAll() {

        return new ArrayList<>(
                storage.values()
        );
    }

    @Override
    public boolean delete(Long id) {

        return storage.remove(id) != null;
    }

    @Override
    public List<Bill> findByProvider(
            String provider) {

        return storage.values()
                .stream()
                .filter(
                        bill ->
                                bill.getProvider()
                                        .equalsIgnoreCase(
                                                provider
                                        )
                )
                .collect(
                        Collectors.toList()
                );
    }
}