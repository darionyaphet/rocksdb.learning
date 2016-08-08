package org.darion.yaphet.rocksdb;

import org.rocksdb.Options;
import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;
import org.rocksdb.WriteBatch;

import java.nio.charset.Charset;

public class BatchExample {

    static {
        RocksDB.loadLibrary();
    }


    private static byte[] getBytes(String value) {
        return value.getBytes(Charset.defaultCharset());
    }

    public static void main(String[] args) throws RocksDBException {

        Options options = new Options().setCreateIfMissing(true);
        RocksDB client = RocksDB.open(options, "/tmp/batch.db");

        try {
            WriteBatch batch = new WriteBatch(1024);

            //atomically apply a set of updates
            for (int index = 0; index < 10; index++) {
                batch.put(getBytes("key_" + index), getBytes("value_" + index));
            }

            batch.count();

            for (int index = 0; index < 10; index += 2) {
                batch.remove(getBytes("key_" + index));
            }
        } finally {
            client.close();
        }
    }
}
