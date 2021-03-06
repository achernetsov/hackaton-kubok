/*
SPDX-License-Identifier: Apache-2.0
*/

package org.example;

import org.example.ledgerapi.StateList;
import org.hyperledger.fabric.contract.Context;

import java.util.List;

public class ActList {

    private StateList stateList;

    public ActList(Context ctx) {
        this.stateList = StateList.getStateList(ctx, ActList.class.getSimpleName(), CompletionAct::deserialize);
    }

    public ActList addAct(CompletionAct act) {
        System.out.printf("Invoke ActList.addAct: act = %s", act);
        stateList.addState(act);
        return this;
    }

    public CompletionAct getAct(String paperKey) {
        return (CompletionAct) this.stateList.getState(paperKey);
    }

    public List<String> getActs() {
        return this.stateList.getUuids();
    }

    public String getLastAddedAct() {
        return this.stateList.getLastAddedUuid();
    }

    public ActList updateAct(CompletionAct act) {
        this.stateList.updateState(act);
        return this;
    }
}
