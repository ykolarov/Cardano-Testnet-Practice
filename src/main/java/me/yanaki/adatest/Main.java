package me.yanaki.adatest;

import io.blockfrost.sdk.api.BlockService;
import io.blockfrost.sdk.api.PoolService;
import io.blockfrost.sdk.api.exception.APIException;
import io.blockfrost.sdk.api.model.Block;
import io.blockfrost.sdk.api.util.Constants;
import io.blockfrost.sdk.impl.BlockServiceImpl;
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
            for (String pool: allPools) {
                System.out.println(pool + poolService.getPoolMetadata(pool));
            }


        } catch (APIException e) {
            throw new RuntimeException(e);
        }
    }

    private static void printLastBlock() {
        BlockService blockService = new BlockServiceImpl(
                Constants.BLOCKFROST_TESTNET_URL, System.getenv("PROJECT_ID"));
        try {
            Block block = blockService.getLatestBlock();
            System.out.println(block.toString());
        } catch (APIException e) {
            throw new RuntimeException(e);
        }
    }
}