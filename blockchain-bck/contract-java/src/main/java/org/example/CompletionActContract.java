/*
SPDX-License-Identifier: Apache-2.0
*/
package org.example;

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
    public CompletionAct issue(CompletionActContext ctx, String executor, String customer, String contractNum,
                               Double SLA, Double moneyAmount) {
        System.out.printf("Invoke method CompletionActContract.issue: context = %s, executor = %s, customer = %s, " +
                "contractNum = %s, SLA = %.2f, moneyAmount = %.2f", ctx, executor, customer, contractNum, SLA, moneyAmount);

        // create an instance of the paper
        CompletionAct act = CompletionAct.createInstance(executor, customer, contractNum, SLA, moneyAmount, "");

        // Smart contract, rather than paper, moves paper into ISSUED state
        act.setIssued();

        System.out.println("Created completion act: " + act);
        // Add the paper to the list of all similar commercial papers in the ledger
        // world state
        ctx.actList.addAct(act);

        // Must return a serialized paper to caller of smart contract
        return act;
    }
}
