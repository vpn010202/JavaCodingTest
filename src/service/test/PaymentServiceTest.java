package service.test;

import domain.*;
import repository.BillRepository;
import repository.PaymentRepository;
import repository.memory.InMemoryBillRepository;
import repository.memory.InMemoryPaymentRepository;
import service.PaymentService;
import service.test.util.TestUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class PaymentServiceTest {

    public static void run() {

        shouldPayBill();

        shouldPayMultiple();

        System.out.println(
                "PaymentServiceTest PASSED"
        );
    }

    private static void shouldPayBill() {

        Account account =
                new Account();

        BillRepository billRepository =
                new InMemoryBillRepository();

        PaymentRepository paymentRepository =
                new InMemoryPaymentRepository();

        PaymentService service =
                new PaymentService(
                        account,
                        billRepository,
                        paymentRepository
                );

        service.cashIn(
                new BigDecimal("500000")
        );

        Bill bill =
                new Bill(
                        1L,
                        BillType.ELECTRIC,
                        "EVN",
                        new BigDecimal("200000"),
                        LocalDate.now()
                );

        billRepository.save(
                bill
        );

        service.pay(1L);

        TestUtils.assertTrue(
                bill.isPaid(),
                "Pay bill"
        );
    }

    private static void shouldPayMultiple() {

        Account account =
                new Account();

        BillRepository billRepository =
                new InMemoryBillRepository();

        PaymentRepository paymentRepository =
                new InMemoryPaymentRepository();

        PaymentService service =
                new PaymentService(
                        account,
                        billRepository,
                        paymentRepository
                );

        service.cashIn(
                new BigDecimal("1000000")
        );

        billRepository.save(
                new Bill(
                        1L,
                        BillType.ELECTRIC,
                        "EVN",
                        new BigDecimal("100000"),
                        LocalDate.now()
                )
        );

        billRepository.save(
                new Bill(
                        2L,
                        BillType.WATER,
                        "SAVACO",
                        new BigDecimal("100000"),
                        LocalDate.now()
                )
        );

        service.payMultiple(
                List.of(1L, 2L)
        );

        TestUtils.assertTrue(
                billRepository.findById(1L)
                        .get()
                        .isPaid(),
                "Pay multiple bill #1"
        );

        TestUtils.assertTrue(
                billRepository.findById(2L)
                        .get()
                        .isPaid(),
                "Pay multiple bill #2"
        );
    }
}