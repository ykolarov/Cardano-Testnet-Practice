package me.yanaki.adatest;

import io.blockfrost.sdk.api.BlockService;
import io.blockfrost.sdk.api.exception.APIException;
import io.blockfrost.sdk.api.model.Block;
import io.blockfrost.sdk.api.util.Constants;
import io.blockfrost.sdk.impl.BlockServiceImpl;

public class Main {

    public static void main(String[] args) {

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