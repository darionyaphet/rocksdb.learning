package org.darion.yaphet.rocksdb;

import org.rocksdb.RocksDB;
import org.rocksdb.WriteOptions;

public class WriteOptionsExample {
    public static void main(String[] args) {
        RocksDB.loadLibrary();

        WriteOptions options = new WriteOptions();
        options.setSync(false).setDisableWAL(false);

    }
}
