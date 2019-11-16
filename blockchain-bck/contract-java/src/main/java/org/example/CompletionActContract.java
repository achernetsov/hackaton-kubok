/*
SPDX-License-Identifier: Apache-2.0
*/
package org.example;

import org.example.ledgerapi.State;
import org.hyperledger.fabric.contract.Context;
import org.hyperledger.fabric.contract.ContractInterface;
import org.hyperledger.fabric.contract.annotation.*;
import org.hyperledger.fabric.shim.ChaincodeStub;

import java.util.logging.Logger;

/**
 * A custom context provides easy access to list of all commercial papers
 */

/**
 * Define commercial paper smart contract by extending Fabric Contract class
 */
@Contract(name = "org.papernet.commercialpaper", info = @Info(title = "MyAsset contract", description = "", version = "0.0.1", license = @License(name = "SPDX-License-Identifier: ", url = ""), contact = @Contact(email = "java-contract@example.com", name = "java-contract", url = "http://java-contract.me")))
@Default
public class CompletionActContract implements ContractInterface {

    // use the classname for the logger, this way you can refactor
    private final static Logger LOG = Logger.getLogger(CompletionActContract.class.getName());

    @Override
    public Context createContext(ChaincodeStub stub) {
        return new CompletionActContext(stub);
    }

    public CompletionActContract() {

    }

    /**
     * Define a custom context for commercial paper
     */

    /**
     * Instantiate to perform any setup of the ledger that might be required.
     *
     * @param {Context} ctx the transaction context
     */
    @Transaction
    public void instantiate(CompletionActContext ctx) {
        // No implementation required with this example
        // It could be where data migration is performed, if necessary
        LOG.info("No data migration to perform");
    }

    /**
     * Issue completion act
     */
    @Transaction
    public CompletionAct issue(CompletionActContext ctx, String name, String executor, String customer,
                               String contractNum, Double SLA, Double moneyAmountPlan, Double moneyAmountFact) {
        System.out.printf("Invoke method CompletionActContract.issue: context = %s, name = %s, executor = %s, customer = %s, " +
                        "contractNum = %s, SLA = %.2f, moneyAmountPlan = %.2f, moneyAmountFact = %.2f",
                ctx, name, executor, customer, contractNum, SLA, moneyAmountPlan, moneyAmountFact);

        // create an instance of the paper
        CompletionAct act = CompletionAct.createInstance(null, null, name, executor, customer, contractNum,
                SLA, moneyAmountPlan, moneyAmountFact, "", "");

        // Smart contract, rather than paper, moves paper into ISSUED state
        act.setIssued();

        System.out.println("Created completion act: " + act);
        // Add the paper to the list of all similar commercial papers in the ledger
        // world state
        ctx.actList.addAct(act);

        // Must return a serialized paper to caller of smart contract
        return act;
    }

    @Transaction
    public CompletionAct customerAgree(CompletionActContext ctx, String uuid) {
        System.out.printf("Invoke method CompletionActContract.customerAgree: context = %s, uuid = %s", ctx, uuid);

        // Retrieve the current paper using key fields provided
        String paperKey = State.makeKey(new String[]{uuid});
        CompletionAct act = ctx.actList.getAct(paperKey);

        // Validate current owner
        if (!act.getState().equals(CompletionAct.ISSUED)) {
            throw new RuntimeException("Act uuid = " + uuid + " is not status ISSUED. It status = " + act.getState());
        }

        if (act.isIssued()) {
            act.setCustomerAgreed();
        }

        // Update the paper
        ctx.actList.updateAct(act);
        return act;
    }

    @Transaction
    public CompletionAct customerRefuse(CompletionActContext ctx, String uuid, String rejectReason) {
        System.out.printf("Invoke method CompletionActContract.customerRefuse: context = %s, uuid = %s, rejectReason = %s",
                ctx, uuid, rejectReason);

        // Retrieve the current paper using key fields provided
        String paperKey = State.makeKey(new String[]{uuid});
        CompletionAct act = ctx.actList.getAct(paperKey);

        // Validate current owner
        if (!act.getState().equals(CompletionAct.ISSUED)) {
            throw new RuntimeException("Act uuid = " + uuid + " is not status ISSUED. It status = " + act.getState());
        }
        if (rejectReason == null) {
            throw new IllegalArgumentException("Not set reject reason for act uuid = " + uuid);
        }

        if (act.isIssued()) {
            act.setCustomerRefused().setRejectReason(rejectReason);
            act.setClosed();
        }

        // Update the paper
        ctx.actList.updateAct(act);
        return act;
    }

    @Transaction
    public CompletionAct controlAgree(CompletionActContext ctx, String uuid) {
        System.out.printf("Invoke method CompletionActContract.controlAgree: context = %s, uuid = %s", ctx, uuid);

        // Retrieve the current paper using key fields provided
        String paperKey = State.makeKey(new String[]{uuid});
        CompletionAct act = ctx.actList.getAct(paperKey);

        // Validate current owner
        if (!act.getState().equals(CompletionAct.CUSTOMER_AGREED)) {
            throw new RuntimeException("Act uuid = " + uuid + " is not status CUSTOMER_AGREED. It status = " + act.getState());
        }

        if (act.isCustomerAgreed()) {
            act.setControlAgreed();
        }

        // Update the paper
        ctx.actList.updateAct(act);
        return act;
    }

    @Transaction
    public CompletionAct controlRefuse(CompletionActContext ctx, String uuid, String rejectReason) {
        System.out.printf("Invoke method CompletionActContract.controlRefuse: context = %s, uuid = %s, rejectReason = %s",
                ctx, uuid, rejectReason);

        // Retrieve the current paper using key fields provided
        String paperKey = State.makeKey(new String[]{uuid});
        CompletionAct act = ctx.actList.getAct(paperKey);

        // Validate current owner
        if (!act.getState().equals(CompletionAct.CUSTOMER_AGREED)) {
            throw new RuntimeException("Act uuid = " + uuid + " is not status CUSTOMER_AGREED. It status = " + act.getState());
        }
        if (rejectReason == null) {
            throw new IllegalArgumentException("Not set reject reason for act uuid = " + uuid);
        }

        if (act.isCustomerAgreed()) {
            act.setControlRefused().setRejectReason(rejectReason);
            act.setClosed();
        }

        // Update the paper
        ctx.actList.updateAct(act);
        return act;
    }

    @Transaction
    public CompletionAct accountingAgree(CompletionActContext ctx, String uuid) {
        System.out.printf("Invoke method CompletionActContract.accountingAgree: context = %s, uuid = %s", ctx, uuid);

        // Retrieve the current paper using key fields provided
        String paperKey = State.makeKey(new String[]{uuid});
        CompletionAct act = ctx.actList.getAct(paperKey);

        // Validate current owner
        if (!act.getState().equals(CompletionAct.CONTROL_AGREED)) {
            throw new RuntimeException("Act uuid = " + uuid + " is not status CONTROL_AGREED. It status = " + act.getState());
        }

        if (act.isControlAgreed()) {
            act.setAccountingAgreed();
            act.setClosed();
        }

        // Update the paper
        ctx.actList.updateAct(act);
        return act;
    }

    @Transaction
    public CompletionAct accountingRefuse(CompletionActContext ctx, String uuid, String rejectReason) {
        System.out.printf("Invoke method CompletionActContract.controlRefuse: context = %s, uuid = %s, rejectReason = %s",
                ctx, uuid, rejectReason);

        // Retrieve the current paper using key fields provided
        String paperKey = State.makeKey(new String[]{uuid});
        CompletionAct act = ctx.actList.getAct(paperKey);

        // Validate current owner
        if (!act.getState().equals(CompletionAct.CONTROL_AGREED)) {
            throw new RuntimeException("Act uuid = " + uuid + " is not status CONTROL_AGREED. It status = " + act.getState());
        }
        if (rejectReason == null) {
            throw new IllegalArgumentException("Not set reject reason for act uuid = " + uuid);
        }

        if (act.isControlAgreed()) {
            act.setAccountingRefused().setRejectReason(rejectReason);
            act.setClosed();
        }

        // Update the paper
        ctx.actList.updateAct(act);
        return act;
    }

    public CompletionAct getAct(CompletionActContext ctx, String uuid) {
        String paperKey = State.makeKey(new String[]{uuid});
        return ctx.actList.getAct(paperKey);
    }
}
