package org.magnetocorp;

import org.hyperledger.fabric.gateway.Gateway;
import org.hyperledger.fabric.gateway.Network;
import org.hyperledger.fabric.gateway.Wallet;
import org.hyperledger.fabric.protos.common.Common;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class EventRegister {

    private static final String ENVKEY = "CONTRACT_NAME";

    public static void main(String[] args) {

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
                Network network = gateway.getNetwork("mychannel");

                network.addBlockListener(blockEvent -> {
                    Common.Block block = blockEvent.getBlock();

                    System.out.println("Receive event: " + blockEvent);
                    System.out.println("BLock All FIelds: " + block.getAllFields());
                    System.out.println("BLock Number: " + blockEvent.getBlockNumber());
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
