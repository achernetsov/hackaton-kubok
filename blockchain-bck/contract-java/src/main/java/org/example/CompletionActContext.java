package org.example;

import org.hyperledger.fabric.contract.Context;
import org.hyperledger.fabric.shim.ChaincodeStub;

class CompletionActContext extends Context {

    public CompletionActContext(ChaincodeStub stub) {
        super(stub);
        this.actList = new ActList(this);
    }

    public ActList actList;

}
