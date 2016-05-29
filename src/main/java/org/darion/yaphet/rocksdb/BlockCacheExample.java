package org.darion.yaphet.rocksdb;

import org.rocksdb.BlockBasedTableConfig;

public class BlockCacheExample {
    public static void main(String[] args) {
        BlockBasedTableConfig config = new BlockBasedTableConfig();
        config = config.setBlockCacheSize(1024 * 8);

        
    }
}
