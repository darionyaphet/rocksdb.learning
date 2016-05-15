package org.darion.yaphet.rocksdb;

import org.rocksdb.Options;
import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;

public class QueueService {
    public static void main(String[] args) throws RocksDBException {
        Options options = new Options().setCreateIfMissing(true);

        RocksDB client = RocksDB.open(options, "");
    }
}
