/*
SPDX-License-Identifier: Apache-2.0
*/

package org.magnetocorp;

import org.hyperledger.fabric.gateway.*;
import org.papernet.CompletionAct;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public class AccountingAgree {

    private static final String ENVKEY = "CONTRACT_NAME";

    public static void main(String[] args) {

        String contractName = "papercontract";
        // get the name of the contract, in case it is overridden
        Map<String, String> envvar = System.getenv();
        if (envvar.containsKey(ENVKEY)) {
            contractName = envvar.get(ENVKEY);
        }

        Gateway.Builder builder = Gateway.createBuilder();

        try {
            // A wallet stores a collection of identities
            Path walletPath = Paths.get("..", "identity", "user", "isabella", "wallet");
            Wallet wallet = Wallet.createFileSystemWallet(walletPath);

            String userName = "User1@org1.example.com";

            Path connectionProfile = Paths.get("..", "gateway", "networkConnection.yaml");

            // Set connection options on the gateway builder
            builder.identity(wallet, userName).networkConfig(connectionProfile).discovery(false);

            // Connect to gateway using application specified parameters
            try (Gateway gateway = builder.connect()) {
                // Access PaperNet network
                System.out.println("Use network channel: mychannel.");
                Network network = gateway.getNetwork("mychannel");

                // Get addressability to commercial paper contract
                System.out.println("Use org.papernet.commercialpaper smart contract.");
                Contract contract = network.getContract(contractName, "org.papernet.commercialpaper");

                // Issue commercial paper
                System.out.println("Submit commercial paper issue transaction.");
                byte[] response = contract.submitTransaction("accountingAgree", args[0]);

                // Process response
                System.out.println("Process issue transaction response.");
                CompletionAct act = CompletionAct.deserialize(response);
                System.out.println(act);
            }
        } catch (GatewayException | IOException | TimeoutException | InterruptedException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
