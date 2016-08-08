package org.darion.yaphet.rocksdb;

import org.rocksdb.Options;
import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;
import org.rocksdb.Snapshot;

public class SnapshotsExample {
    public static void main(String[] args) throws RocksDBException {
        RocksDB.loadLibrary();

        Options options = new Options().setCreateIfMissing(true);
        RocksDB client = RocksDB.open(options, "/tmp/basic.db");

        try {
            Snapshot snapshots = client.getSnapshot();
        } finally {
            client.close();
        }
    }
}
