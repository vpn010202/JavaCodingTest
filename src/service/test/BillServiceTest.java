package service.test;

import domain.Bill;
import domain.BillType;
import repository.BillRepository;
import repository.memory.InMemoryBillRepository;
import service.BillService;
import service.test.util.TestUtils;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BillServiceTest {

    public static void run() {

        shouldCreateBill();

        shouldUpdateBill();

        System.out.println(
                "BillServiceTest PASSED"
        );
    }

    private static void shouldCreateBill() {

        BillRepository repository =
                new InMemoryBillRepository();

        BillService service =
                new BillService(repository);

        Bill bill =
                service.createBill(
                        1L,
                        BillType.ELECTRIC,
                        "EVN",
                        new BigDecimal("200000"),
                        LocalDate.now()
                );

        TestUtils.assertEquals(
                1L,
                bill.getId(),
                "Create bill"
        );
    }

    private static void shouldUpdateBill() {

        BillRepository repository =
                new InMemoryBillRepository();

        BillService service =
                new BillService(repository);

        service.createBill(
                1L,
                BillType.ELECTRIC,
                "EVN",
                new BigDecimal("100"),
                LocalDate.now()
        );

        Bill updated =
                service.updateBill(
                        1L,
                        BillType.WATER,
                        "SAVACO",
                        new BigDecimal("200"),
                        LocalDate.now()
                );

        TestUtils.assertEquals(
                BillType.WATER,
                updated.getType(),
                "Update bill"
        );
    }
}