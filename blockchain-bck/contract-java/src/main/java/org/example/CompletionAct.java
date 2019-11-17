/*
 * SPDX-License-Identifier:
 */

package org.example;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.ledgerapi.State;
import org.hyperledger.fabric.contract.annotation.DataType;
import org.hyperledger.fabric.contract.annotation.Property;
import org.json.JSONPropertyIgnore;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@DataType()
public class CompletionAct extends State {

    // Enumerate commercial paper state values
    public final static String ISSUED = "ISSUED";
    public final static String CUSTOMER_AGREED = "CUSTOMER_AGREED";
    public final static String CUSTOMER_REFUSED = "CUSTOMER_REFUSED";
    public final static String CONTROL_AGREED = "CONTROL_AGREED";
    public final static String CONTROL_REFUSED = "CONTROL_REFUSED";
    public final static String ACCOUNTING_AGREED = "ACCOUNTING_AGREED";
    public final static String ACCOUNTING_REFUSED = "ACCOUNTING_REFUSED";
    public final static String CLOSED = "CLOSED";

    @Property()
    private String state = "";

    @Property()
    private UUID uuid;

    @Property()
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private LocalDateTime dateTime;

    @Property()
    private String name;

    @Property()
    private String executor;

    @Property()
    private String customer;

    @Property()
    private String contractNum;

    @Property()
    private Double SLA;

    @Property()
    private Double moneyAmountPlan;

    @Property()
    private Double moneyAmountFact;

    @Property()
    private String rejectReason;

    public String getState() {
        return state;
    }

    public CompletionAct setState(String state) {
        this.state = state;
        return this;
    }

    @JSONPropertyIgnore()
    @JsonIgnore
    public boolean isIssued() {
        return this.state.equals(CompletionAct.ISSUED);
    }

    @JSONPropertyIgnore()
    @JsonIgnore
    public boolean isCustomerAgreed() {
        return this.state.equals(CompletionAct.CUSTOMER_AGREED);
    }

    @JSONPropertyIgnore()
    @JsonIgnore
    public boolean isCustomerRefused() {
        return this.state.equals(CompletionAct.CUSTOMER_REFUSED);
    }

    @JSONPropertyIgnore()
    @JsonIgnore
    public boolean isControlAgreed() {
        return this.state.equals(CompletionAct.CONTROL_AGREED);
    }

    @JSONPropertyIgnore()
    @JsonIgnore
    public boolean isControlRefused() {
        return this.state.equals(CompletionAct.CONTROL_REFUSED);
    }

    @JSONPropertyIgnore()
    @JsonIgnore
    public boolean isAccountingAgreed() {
        return this.state.equals(CompletionAct.ACCOUNTING_AGREED);
    }

    @JSONPropertyIgnore()
    @JsonIgnore
    public boolean isAccountingRefused() {
        return this.state.equals(CompletionAct.ACCOUNTING_REFUSED);
    }

    @JSONPropertyIgnore()
    @JsonIgnore
    public boolean isClosed() {
        return this.state.equals(CompletionAct.CLOSED);
    }

    public CompletionAct setIssued() {
        this.state = CompletionAct.ISSUED;
        return this;
    }

    public CompletionAct setCustomerAgreed() {
        this.state = CompletionAct.CUSTOMER_AGREED;
        return this;
    }

    public CompletionAct setCustomerRefused() {
        this.state = CompletionAct.CUSTOMER_REFUSED;
        return this;
    }

    public CompletionAct setControlAgreed() {
        this.state = CompletionAct.CONTROL_AGREED;
        return this;
    }

    public CompletionAct setControlRefused() {
        this.state = CompletionAct.CONTROL_AGREED;
        return this;
    }

    public CompletionAct setAccountingAgreed() {
        this.state = CompletionAct.ACCOUNTING_AGREED;
        return this;
    }

    public CompletionAct setAccountingRefused() {
        this.state = CompletionAct.ACCOUNTING_REFUSED;
        return this;
    }

    public CompletionAct setClosed() {
        this.state = CompletionAct.CLOSED;
        return this;
    }

    public CompletionAct() {
        super();
    }

    public CompletionAct setKey() {
        this.key = State.makeKey(new String[]{this.uuid.toString()});
        return this;
    }

    @JsonProperty("uuid")
    public UUID getUuid() {
        return uuid;
    }

    public CompletionAct setUuid(String uuid) {
        this.uuid = UUID.fromString(uuid);
        return this;
    }

    @JsonProperty("dateTime")
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public CompletionAct setDateTime(String dateTime) {
        this.dateTime = LocalDateTime.parse(dateTime);
        return this;
    }

    @JsonProperty("executor")
    public String getExecutor() {
        return executor;
    }

    public CompletionAct setExecutor(String executor) {
        this.executor = executor;
        return this;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    public CompletionAct setName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("customer")
    public String getCustomer() {
        return customer;
    }

    public CompletionAct setCustomer(String customer) {
        this.customer = customer;
        return this;
    }

    @JsonProperty("contractNum")
    public String getContractNum() {
        return contractNum;
    }

    public CompletionAct setContractNum(String contractNum) {
        this.contractNum = contractNum;
        return this;
    }

    @JsonProperty("SLA")
    public Double getSLA() {
        return SLA;
    }

    public CompletionAct setSLA(Double SLA) {
        this.SLA = SLA;
        return this;
    }

    @JsonProperty("moneyAmountPlan")
    public Double getMoneyAmountPlan() {
        return moneyAmountPlan;
    }

    public CompletionAct setMoneyAmountPlan(Double moneyAmountPlan) {
        this.moneyAmountPlan = moneyAmountPlan;
        return this;
    }

    @JsonProperty("moneyAmountFact")
    public Double getMoneyAmountFact() {
        return moneyAmountFact;
    }

    public CompletionAct setMoneyAmountFact(Double moneyAmountFact) {
        this.moneyAmountFact = moneyAmountFact;
        return this;
    }

    @JsonProperty("rejectReason")
    public String getRejectReason() {
        return rejectReason;
    }

    public CompletionAct setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
        return this;
    }

    @Override
    public String toString() {
        return "CompletionAct{" +
                "state='" + state + '\'' +
                ", uuid=" + uuid +
                ", dateTime=" + dateTime +
                ", name='" + name + '\'' +
                ", executor='" + executor + '\'' +
                ", customer='" + customer + '\'' +
                ", contractNum='" + contractNum + '\'' +
                ", SLA=" + SLA +
                ", moneyAmountPlan=" + moneyAmountPlan +
                ", moneyAmountFact=" + moneyAmountFact +
                ", rejectReason='" + rejectReason + '\'' +
                ", key='" + key + '\'' +
                '}';
    }

    /**
     * Deserialize a state data to commercial paper
     *
     * @param {Buffer} data to form back into the object
     */
    public static CompletionAct deserialize(byte[] data) {
        if (data == null || data.length == 0) {
            throw new IllegalArgumentException("CompletionAct.deserialize: input byte[] is empty (not JSON)");
        }
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            CompletionAct completionAct = objectMapper.readValue(data, CompletionAct.class);
            completionAct.setKey();
            return completionAct;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String> deserializeList(byte[] data) {
        if (data == null || data.length == 0) {
            return Collections.emptyList();
        }
        ObjectMapper mapper = new ObjectMapper();
        try {
            String[] actUuids = mapper.readValue(data, String[].class);
            return Arrays.asList(actUuids);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] serialize(CompletionAct act) {
        return State.serialize(act);
    }

    /**
     * Factory method to create a commercial paper object
     */
    public static CompletionAct createInstance(String uuid, String dateTime, String name, String executor, String customer,
                                               String contractNum, Double SLA, Double moneyAmountPlan, Double moneyAmountFact,
                                               String rejectReason, String state) {
        System.out.println("Invoke method CompletionAct.createInstance");
        return new CompletionAct()
                .setUuid(uuid == null ? UUID.randomUUID().toString() : uuid)
                .setDateTime(dateTime == null ? LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME) : dateTime)
                .setName(name)
                .setExecutor(executor).setCustomer(customer).setContractNum(contractNum)
                .setSLA(SLA).setMoneyAmountPlan(moneyAmountPlan).setMoneyAmountFact(moneyAmountFact)
                .setRejectReason(rejectReason).setKey().setState(state);
    }
}
