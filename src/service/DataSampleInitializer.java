package service;

import domain.BillType;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DataSampleInitializer {

    private final BillService billService;

    public DataSampleInitializer(
            BillService billService) {

        this.billService = billService;
    }

    public void initialize() {

        billService.createBill(
                1L,
                BillType.ELECTRIC,
                "EVN HCMC",
                new BigDecimal("200000"),
                LocalDate.of(2020,10,25)
        );

        billService.createBill(
                2L,
                BillType.WATER,
                "SAVACO HCMC",
                new BigDecimal("175000"),
                LocalDate.of(2020,10,30)
        );

        billService.createBill(
                3L,
                BillType.INTERNET,
                "VNPT",
                new BigDecimal("800000"),
                LocalDate.of(2020,11,30)
        );
    }
}