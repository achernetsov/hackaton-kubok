package org.smartact.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.papernet.CompletionAct;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompletionActDto {

    String key;

    private String state;


    private UUID uuid;


    private String dateTime;


    private String name;


    private String executor;


    private String customer;


    private String contractNum;


    private Double SLA;


    private Double moneyAmountPlan;


    private Double moneyAmountFact;


    private String rejectReason;


    public static CompletionActDto from(CompletionAct act) {
        return new CompletionActDto(
                act.getKey(),
                act.getState(),
                act.getUuid(),
                act.getDateTime().toString(),
                act.getName(),
                act.getExecutor(),
                act.getCustomer(),
                act.getContractNum(),
                act.getSLA(),
                act.getMoneyAmountPlan(),
                act.getMoneyAmountFact(),
                act.getRejectReason()
        );
    }

    public CompletionAct toAct() {
        return CompletionAct.createInstance(uuid != null ? uuid.toString() : null, dateTime, name, executor,
                customer, contractNum, SLA, moneyAmountPlan, moneyAmountFact, rejectReason, state);
    }
}
