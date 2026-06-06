package repository;

import domain.Bill;

import java.util.List;
import java.util.Optional;

public interface BillRepository
        extends Repository<Bill, Long> {

    List<Bill> findByProvider(String provider);

}