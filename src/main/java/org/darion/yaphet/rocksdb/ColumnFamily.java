package org.darion.yaphet.rocksdb;

import org.rocksdb.ColumnFamilyDescriptor;
import org.rocksdb.ColumnFamilyHandle;
import org.rocksdb.ColumnFamilyOptions;
import org.rocksdb.Options;
import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;
import org.rocksdb.WriteBatch;

public class ColumnFamily {

    public static void main(String[] args) throws RocksDBException {
        RocksDB.loadLibrary();

        Options options = new Options().setCreateIfMissing(true);
        RocksDB client = RocksDB.open(options, "/tmp/columnfamily.db");

        ColumnFamilyDescriptor descriptor = new ColumnFamilyDescriptor(
                "ColumnFamily0".getBytes(), new ColumnFamilyOptions());
        ColumnFamilyHandle handle = client.createColumnFamily(descriptor);

        WriteBatch batch = new WriteBatch(Constant.K * 32);
        for (int index = 0; index < 1024 * 1024; index++) {
            batch.put(("key_" + index).getBytes(), ("value_" + index).getBytes());
        }


        // release resources
        handle.dispose();
        client.close();
        options.dispose();
    }
}
