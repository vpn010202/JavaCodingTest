package service.test;

import domain.*;
import repository.*;
import repository.memory.*;
import service.*;
import service.test.util.TestUtils;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ScheduleServiceTest {

    public static void run() {

        shouldCreatePendingPayment();

        System.out.println(
                "ScheduleServiceTest PASSED"
        );
    }

    private static void shouldCreatePendingPayment() {

        Account account =
                new Account();

        BillRepository billRepository =
                new InMemoryBillRepository();

        PaymentRepository paymentRepository =
                new InMemoryPaymentRepository();

        PaymentService paymentService =
                new PaymentService(
                        account,
                        billRepository,
                        paymentRepository
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

        ScheduleService scheduleService =
                new ScheduleService(
                        new InMemoryScheduledPaymentRepository(),
                        paymentService,
                        paymentRepository,
                        billRepository
                );

        scheduleService.schedulePayment(
                1L,
                LocalDate.now().plusDays(1)
        );

        TestUtils.assertEquals(
                PaymentStatus.PENDING,
                paymentRepository.findAll()
                        .getFirst()
                        .getStatus(),
                "Pending payment"
        );
    }
}