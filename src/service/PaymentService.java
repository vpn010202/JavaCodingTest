package service;

import domain.*;
import exception.BillAlreadyPaidException;
import exception.BillNotFoundException;
import exception.InsufficientFundException;
import repository.BillRepository;
import repository.PaymentRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class PaymentService {

    private final Account account;
    private final BillRepository billRepository;
    private final PaymentRepository paymentRepository;

    private final AtomicLong paymentId =
            new AtomicLong(1);

    public PaymentService(
            Account account,
            BillRepository billRepository,
            PaymentRepository paymentRepository) {

        this.account = account;
        this.billRepository = billRepository;
        this.paymentRepository = paymentRepository;
    }

    public void cashIn(BigDecimal amount) {

        account.cashIn(amount);
    }

    public BigDecimal getBalance() {

        return account.getBalance();
    }

    public void pay(Long billId) {

        Bill bill =
                billRepository.findById(billId)
                        .orElseThrow(
                                () -> new BillNotFoundException(billId)
                        );

        if (bill.isPaid()) {
            throw new BillAlreadyPaidException(billId);
        }

        if (account.hasEnough(
                bill.getAmount())) {

            throw new InsufficientFundException();
        }

        account.deduct(
                bill.getAmount()
        );

        bill.markPaid();

        Payment payment =
                new Payment(
                        paymentId.getAndIncrement(),
                        bill.getId(),
                        bill.getAmount(),
                        LocalDate.now(),
                        PaymentStatus.PROCESSED
                );

        paymentRepository.save(payment);

    }

    public void payMultiple(
            List<Long> billIds) {

        List<Bill> bills =
                billIds.stream()
                        .map(id ->
                                billRepository.findById(id)
                                        .orElseThrow(
                                                () ->
                                                        new BillNotFoundException(id)
                                        )
                        )
                        .sorted(
                                Comparator.comparing(
                                        Bill::getDueDate
                                )
                        )
                        .toList();

        for (Bill bill : bills) {

            if (bill.isPaid()) {

                throw new BillAlreadyPaidException(
                        bill.getId()
                );
            }
        }

        BigDecimal totalAmount =
                bills.stream()
                        .map(Bill::getAmount)
                        .reduce(
                                BigDecimal.ZERO,
                                BigDecimal::add
                        );

        if (account.hasEnough(
                totalAmount)) {

            throw new InsufficientFundException();
        }

        for (Bill bill : bills) {

            account.deduct(
                    bill.getAmount()
            );

            bill.markPaid();

            Payment payment =
                    new Payment(
                            paymentId.getAndIncrement(),
                            bill.getId(),
                            bill.getAmount(),
                            LocalDate.now(),
                            PaymentStatus.PROCESSED
                    );

            paymentRepository.save(
                    payment
            );
        }
    }
    public List<Payment> getHistory() {

        return paymentRepository.findAll();
    }

    public void processScheduledPayment(
            Long billId) {

        Bill bill =
                billRepository.findById(billId)
                        .orElseThrow(
                                () -> new BillNotFoundException(
                                        billId
                                )
                        );

        if (bill.isPaid()) {

            throw new BillAlreadyPaidException(
                    billId
            );
        }

        if (account.hasEnough(
                bill.getAmount())) {

            throw new InsufficientFundException();
        }

        account.deduct(
                bill.getAmount()
        );

        bill.markPaid();

        paymentRepository.findAll()
                .stream()
                .filter(payment ->
                        payment.getBillId()
                                .equals(
                                        billId
                                ))
                .filter(payment ->
                        payment.getStatus()
                                == PaymentStatus.PENDING)
                .findFirst()
                .ifPresent(
                        Payment::markProcessed
                );
    }
}