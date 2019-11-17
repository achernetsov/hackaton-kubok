package org.smartact;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.papernet.CompletionAct;
import org.smartact.controller.CompletionActDto;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Test {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
//    private static final DateTimeFormatter format = DateTimeFormatter.ofPattern("HHmm dd.MMM.yyyy");

    public static void main(String[] args) throws JsonProcessingException {
        CompletionAct completionAct = new CompletionAct();
        completionAct.setUuid(UUID.randomUUID().toString());
        completionAct.setKey();
        completionAct.setContractNum("123");
        completionAct.setCustomer("customer");
        completionAct.setDateTime(LocalDateTime.now().toString());
        completionAct.setExecutor("executor");
        completionAct.setMoneyAmountFact(5.);
        completionAct.setMoneyAmountPlan(5.);
        completionAct.setName("Completed act");
        completionAct.setRejectReason("");
        completionAct.setSLA(99.);

        completionAct.setState("ISSUED");

        CompletionActDto actDto = CompletionActDto.from(completionAct);

        String json = OBJECT_MAPPER.writeValueAsString(actDto);
        System.out.println(json);


    }
}
