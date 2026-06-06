package service.test;

public class TestSystemRunner {

    static void main(String[] args) {

        BillServiceTest.run();

        PaymentServiceTest.run();

        ScheduleServiceTest.run();

        System.out.println(
                "\nALL TESTS PASSED"
        );
    }
}