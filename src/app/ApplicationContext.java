package app;

import command.*;
import domain.Account;
import repository.BillRepository;
import repository.PaymentRepository;
import repository.ScheduledPaymentRepository;
import repository.memory.InMemoryBillRepository;
import repository.memory.InMemoryPaymentRepository;
import repository.memory.InMemoryScheduledPaymentRepository;
import service.*;

public class ApplicationContext {

    private final CommandRegistry registry;

    private final ScheduleService scheduleService;

    public ApplicationContext() {

        this.registry = new CommandRegistry();

        Account account =
                new Account();

        BillRepository billRepository =
                new InMemoryBillRepository();

        PaymentRepository paymentRepository =
                new InMemoryPaymentRepository();

        ScheduledPaymentRepository scheduleRepository =
                new InMemoryScheduledPaymentRepository();

        BillService billService =
                new BillService(
                        billRepository
                );

        PaymentService paymentService =
                new PaymentService(
                        account,
                        billRepository,
                        paymentRepository
                );

        scheduleService =
                new ScheduleService(
                        scheduleRepository,
                        paymentService,
                        paymentRepository,
                        billRepository
                );

        DataSampleInitializer initializer =
                new DataSampleInitializer(
                        billService
                );

        initializer.initialize();

        registerCommands(
                billService,
                paymentService,
                scheduleService
        );
    }

    private void registerCommands(
            BillService billService,
            PaymentService paymentService,
            ScheduleService scheduleService) {

        registry.register(
                "CASH_IN",
                new CashInCommand(
                        paymentService
                )
        );

        registry.register(
                "LIST_BILL",
                new ListBillCommand(
                        billService
                )
        );

        registry.register(
                "VIEW_BILL",
                new ViewBillCommand(
                        billService
                )
        );

        registry.register(
                "CREATE_BILL",
                new CreateBillCommand(
                        billService
                )
        );

        registry.register(
                "UPDATE_BILL",
                new UpdateBillCommand(
                        billService
                )
        );

        registry.register(
                "DELETE_BILL",
                new DeleteBillCommand(
                        billService
                )
        );

        registry.register(
                "PAY",
                new PayCommand(
                        paymentService
                )
        );

        registry.register(
                "LIST_PAYMENT",
                new ListPaymentCommand(
                        paymentService
                )
        );

        registry.register(
                "SEARCH_BILL_BY_PROVIDER",
                new SearchBillByProviderCommand(
                        billService
                )
        );

        registry.register(
                "DUE_DATE",
                new DueDateCommand(
                        billService
                )
        );

        registry.register(
                "SCHEDULE",
                new SchedulePaymentCommand(
                        scheduleService
                )
        );

        registry.register(
                "EXIT",
                new ExitCommand()
        );
    }

    public CommandRegistry getRegistry() {
        return registry;
    }

    public ScheduleService getScheduleService() {
        return scheduleService;
    }
}