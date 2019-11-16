/*
 * SPDX-License-Identifier:
 */

package org.example;

import org.example.ledgerapi.State;
import org.hyperledger.fabric.contract.annotation.DataType;
import org.hyperledger.fabric.contract.annotation.Property;
import org.json.JSONObject;
import org.json.JSONPropertyIgnore;

import java.time.LocalDateTime;
import java.util.UUID;

import static java.nio.charset.StandardCharsets.UTF_8;

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
    public boolean isIssued() {
        return this.state.equals(CompletionAct.ISSUED);
    }

    @JSONPropertyIgnore()
    public boolean isCustomerAgreed() {
        return this.state.equals(CompletionAct.CUSTOMER_AGREED);
    }

    @JSONPropertyIgnore()
    public boolean isCustomerRefused() {
        return this.state.equals(CompletionAct.CUSTOMER_REFUSED);
    }

    @JSONPropertyIgnore()
    public boolean isControlAgreed() {
        return this.state.equals(CompletionAct.CONTROL_AGREED);
    }

    @JSONPropertyIgnore()
    public boolean isControlRefused() {
        return this.state.equals(CompletionAct.CONTROL_REFUSED);
    }

    @JSONPropertyIgnore()
    public boolean isAccountingAgreed() {
        return this.state.equals(CompletionAct.ACCOUNTING_AGREED);
    }

    @JSONPropertyIgnore()
    public boolean isAccountingRefused() {
        return this.state.equals(CompletionAct.ACCOUNTING_REFUSED);
    }

    @JSONPropertyIgnore()
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
        this.uuid = UUID.randomUUID();
        this.dateTime = LocalDateTime.now();
    }

    public CompletionAct setKey() {
        this.key = State.makeKey(new String[]{this.uuid.toString()});
        return this;
    }

    public UUID getUuid() {
        return uuid;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getExecutor() {
        return executor;
    }

    public CompletionAct setExecutor(String executor) {
        this.executor = executor;
        return this;
    }

    public String getName() {
        return name;
    }

    public CompletionAct setName(String name) {
        this.name = name;
        return this;
    }

    public String getCustomer() {
        return customer;
    }

    public CompletionAct setCustomer(String customer) {
        this.customer = customer;
        return this;
    }

    public String getContractNum() {
        return contractNum;
    }

    public CompletionAct setContractNum(String contractNum) {
        this.contractNum = contractNum;
        return this;
    }

    public Double getSLA() {
        return SLA;
    }

    public CompletionAct setSLA(Double SLA) {
        this.SLA = SLA;
        return this;
    }

    public Double getMoneyAmountPlan() {
        return moneyAmountPlan;
    }

    public CompletionAct setMoneyAmountPlan(Double moneyAmountPlan) {
        this.moneyAmountPlan = moneyAmountPlan;
        return this;
    }

    public Double getMoneyAmountFact() {
        return moneyAmountFact;
    }

    public CompletionAct setMoneyAmountFact(Double moneyAmountFact) {
        this.moneyAmountFact = moneyAmountFact;
        return this;
    }

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
        JSONObject json = new JSONObject(new String(data, UTF_8));

        String state = json.getString("state");
        String name = json.getString("name");
        String executor = json.getString("executor");
        String customer = json.getString("customer");
        String contractNum = json.getString("contractNum");
        Double SLA = json.getDouble("SLA");
        Double moneyAmountPlan = json.getDouble("moneyAmountPlan");
        Double moneyAmountFact = json.getDouble("moneyAmountFact");
        return createInstance(name, executor, customer, contractNum, SLA, moneyAmountPlan, moneyAmountFact, state);
    }

    public static byte[] serialize(CompletionAct paper) {
        return State.serialize(paper);
    }

    /**
     * Factory method to create a commercial paper object
     */
    public static CompletionAct createInstance(String name, String executor, String customer, String contractNum,
                                               Double SLA, Double moneyAmountPlan, Double moneyAmountFact, String state) {
        System.out.println("Invoke method CompletionAct.createInstance");
        return new CompletionAct()
                .setName(name)
                .setExecutor(executor).setCustomer(customer).setContractNum(contractNum)
                .setSLA(SLA).setMoneyAmountPlan(moneyAmountPlan).setMoneyAmountFact(moneyAmountFact)
                .setKey().setState(state);
    }
}
