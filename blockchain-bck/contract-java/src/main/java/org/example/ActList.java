/*
SPDX-License-Identifier: Apache-2.0
*/

package org.example;

import org.example.ledgerapi.State;
import org.example.ledgerapi.StateList;
import org.hyperledger.fabric.contract.Context;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<CompletionAct> getActs() {
        List<State> states = this.stateList.getStates();
        System.out.println("ActList.getActs: states = " + states);
        return states.stream()
                .map(state -> (CompletionAct) state)
                .collect(Collectors.toList());
    }

    public ActList updateAct(CompletionAct act) {
        this.stateList.updateState(act);
        return this;
    }
}
