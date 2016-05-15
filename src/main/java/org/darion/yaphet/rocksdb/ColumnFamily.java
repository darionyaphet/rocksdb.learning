package org.darion.yaphet.rocksdb;

import org.rocksdb.ColumnFamilyDescriptor;
import org.rocksdb.ColumnFamilyHandle;
import org.rocksdb.ColumnFamilyOptions;
import org.rocksdb.Options;
import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;

public class ColumnFamily {
    public static void main(String[] args) throws RocksDBException {
        RocksDB.loadLibrary();

        Options options = new Options().setCreateIfMissing(true);
        RocksDB client = RocksDB.open(options, "/tmp/columnfamily.db");

        ColumnFamilyDescriptor descriptor = new ColumnFamilyDescriptor(
                "ColumnFamily0".getBytes(), new ColumnFamilyOptions());
        ColumnFamilyHandle handle = client.createColumnFamily(descriptor);

    }
}
