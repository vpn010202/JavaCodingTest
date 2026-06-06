package service;

import domain.Bill;
import domain.Payment;
import domain.PaymentStatus;
import domain.ScheduledPayment;
import exception.BillAlreadyPaidException;
import repository.BillRepository;
import repository.PaymentRepository;
import repository.ScheduledPaymentRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

public class ScheduleService {

    private final ScheduledPaymentRepository repository;

    private final PaymentRepository paymentRepository;

    private final BillRepository billRepository;

    private final PaymentService paymentService;

    private final AtomicLong paymentId =
            new AtomicLong(1000);

    public ScheduleService(
            ScheduledPaymentRepository repository,
            PaymentService paymentService,
            PaymentRepository paymentRepository,
            BillRepository billRepository) {

        this.repository = repository;
        this.paymentService = paymentService;
        this.paymentRepository = paymentRepository;
        this.billRepository = billRepository;
    }

    public void schedulePayment(
            Long billId,
            LocalDate scheduledDate) {

        Bill bill =
                billRepository.findById(billId)
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Sorry! Not found a bill with such id"
                                )
                        );

        if (bill.isPaid()) {

            throw new BillAlreadyPaidException(
                    billId
            );
        }

        if (scheduledDate.isBefore(
                LocalDate.now())) {

            throw new RuntimeException(
                    "Scheduled date must be in the future."
            );
        }

        repository.save(
                new ScheduledPayment(
                        billId,
                        scheduledDate
                )
        );

        Payment pendingPayment =
                new Payment(
                        paymentId.getAndIncrement(),
                        billId,
                        bill.getAmount(),
                        scheduledDate,
                        PaymentStatus.PENDING
                );

        paymentRepository.save(
                pendingPayment
        );
    }

    public void processSchedules(
            LocalDate currentDate) {

        List<ScheduledPayment> schedules =
                repository.findDueSchedules(
                        currentDate
                );

        for (ScheduledPayment schedule :
                schedules) {

            try {

                Bill bill =
                        billRepository.findById(
                                        schedule.billId()
                                )
                                .orElse(null);

                if (bill == null) {

                    repository.delete(
                            schedule.billId()
                    );

                    continue;
                }

                if (bill.isPaid()) {

                    repository.delete(
                            schedule.billId()
                    );

                    continue;
                }

                paymentService.processScheduledPayment(
                        bill.getId()
                );

                repository.delete(
                        schedule.billId()
                );

            } catch (Exception exception) {
                Logger.getLogger(exception.getMessage());
            }
        }
    }
}