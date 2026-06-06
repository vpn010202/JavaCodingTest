package service;

import domain.Bill;
import domain.BillType;
import exception.BillNotFoundException;
import exception.DuplicateBillException;
import repository.BillRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class BillService {

    private final BillRepository repository;

    public BillService(BillRepository repository) {
        this.repository = repository;
    }

    public Bill createBill(
            Long id,
            BillType type,
            String provider,
            BigDecimal amount,
            LocalDate dueDate) {

        if (repository.findById(id).isPresent()) {
            throw new DuplicateBillException(id);
        }

        Bill bill = new Bill(
                id,
                type,
                provider,
                amount,
                dueDate
        );

        return repository.save(bill);
    }

    public Bill updateBill(
            Long id,
            BillType type,
            String provider,
            BigDecimal amount,
            LocalDate dueDate) {

        Bill bill =
                repository.findById(id)
                        .orElseThrow(
                                () -> new BillNotFoundException(id)
                        );

        bill.update(
                type,
                provider,
                amount,
                dueDate
        );

        return repository.save(bill);
    }

    public void deleteBill(Long id) {

        boolean deleted =
                repository.delete(id);

        if (!deleted) {
            throw new BillNotFoundException(id);
        }
    }

    public Bill getBill(Long id) {

        return repository.findById(id)
                .orElseThrow(
                        () -> new BillNotFoundException(id)
                );
    }

    public List<Bill> getAllBills() {
        return repository.findAll();
    }

    public List<Bill> searchByProvider(
            String provider) {

        return repository.findByProvider(provider);
    }
}