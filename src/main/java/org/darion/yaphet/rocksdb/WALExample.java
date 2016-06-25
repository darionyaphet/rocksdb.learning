package org.darion.yaphet.rocksdb;

import org.rocksdb.Options;
import org.rocksdb.RocksDB;

public class WALExample {
    static {
        RocksDB.loadLibrary();
    }

    public static void main(String[] args) {
        Options options = new Options()
                .setCreateIfMissing(true)
                .setWalDir("/tmp/basic.wal")
                .setWalSizeLimitMB(1024)
                .setWalTtlSeconds(20);

    }
}
