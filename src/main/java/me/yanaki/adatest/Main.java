package me.yanaki.adatest;

import io.blockfrost.sdk.api.EpochService;
import io.blockfrost.sdk.api.PoolService;
import io.blockfrost.sdk.api.exception.APIException;
import io.blockfrost.sdk.api.model.Epoch;
import io.blockfrost.sdk.api.model.Pool;
import io.blockfrost.sdk.api.model.PoolMetadata;
import io.blockfrost.sdk.api.util.Constants;
import io.blockfrost.sdk.impl.EpochServiceImpl;
import io.blockfrost.sdk.impl.PoolServiceImpl;

import java.util.List;


public class Main {

    public static void main(String[] args) {
        printPoolData();
    }

    private static void printPoolData() {
        PoolService poolService = new PoolServiceImpl(
                Constants.BLOCKFROST_TESTNET_URL, System.getenv("PROJECT_ID"));
       try {
            List<String> allPools = poolService.getAllPools();
            for (String poolString: allPools) {
                Pool pool = poolService.getPool(poolString);
                PoolMetadata poolMetadata = poolService.getPoolMetadata(poolString);

                String poolName = poolMetadata.getName() == null ? "Unnamed" : poolMetadata.getName();

                String livePledge = pool.getLivePledge();
                String activeStake = pool.getActiveStake();
                Integer blocksMinted = pool.getBlocksMinted();

                System.out.println(poolName + " has a live pledge of: " +
                        livePledge + " and an active stake of: " + activeStake +
                        " and has minted " + blocksMinted + " blocks");
            }
        } catch (APIException e) {
            throw new RuntimeException(e);
        }
    }

}