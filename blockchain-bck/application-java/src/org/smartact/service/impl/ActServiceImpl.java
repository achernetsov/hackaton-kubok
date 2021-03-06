package org.smartact.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hyperledger.fabric.gateway.*;
import org.papernet.CompletionAct;
import org.smartact.service.ActService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

@Service
public class ActServiceImpl implements ActService {

    private static final String CONTACT_NAME = "papercontract";

    private static final String MAGNETOCORP_PATH = "/home/nchernetsov/go/src/github.com/hyperledger/fabric-samples/commercial-paper/organization/magnetocorp/";

    private Wallet wallet;

    @Override
    public CompletionAct issue(CompletionAct act) {
        return invokeTransactionChaincodeMethod("issue", act.getName(), act.getExecutor(), act.getCustomer(),
                act.getContractNum() != null ? act.getContractNum() : "", act.getSLA().toString(),
                act.getMoneyAmountPlan() != null ? act.getMoneyAmountPlan().toString() : "0.0",
                act.getMoneyAmountFact() != null ? act.getMoneyAmountFact().toString() : "0.0");
    }

    @Override
    public CompletionAct get(String uuid) {
        Gateway.Builder builder = Gateway.createBuilder();

        try {
            // A wallet stores a collection of identities
            /*Path walletPath = Paths.get(MAGNETOCORP_PATH, "identity", "user", "isabella", "wallet");
            Wallet wallet = Wallet.createFileSystemWallet(walletPath);*/

            String userName = "User1@org1.example.com";

            Path connectionProfile = Paths.get(MAGNETOCORP_PATH, "gateway", "networkConnection.yaml");

            // Set connection options on the gateway builder
            builder.identity(wallet, userName).networkConfig(connectionProfile).discovery(false);

            // Connect to gateway using application specified parameters
            try (Gateway gateway = builder.connect()) {
                // Access PaperNet network
                System.out.println("Use network channel: mychannel.");
                Network network = gateway.getNetwork("mychannel");

                // Get addressability to commercial paper contract
                System.out.println("Use org.papernet.commercialpaper smart contract.");
                Contract contract = network.getContract(CONTACT_NAME, "org.papernet.commercialpaper");

                byte[] response = contract.evaluateTransaction("queryAct", uuid);

                return CompletionAct.deserialize(response);
            }
        } catch (GatewayException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<CompletionAct> getActs() {
        Gateway.Builder builder = Gateway.createBuilder();

        try {
            // A wallet stores a collection of identities
            /*Path walletPath = Paths.get(MAGNETOCORP_PATH, "identity", "user", "isabella", "wallet");
            Wallet wallet = Wallet.createFileSystemWallet(walletPath);*/

            String userName = "User1@org1.example.com";

            Path connectionProfile = Paths.get(MAGNETOCORP_PATH, "gateway", "networkConnection.yaml");

            // Set connection options on the gateway builder
            builder.identity(wallet, userName).networkConfig(connectionProfile).discovery(false);

            // Connect to gateway using application specified parameters
            try (Gateway gateway = builder.connect()) {
                // Access PaperNet network
                System.out.println("Use network channel: mychannel.");
                Network network = gateway.getNetwork("mychannel");

                // Get addressability to commercial paper contract
                System.out.println("Use org.papernet.commercialpaper smart contract.");
                Contract contract = network.getContract(CONTACT_NAME, "org.papernet.commercialpaper");

                byte[] response = contract.evaluateTransaction("queryAllActs");

                List<String> uuids = CompletionAct.deserializeList(response);

                return uuids.stream().map(this::get).collect(Collectors.toList());
            }
        } catch (GatewayException | IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        return null;
    }

    @Override
    public CompletionAct getLastAddedAct() {
        Gateway.Builder builder = Gateway.createBuilder();
        try {
            String userName = "User1@org1.example.com";

            Path connectionProfile = Paths.get(MAGNETOCORP_PATH, "gateway", "networkConnection.yaml");

            // Set connection options on the gateway builder
            builder.identity(wallet, userName).networkConfig(connectionProfile).discovery(false);

            // Connect to gateway using application specified parameters
            try (Gateway gateway = builder.connect()) {
                // Access PaperNet network
                System.out.println("Use network channel: mychannel.");
                Network network = gateway.getNetwork("mychannel");

                // Get addressability to commercial paper contract
                System.out.println("Use org.papernet.commercialpaper smart contract.");
                Contract contract = network.getContract(CONTACT_NAME, "org.papernet.commercialpaper");

                byte[] response = contract.evaluateTransaction("queryLastAddedAct");
                if (response == null || response.length == 0) {
                    return null;
                }
                ObjectMapper mapper = new ObjectMapper();
                try {
                    String lastAddedActUuid = mapper.readValue(response, String.class);
                    return get(lastAddedActUuid);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (GatewayException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public CompletionAct customerAgree(String uuid) {
        return invokeTransactionChaincodeMethod("customerAgree", uuid);
    }

    @Override
    public CompletionAct customerRefuse(String uuid, String rejectReason) {
        return invokeTransactionChaincodeMethod("customerRefuse", uuid, rejectReason);
    }

    @Override
    public CompletionAct controlAgree(String uuid) {
        return invokeTransactionChaincodeMethod("controlAgree", uuid);
    }

    @Override
    public CompletionAct controlRefuse(String uuid, String rejectReason) {
        return invokeTransactionChaincodeMethod("controlRefuse", uuid, rejectReason);
    }

    @Override
    public CompletionAct accountingAgree(String uuid) {
        return invokeTransactionChaincodeMethod("accountingAgree", uuid);
    }

    @Override
    public CompletionAct accountingRefuse(String uuid, String rejectReason) {
        return invokeTransactionChaincodeMethod("accountingRefuse", uuid, rejectReason);
    }

    private CompletionAct invokeTransactionChaincodeMethod(String methodName, String... parameters) {
        Gateway.Builder builder = Gateway.createBuilder();

        try {
            // A wallet stores a collection of identities
            Path walletPath = Paths.get(MAGNETOCORP_PATH, "identity", "user", "isabella", "wallet");
            Wallet wallet = Wallet.createFileSystemWallet(walletPath);

            String userName = "User1@org1.example.com";

            Path connectionProfile = Paths.get(MAGNETOCORP_PATH, "gateway", "networkConnection.yaml");

            // Set connection options on the gateway builder
            builder.identity(wallet, userName).networkConfig(connectionProfile).discovery(false);

            // Connect to gateway using application specified parameters
            try (Gateway gateway = builder.connect()) {
                // Access PaperNet network
                System.out.println("Use network channel: mychannel.");
                Network network = gateway.getNetwork("mychannel");

                // Get addressability to commercial paper contract
                System.out.println("Use org.papernet.commercialpaper smart contract.");
                Contract contract = network.getContract(CONTACT_NAME, "org.papernet.commercialpaper");

                // Issue commercial paper
                System.out.println("Submit commercial paper issue transaction.");
                byte[] response = contract.submitTransaction(methodName, parameters);

                // Process response
                System.out.println("Process issue transaction response.");
                return CompletionAct.deserialize(response);
            }
        } catch (GatewayException | IOException | TimeoutException | InterruptedException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        return null;
    }

    @PostConstruct
    private void init() {
        try {
            // A wallet stores a collection of identities
            Path walletPath = Paths.get(MAGNETOCORP_PATH, "identity", "user", "isabella", "wallet");
            wallet = Wallet.createFileSystemWallet(walletPath);

            // Location of credentials to be stored in the wallet
            Path credentialPath = Paths.get(MAGNETOCORP_PATH, "..", "..", "..", "basic-network", "crypto-config",
                    "peerOrganizations", "org1.example.com", "users", "User1@org1.example.com", "msp");
            Path certificatePem = credentialPath.resolve(Paths.get("signcerts",
                    "User1@org1.example.com-cert.pem"));
            Path privateKey = credentialPath.resolve(Paths.get("keystore",
                    "c75bd6911aca808941c3557ee7c97e90f3952e379497dc55eb903f31b50abc83_sk"));

            // Load credentials into wallet
            String identityLabel = "User1@org1.example.com";
            Wallet.Identity identity = Wallet.Identity.createIdentity("Org1MSP",
                    Files.newBufferedReader(certificatePem), Files.newBufferedReader(privateKey));

            wallet.put(identityLabel, identity);
        } catch (IOException e) {
            System.err.println("Error adding to wallet");
            e.printStackTrace();
        }
    }
}
