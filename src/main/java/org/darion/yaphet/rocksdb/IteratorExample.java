package org.darion.yaphet.rocksdb;

import org.rocksdb.Options;
import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;
import org.rocksdb.RocksIterator;

public class IteratorExample {
    static {
        RocksDB.loadLibrary();
    }

    public static void main(String[] args) throws RocksDBException {
        Options options = new Options().setCreateIfMissing(true);
        RocksDB client = RocksDB.open(options, "/tmp/basic.db");
        RocksIterator iterator = client.newIterator();
        try {
            for (iterator.seekToFirst(); iterator.isValid(); iterator.next()) {
                String key = new String(iterator.key());
                String value = new String(iterator.value());
                System.out.println(key + " --> " + value);
            }

            for (iterator.seek("yid".getBytes()); iterator.isValid(); iterator.prev()) {
                String key = new String(iterator.key());
                String value = new String(iterator.value());
                System.out.println(key + " --> " + value);
            }
        } finally {
            client.close();
        }


    }
}
