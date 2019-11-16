/*
SPDX-License-Identifier: Apache-2.0
*/

package org.example;

import org.example.ledgerapi.StateList;
import org.hyperledger.fabric.contract.Context;

public class ActList {

    private StateList stateList;

    public ActList(Context ctx) {
        this.stateList = StateList.getStateList(ctx, ActList.class.getSimpleName(), CompletionAct::deserialize);
    }

    public ActList addAct(CompletionAct act) {
        stateList.addState(act);
        return this;
    }

    public CompletionAct getAct(String paperKey) {
        return (CompletionAct) this.stateList.getState(paperKey);
    }

    public ActList updateAct(CompletionAct act) {
        this.stateList.updateState(act);
        return this;
    }
}
