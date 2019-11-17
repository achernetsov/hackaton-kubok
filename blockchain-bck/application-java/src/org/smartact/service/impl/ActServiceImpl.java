package org.smartact.service.impl;

import org.hyperledger.fabric.gateway.*;
import org.papernet.CompletionAct;
import org.smartact.service.ActService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeoutException;

@Service
public class ActServiceImpl implements ActService {
    private static final String CONTRACT_ISSUE_METHOD = "issue";

    @Override
    public CompletionAct issue(CompletionAct act) {
        Gateway.Builder builder = Gateway.createBuilder();

        String contractName = act.getName();

        try {
            // A wallet stores a collection of identities
            Path walletPath = Paths.get("..", "identity", "user", "isabella", "wallet");
            Wallet wallet = Wallet.createFileSystemWallet(walletPath);

            // TODO
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
                Contract contract = network.getContract(act.getName(), "org.papernet.commercialpaper");

                // Issue commercial paper
                System.out.println("Submit commercial paper issue transaction.");
//                byte[] response = contract.submitTransaction("issue", "test completion act №1", "Naprasnii trud", "No money",
//                        "123456", "87.0", "19293.17", "15038.62");

                byte[] response = contract.submitTransaction(CONTRACT_ISSUE_METHOD,
                        act.getName(),
                        act.getExecutor(),
                        act.getCustomer(),
                        act.getContractNum(),
                        act.getSLA().toString(),
                        act.getMoneyAmountPlan().toString(),
                        act.getMoneyAmountFact().toString());

                // Process response
                System.out.println("Process issue transaction response.");
                return CompletionAct.deserialize(response);
            }
        } catch (GatewayException | IOException | TimeoutException | InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
